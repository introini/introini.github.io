#include <string>
#include <vector>
#include <iostream>
#include <cassert>
using namespace std;
#include "smartptr.h"

SmartPtr<vector<int> >  createLargeObject()
{
    SmartPtr<vector<int> >  pv(new vector<int>());
    
    for (int i = 0; i < 100000; i++) 
	pv->push_back(i);

    return pv;
}

int main()
{
    SmartPtr<string> ps1;
    SmartPtr<string> ps2(new string("hello "));
    SmartPtr<string> ps3(new string("world "));
    ps1 = ps2 = ps3;
    // should print "world world world "
    cout << *ps1 << *ps2 << *ps3 << endl;

    int *c = new int(1);
    cout << *c << endl;
    ++*c;
    cout << *c << endl;


    SmartPtr<vector<int> >  pv1;
    pv1 = createLargeObject();
    for (int i = 0; i < 100000; i++) {
	assert(i == (*pv1)[i]);
    }

    // Note that we do not call delete anywhere in this code.

    return 0;
}
