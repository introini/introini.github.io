# Makefile

# Set compiler variables for C and C++
CC = gcc
CXX = g++

# Create INCLUDES variable for use if needed
INCLUDES = -I../../lab3/solutions/part1/

# Set flags for compiling C and C++
# Debugging: -g
# Enable all warnings: -Wall  
CFLAGS = -g -Wall $(INCLUDES)
CXXFLAGS = -g -Wall $(INCLUDES)

# Set flag for linking
# Debugging: -g
LDFLAGS = -g -L../../lab3/solutions/part1/

 
# Create LDLIBS variable for linking libraries
LDLIBS = -lmylist

# ------------ #
# First target #
# ------------ #
mdb-lookup: mdb-lookup.o

# ------------------- #
# Dependent target(s) #
# ------------------- #

mdb-lookup.o: mdb-lookup.c  

# --------------- #
# PHONY target(s) #
# --------------- #

.PHONY: clean
clean:
	rm -f *.o a.out core mdb-lookup

.PHONY: valgrind
valgrind:
	valgrind --leak-check=full ./mdb-lookup my-mdb

.PHONY: valgrind-cs3157
valgrind-cs3157:
	valgrind --leak-check=full ./mdb-lookup mdb-cs3157

.PHONY: novalgrind
novalgrind:
	./mdb-lookup my-mdb 

.PHONY: novalgrind-cs
novalgrind-cs:
	./mdb-lookup mdb-cs3157 

.PHONY: jae
jae:
	./mdb-lookup-cs my-mdb

.PHONY: jae-cs
jae-cs:
	./mdb-lookup-cs mdb-cs3157

.PHONY: jae-valgrind
jae-valgrind:
	valgrind --leak-check=full ./mdb-lookup-cs my-mdb

.PHONY: jae-cs-valgrind
jae-cs-valgrind:
	valgrind --leak-check=full ./mdb-lookup-cs mdb-cs3157

.PHONY: all
all: clean mdb


