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

convert: convert.o bin.o

# ------------------- #
# Dependent target(s) #
# ------------------- #

convert.o: convert.c

bin.o: bin.c

# --------------- #
# PHONY target(s) #
# --------------- #

.PHONY: clean
clean:
	rm -f *.o a.out core convert

.PHONY: all
all: clean convert


