#include "mystring.h"

MyString operator+(const MyString& lhs, const MyString& rhs) 
{ 
    MyString temp;
    temp += lhs;
    temp += rhs;
    return temp; 
}

int main() {

    MyString s1("hello ");

    MyString s2("world!");

    MyString s3;
    
    s3 = s1 + s2;

    cout << s3 << endl;
    
    cout << s1 + s2 << endl;
    
    cout << s1 + "world!" << endl;
    
    cout << "hello " + s2 << endl;

    // this is an error
    // cout << "hello " + "world!" << endl;

    return 0;
}
