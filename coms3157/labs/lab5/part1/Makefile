# Makefile

# Set compiler variables for C and C++
CC = gcc
CXX = g++

# Create INCLUDES variable for use if needed
INCLUDES =

# Set flags for compiling C and C++
# Debugging: -g
# Enable all warnings: -Wall  
CFLAGS = -g -Wall $(INCLUDES)
CXXFLAGS = -g -Wall $(INCLUDES)

# Set flag for linking
# Debugging: -g
LDFLAGS = -g 

 
# Create LDLIBS variable for linking libraries
LDLIBS = 

# ------------ #
# First target #
# ------------ #
.PHONY: default
default: mdb-lookup-server-nc-2 mdb-lookup-server-nc-1


# ------------------- #
# Dependent target(s) #
# ------------------- #

mdb-lookup-server-nc-2: mdb-lookup-server-nc-2.o

mdb-lookup-server-nc-2.o: mdb-lookup-server-nc-2.c  

mdb-lookup-server-nc-1: mdb-lookup-server-nc-1.o

mdb-lookup-server-nc-1.o: mdb-lookup-server-nc-1.c  


# --------------- #
# PHONY target(s) #
# --------------- #

.PHONY: clean
clean:
	rm -f *.o mypipe-* a.out core mdb-lookup-server-nc-2 mdb-lookup-server-nc-1

.PHONY: server-1
server-1:
	./mdb-lookup-server-nc-2

.PHONY: valgrind
valgrind:
	valgrind --leak-check=full ./mdb-lookup-server-nc-2

.PHONY: novalgrind
novalgrind:
	./mdb-lookup-server-nc-2

.PHONY: all
all: clean mdb
