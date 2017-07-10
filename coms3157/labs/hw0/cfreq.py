#                         #
# Mike Introini - mbi2105 #
#                         #

import sys

try:
    # Hardcoding file to variable 'fileName'
    fileName = sys.argv[1]

    # Create an empty dictionary that will group each charater
    d = {}

    # Open the stream in Read mode and assign it the name
    # 'fileToRead', then use the .read() function on the stream
    # and assign it to the variable name 'reader'
    with open( fileName,"r" ) as fileToRead:
        reader = fileToRead.read()
    fileToRead.close() # close to the stream
    
    # Assign the total number of characters using the 'len()' function
    # to 'totalChar'
    totalChar = len( reader )

    # Iterate through the dictionary adding each ASCII character
    # as a Key in the dictionary. If the dictionary does not contain
    # that character, then add it and assign '1' as its value. 
    # If the dictionary contains the current character, get the value
    # of the key and increment it by '1'.
    for i in reader:
        c = ord(i)
        if not d.has_key( c ):
            d[c] = 1
        else:
            d[c] = d.get( c ) + 1
    
    # Create a list of tuples containing the Key Value pair and sort it
    oKeys = d.items() 
    oKeys.sort() 

    # Iterate through the list and use the value of each key
    # to calculate the percentage occurance of each character in the file.
    for k in oKeys:
        percentage = float( d.get( k[0] ) ) / totalChar * 100 
        print str( k[0] ) + " " + str( format(percentage, '.2f') )

except Exception as e:
    print e
    print "Looks like something went wrong, please try again."
