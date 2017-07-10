CC  = g++
CXX = g++

INCLUDES =

CFLAGS   = -g -Wall $(INCLUDES)
CXXFLAGS = -g -Wall $(INCLUDES) -std=c++0x

LDFLAGS = -g
LDLIBS  =

executables = smartptr-test1 
objects = smartprt-test1.o

.PHONY: default
default: $(executables)

$(objects): smartptr.h

.PHONY: clean
clean:
	rm -f *~ a.out core $(objects) $(executables)

.PHONY: all
all: clean default
