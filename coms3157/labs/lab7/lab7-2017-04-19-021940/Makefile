
#
# Makefile for lab 6, part 1
#

CC  = gcc
CXX = g++

INCLUDES = 
CFLAGS   = -g -Wall $(INCLUDES)
CXXFLAGS = -g -Wall $(INCLUDES)

LDFLAGS = -g
LDLIBS = 

.PHONY: default
default: http-server 

http-server: http-server.o HandleHTTPRequest.o DieWithError.o
# header dependency
http-server.o: HandleHTTPRequest.o DieWithError.o
HandleHTTPClient.o: DieWithError.o 
DieWithError.o:

.PHONY: clean
clean:
	rm -f *.o *~ a.out core http-server

.PHONY: valgrind-mdb
valgrind-mdb:
	valgrind --leak-check=full ./mdb-lookup-server mdb 43553

.PHONY: novalgrind-mdb
novalgrind-mdb:
	./mdb-lookup-server mdb 43553

.PHONY: valgrind
valgrind:
	valgrind --leak-check=full ./http-server 33553 ~/html localhost 43553

.PHONY: novalgrind
novalgrind:
	./http-server 33553 ~/html localhost 43553


.PHONY: jae-mdb
jae-mdb:
	./mdb-lookup-server mdb 43553

.PHONY: jae-http
jae-http:
	./http 33553 ~/html localhost 43553

.PHONY: connect
connect: 
	nc 127.0.0.1 33553

.PHONY: all
all: clean default

