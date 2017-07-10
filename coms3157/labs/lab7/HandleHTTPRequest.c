#include "includes.h"
#include <sys/stat.h>
#include <stdlib.h>

#define BUF 4096   /* Size of receive buffer */
#define RESP501 "HTTP/1.0 501 Not Implemented\r\n"
#define HTML501 "<html>\n<body>\n<h1>\n501 Not Implemented\n</h1>\n</body>\n</html>"
#define RESP400 "HTTP/1.0 400 Bad Request\r\n"
#define HTML400 "<html>\n<body>\n<h1>\n400 Bad Request\n</h1>\n</body>\n</html>"
#define RESP404 "HTTP/1.0 404 Not Found\r\n"
#define HTML404 "<html>\n<body>\n<h1>\n404 File Not Found\n</h1>\n</body>\n</html>"
#define RESP200 "HTTP/1.0 200 OK\r\n"

void DieWithError(char *errorMessage, int socket); 
void Die(char *errorMessage);

/* Send Error Headers and HTML to the client */
void sendErr(char *buffer,char *err,char *errHTML,int sock) {
    size_t len;

    // Send Header to Client
    snprintf(buffer,BUF,"%s\r\n",err);
    if ((len = send(sock,buffer,strlen(err),0)) != strlen(err))
        DieWithError("Failed to send Server Header response",sock);
    
    snprintf(buffer,BUF,"%s","\r\n"); 
    if ((len = send(sock,buffer,strlen("\r\n"), 0)) != strlen("\r\n"))
        DieWithError("Couldn't send blank line", sock); 

    // Send HTML to Browser
    snprintf(buffer,BUF,"%s\r\n",errHTML);
    if ((len = send(sock,buffer,strlen(errHTML),0)) != strlen(errHTML))
        DieWithError("Failed to send HTML", sock);

}

// Send the 200 OK header
void sendOK(char *buffer, int sock) {
    size_t len;

    snprintf(buffer,BUF,"%s\r\n",RESP200);
    if ((len = send(sock,buffer,strlen(RESP200),0)) != strlen(RESP200))
        DieWithError("Couldn't send response", sock);

    snprintf(buffer,BUF,"%s","\r\n"); 
    if ((len = send(sock,buffer,strlen("\r\n"),0)) != strlen("\r\n"))
        DieWithError("Couldn't send blank line", sock); 

}

/* This function will send content to the client
 * when a valid request has been made and the 
 * content exists.
 */
size_t sendContent(char *fileP, int sock) {
    FILE *local = fopen(fileP, "rb");
    char file_buf[BUF];
    size_t len;
    
    if (local == NULL)
        fprintf(stderr, "Can't open %s\n", fileP);

    sendOK(file_buf, sock);

    /* Send the file */
    while ((len = fread(file_buf,1,BUF,local)) > 0) {
        if (send(sock,file_buf,len,0) != len ) 
            DieWithError("fwrite failed", sock);
    }

    if (ferror(local))
        DieWithError("read err", sock);

    fclose(local);

    return len; // returns the byte count for the file sent 
}

size_t HandleHTTPRequest(const char *wr, char *clntIP, int clntSocket, int mdbSocket)
{
    char buf[BUF];
    char requestLine[BUF]; 
    char srvResponse[BUF];
    char path[1000];
    char *token_separators = "\t \r\n";  
    char *resp501 = RESP501;
    char *html501 = HTML501;
    char *resp400 = RESP400;
    char *html400 = HTML400;
    char *resp404 = RESP404;
    char *html404 = HTML404;
    char *str;
    struct stat sb;
    size_t bytesSent = 0;

    const char* form =
        "<h1>mdb-lookup</h1>\n"
        "<p>\n"
        "<form method=GET action=/mdb-lookup>\n"
        "lookup: <input type=text name=key>\n"
        "<input type=submit>\n"
        "</form>\n"
        "</p>\n";

    FILE *fd = fdopen(clntSocket, "r+b");
    if (fd == NULL)
        DieWithError("fdopen failed", clntSocket);

    // read the 1st line
    if (fgets(requestLine, sizeof(requestLine), fd) == NULL) {
        if (ferror(fd)){
            DieWithError("IO error", clntSocket);
        } else {
            fprintf(stderr,"%s \"   \" 400 Bad Request\n",clntIP);
            fclose(fd);
            close(clntSocket);
            return bytesSent;
        }
    } 
    
    // tokenize the first line before processing it 
    char *method = strtok(requestLine, token_separators);
    char *requestURI = strtok(NULL, token_separators);
    char *proto = strtok(NULL, token_separators);

    /* Check for propper request protocol */
    if (proto == NULL) {
        fprintf(stderr,"%s \"%s %s %s\" %s\n",clntIP,method,requestURI,proto,"501 Not Implemented");
        sendErr(srvResponse,resp501,html501,clntSocket);
        fclose(fd);
        close(clntSocket);
        return bytesSent;      
   } else if (strncmp("HTTP/1.0",proto,9) != 0 && strncmp("HTTP/1.1",proto,9) != 0) {
        // Log to Server
        fprintf(stderr,"%s \"%s %s %s\" %s\n",clntIP,method,requestURI,proto,"501 Not Implemented");
        sendErr(srvResponse,resp501,html501,clntSocket);
        fclose(fd);
        close(clntSocket);
        return bytesSent;
    } else if (requestURI == NULL) {
        fprintf(stderr,"%s \"%s %s %s\" %s\n",clntIP,method,requestURI,proto,"501 Not Implemented");
        sendErr(srvResponse,resp501,html501,clntSocket);
        fclose(fd);
        close(clntSocket);
        return bytesSent;
    } else if (method == NULL) {
        fprintf(stderr,"%s \"%s %s %s\" %s\n",clntIP,method,requestURI,proto,"501 Not Implemented");
        sendErr(srvResponse,resp501,html501,clntSocket);
        fclose(fd);
        close(clntSocket); 
        return bytesSent;
    } else if (strncmp("GET", method, 3) != 0) {
        // Log to Server
        fprintf(stderr,"%s \"%s %s %s\" %s\n",clntIP,method,requestURI,proto,"501 Not Implemented");
        sendErr(srvResponse,resp501,html501,clntSocket);
        fclose(fd);
        close(clntSocket);
        return bytesSent;
    } 

    /* We need to construct the filepath from the requestURI 
     * First check if the URI begins with a "/" */
    str = strrchr(requestURI, '/');
    if (requestURI[0] != '/' || strstr(requestURI, "/../") != NULL || strcmp(str, "/..") == 0) { 
        fprintf(stderr,"%s \"%s %s %s\" %s\n",clntIP,method,requestURI,proto,"400 Bad Request");
        sendErr(srvResponse,resp400,html400,clntSocket);
        close(clntSocket);
        return bytesSent;
    } 

    if (strcmp(requestURI, "/mdb-lookup") == 0){
        sendOK(srvResponse,clntSocket); 
        if((bytesSent = send(clntSocket, form, strlen(form), 0)) != strlen(form))
            DieWithError("Send Form failed", clntSocket);
        fprintf(stderr,"%s \"%s %s %s\" %s\n",clntIP,method,requestURI,proto,"200 OK");
        fclose(fd);
        close(clntSocket);
        return bytesSent;
    }

    if (strncmp(requestURI, "/mdb-lookup?key=",16) == 0) {
        int len;
        char *tableTop = "<p><table border>\n";
        char *tableRow = "<tr><td>";

        /* Read the rest of the header response up to and including the 
         * blank line.
         */
        while (fgets(buf, sizeof(buf), fd) == NULL) {
            if (ferror(fd)) {
                // Close the socket and serve the next client
                fclose(fd);
                DieWithError("Server Terminated without a response", clntSocket);
            }   
            if (strncmp(buf,"\r\n",3) == 0) {
                break;
            } 
        }

        /* Sending the 200 OK response back to the client */
        sendOK(srvResponse,clntSocket); 

        len = strlen(form);
        if(send(clntSocket, form, len, 0) != len)
            DieWithError("Send Form failed", clntSocket);

        len = strlen(tableTop);
        if(send(clntSocket, tableTop, len, 0) != len)
            DieWithError("Send Table failed", clntSocket);

        /* Wrap the mdbSocket in FILE* to use fgets */
        FILE *fdMDB = fdopen(mdbSocket, "rb");
        if (fd == NULL)
            DieWithError("Couldn't open mdbSocket", clntSocket);

        /* parse the query */
        strcpy(buf, requestURI+16);
        strcat(buf,"\n"); // append the /n character
        len = strlen(buf);
        if (send(mdbSocket, buf, len, 0) != len)
            DieWithError("Couldn't send lookup to MDB Server", clntSocket);

        /* Read results from MDBServer one line at a time */
        while(fgets(buf, sizeof(buf), fdMDB) != NULL) {
            if (ferror(fdMDB)) {
                fprintf(stderr, "Somthing went wrong");
            }
            if (strncmp(buf,"\n",2) == 0) {
                break;
            }
            /* Send each result to the client as it comes from
             * MDBServer. This will populate one row of our table
             */
            len = sprintf(srvResponse,"%s%s</td></tr>\n",tableRow,buf);
            if(send(clntSocket, srvResponse, len, 0) != len) {
                DieWithError("Send Rows failed", clntSocket);
            }
        }

        /* Send the rest of the HTML */
        len = sprintf(srvResponse, "</table>\n</body></html>\r\n"); 
        if (send(clntSocket, srvResponse, len, 0) != len)
            DieWithError("Couldn't send end of HTML", clntSocket);
        /* Log the transaction */
        fprintf(stderr,"%s \"%s %s %s\" %s\n",clntIP,method,requestURI,proto,"200 OK");
        fclose(fd);     
        return bytesSent;
    }

    /* Read the rest of the header response up to and including the 
     * blank line.
     */
    while (fgets(buf, sizeof(buf), fd) == NULL) {
        if (ferror(fd)) {
            // Close the socket and serve the next client
            fclose(fd);
            DieWithError("Server Terminated without a response", clntSocket);
        } 
        if (strncmp(buf,"\r\n",3) == 0) {
            break;
        }
    }

    path[0] = '\0';
    snprintf(path,BUF,"%s%s",wr,requestURI);
    /* Append index.html if the request ends with '/' */
    if (requestURI[strlen(requestURI)-1] == '/') {
        snprintf(path,BUF,"%s%sindex.html",wr,requestURI);
    } else{
        /* just pass along the request appended to webroot.*/
        snprintf(path,BUF,"%s%s",wr,requestURI);
    }

    /* test the path */
    if (stat(path, &sb) == -1) {
        /* Send a 404 error to the client if we can't find the file.
         * also print a log to the server
         */    
        fprintf(stderr,"%s \"%s %s %s\" %s\n",clntIP,method,requestURI,proto,"404 File Not Found");
        sendErr(srvResponse,resp404,html404,clntSocket); 
        fclose(fd);
        close(clntSocket);
        return bytesSent;
    } 


    /* is it a directory? */
    if (S_ISDIR(sb.st_mode)) {

        /* Append /index.html and test the new path */
        snprintf(path,BUF,"%s%s/index.html",wr,requestURI);
        
        if (stat(path, &sb) == 0 && S_ISREG(sb.st_mode)) {
            /* Send out index.html from the new path we created */
            bytesSent = sendContent(path, clntSocket);   
            fprintf(stderr,"%s \"%s %s %s\" %s\n",clntIP,method,requestURI,proto,"200 OK");
            fclose(fd);           /* Close wrapper */
            close(clntSocket);
 
        } else {
                
            fprintf(stderr,"%s \"%s %s %s\" %s\n",clntIP,method,requestURI,proto,"404 File Not Found");
            sendErr(srvResponse,resp404,html404,clntSocket); 
            fclose(fd);
            close(clntSocket);
            return bytesSent;   
        }

    } else if (S_ISREG(sb.st_mode)) {

        /* Send the requested file */
        bytesSent = sendContent(path, clntSocket);   
        fprintf(stderr,"%s \"%s %s %s\" %s\n",clntIP,method,requestURI,proto,"200 OK");
        fclose(fd);           /* Close wrapper */
        close(clntSocket);

    }

    return bytesSent;
}
