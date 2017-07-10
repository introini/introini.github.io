// test4.cpp

#include "mystring.h"
MyString operator+(const MyString& lhs, const MyString& rhs) 
{ 
    MyString temp;
    temp += lhs;
    temp += rhs;
    return temp; 
}
static MyString add(const MyString& a, const MyString& b)
{
    MyString t(" and ");
    return a + t + b;
}

int main()
{
    MyString x("one");
    MyString y("two");

    MyString z = add(x, y);
    cout << z << endl;
    return 0;
}
