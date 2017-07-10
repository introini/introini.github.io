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

main: main.o gcd.o prime.o

# ------------------- #
# Dependent target(s) #
# ------------------- #

main.o: main.c

gcd.o: gcd.c gcd.h

prime.o: prime.c prime.h

# --------------- #
# PHONY target(s) #
# --------------- #

.PHONY: clean
clean:
	rm -f *.o a.out core main

.PHONY: all
all: clean main


