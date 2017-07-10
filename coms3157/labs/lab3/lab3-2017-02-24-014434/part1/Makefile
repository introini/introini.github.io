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
LDFLAGS = -g -L.

# Create LDLIBS variable for linking libraries
LDLIBS = -lmylist

# ------------ #
# First target #
# ------------ #

mylist-test: mylist-test.o libmylist.a

# ------------------- #
# Dependent target(s) #
# ------------------- #

mylist-test.o: mylist-test.c mylist.c mylist.h

mylist.o: mylist.c mylist.h

libmylist.a:  mylist.o
	ar rc libmylist.a mylist.o
	ranlib libmylist.a

# --------------- #
# PHONY target(s) #
# --------------- #

.PHONY: clean
clean:
	rm -f *.o a.out core mylist-test

.PHONY: valgrind
valgrind:
	valgrind --leak-check=full ./mylist-test

.PHONY: all
all: clean mylist-test


