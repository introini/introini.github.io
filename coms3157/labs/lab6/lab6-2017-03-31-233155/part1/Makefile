
#
# Makefile for lab 6, part 1
#

CC  = gcc
CXX = g++

INCLUDES = -I../../lab3/part1
CFLAGS   = -g -Wall $(INCLUDES)
CXXFLAGS = -g -Wall $(INCLUDES)

LDFLAGS = -g -L../../lab3/part1
LDLIBS = -lmylist

.PHONY: default
default: mdb-lookup-server 

mdb-lookup-server: mdb-lookup-server.o mdb-lookup.o DieWithError.o
# header dependency
mdb-lookup-server.o: mdb-lookup.o DieWithError.o mdb.h
mdb-lookup.o: DieWithError.o 
DieWithError.o:

.PHONY: clean
clean:
	rm -f *.o *~ a.out core mdb-lookup-server

.PHONY: valgrind
valgrind:
	valgrind --leak-check=full ./mdb-lookup-server mdb-cs3157-short 33553

.PHONY: novalgrind
novalgrind:
	./mdb-lookup-server mdb-cs3157-short 33553

.PHONY: connect
connect: 
	nc 127.0.0.1 33553

.PHONY: all
all: clean default

