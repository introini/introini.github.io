// Sample code showing how to use shared_ptr for an array of objects.
//
//     Compile with clang (recent versions with c++11 support):
//         clang++ -std=c++11 -stdlib=libc++ shared_ptr-test.cpp
//
//     Or with older g++ (versions with at least c++0x support):
//         g++ -std=c++0x shared_ptr-test.cpp

#include <memory> // for shared_ptr
#include <string>
#include <iostream>
#include <cassert>
using namespace std;

shared_ptr<string>  createLargeArray(size_t n)
{
    shared_ptr<string>  sp(new string[n],
            // shared_ptr takes an optional 2nd argument, a deleter,
            // which can be any callable object like 
            // a function pointer, a function object, or a lamda.
            [] (string *a) {
                delete[] a;
            }
            );
    for (size_t i = 0; i < n; ++i) {
        // shared_ptr does not overload operator[], 
        // so we need to apply &* to get back the underlying string*.
        (&*sp)[i] = "hello";
    }
    return sp;
}

int main()
{
    size_t n = 100000;
    shared_ptr<string>  p = createLargeArray(n);
    for (size_t i = 0; i < n; ++i) {
        assert((&*p)[i] == "hello");
    }
}
