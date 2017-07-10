# Makefile

# Set compiler variables for C and C++
CC = gcc
CXX = g++

# Create INCLUDES variable for use if needed
INCLUDES = -I../part1/

# Set flags for compiling C and C++
# Debugging: -g
# Enable all warnings: -Wall  
CFLAGS = -g -Wall $(INCLUDES)
CXXFLAGS = -g -Wall $(INCLUDES)

# Set flag for linking
# Debugging: -g
LDFLAGS = -g -L../part1/

 
# Create LDLIBS variable for linking libraries
LDLIBS = -lmylist

# ------------ #
# First target #
# ------------ #
revecho: revecho.o

# ------------------- #
# Dependent target(s) #
# ------------------- #

revecho.o: revecho.c  

# --------------- #
# PHONY target(s) #
# --------------- #

.PHONY: clean
clean:
	rm -f *.o a.out core revecho

.PHONY: valgrindfound
valgrindfound:
	valgrind --leak-check=full ./revecho hello world dude

.PHONY: valgrindnotfound
valgrindnotfound:
	valgrind --leak-check=full ./revecho hello world friend
.PHONY: all
all: clean revecho


