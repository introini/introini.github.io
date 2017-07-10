#include <string>
#include <iostream>
#include <cassert>
using namespace std;
#include "smartptr.h"

int main()
{
    SmartPtr<string> p1;
    assert(p1 == 0);
    assert(!p1);

    SmartPtr<string> p2(new string("hello"));
    assert(p2 != 0);
    assert(p2);

    assert(p1 < p2);
    p1 = p2;
    assert(p1 == p2);

    cout << p1 << ": " << *p1 << endl;
    cout << p2 << ": " << *p2 << endl;
    return 0;
}
