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

twecho: twecho.o

# ------------------- #
# Dependent target(s) #
# ------------------- #

twecho.o: twecho.c

# --------------- #
# PHONY target(s) #
# --------------- #

.PHONY: clean
clean:
	rm -f *.o a.out core twecho

.PHONY: valgrindeven
valgrindeven:
	valgrind --leak-check=full ./twecho one two

.PHONY: valgrindodd
valgrindodd:
	valgrind --leak-check=full ./twecho one two three

.PHONY: valgrindspecialchar
valgrindspecialchar:
	valgrind --leak-check=full ./twecho one two t30 *w3

.PHONY: all
all: clean twecho


