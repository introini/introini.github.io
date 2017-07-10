
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
default: http-client

http-client: http-client.o DieWithError.o
# header dependency
http-client.o: DieWithError.o
DieWithError.o:

.PHONY: clean
clean:
	rm -f *.o *.html *.jpg *~ a.out core http-client

.PHONY: valgrind
valgrind:
	valgrind --leak-check=full ./http-client www.gnu.org 80 /software/make/manual/make.html

.PHONY: novalgrind
novalgrind:
	./http-client www.gnu.org 80 /software/make/manual/make.html

.PHONY: all
all: clean default

