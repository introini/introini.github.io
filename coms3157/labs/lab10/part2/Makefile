CC  = clang++
CXX = clang++

INCLUDES =

CFLAGS   = -g -Wall $(INCLUDES)
CXXFLAGS = -g -Wall $(INCLUDES) -std=c++11

LDFLAGS = -g
LDLIBS  =

executables = strlist-test
objects = strlist-test.o

.PHONY: default
default: $(executables)

$(objects): strlist.h tlist.h

.PHONY: valgrind
valgrind:
	valgrind --leak-check=full ./strlist-test

.PHONY: novalgrind
novalgrind:
	./strlist-test

.PHONY: clean
clean:
	rm -f *~ a.out core $(objects) $(executables)

.PHONY: all
all: clean default
