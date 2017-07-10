#include "mystring.h"
#include <cassert>
#include <cstdio>
#include <cstring>

MyString operator+(const MyString& lhs, const MyString& rhs) 
{ 
    MyString temp;
    temp += lhs;
    temp += rhs;
    return temp; 
}
int main() {

    MyString s1("that");
    MyString s2("this");
    MyString s3(s2);

    // Testing operator+=
    MyString s("hello");
    s += " world";
    cout << s << endl;

    // Testing operator+ after unfriending
    MyString sp(" ");
    MyString period(".");
    MyString str;

    str += "This" + sp + "should" + sp
        += "work" + sp + "without"
        += sp + "any" + sp + "memory"
        += sp + "leak" 
        += period;

    cout << str << endl;
    
    // Testing all other operators
    cout << "Asserting " << s1 << " < " << s2 << endl;
    assert(s1 < s2);

    cout << "Asserting " << s2 << " > " << s1 << endl;
    assert(s2 > s1);

    cout << "Asserting " << s3 << " == " << s2 << endl;
    assert(s3 == s2);

    cout << "Asserting " << s3 << " != " << s1 << endl;
    assert(s3 != s1);

    cout << "Asserting " << s1 << " <= " << s2 << endl;
    assert(s1 <= s2);

    cout << "Asserting " << s3 << " >= " << s1 << endl;
    assert(s3 >= s1);

    cout << "Asserting (string literal rhs) " << s1 << " > " << "hello" << endl;
    assert(s1 > "hello");

    cout << "Asserting (string literal lhs) " << "hello" << " < " << s1 << endl;
    assert("hello" < s1);

    cout << "Asserting (string literal rhs) " << "this" << " > " << s1 << endl;
    assert("this" > s1);

    cout << "Asserting (string literal lhs) " << s1 << " < " << "this" << endl;
    assert(s1 < "this");

    cout << "Asserting (string literal rhs) " << s1 << " == " << "that" << endl;
    assert(s1 == "that");

    cout << "Asserting (string literal lhs) " << "that" << " == " << s1 << endl;
    assert("that" == s1);

    cout << "Asserting (string literal rhs) " << s2 << " != " << "that" << endl;
    assert(s2 != "that");

    cout << "Asserting (string literal lhs) " << "that" << " != " << s2 << endl;
    assert("that" != s2);

    cout << "Asserting (string literal rhs) " << s3 << " == " << "that" << endl;
    assert(s3 != "that");

    cout << "Asserting (string literal lhs) " << "that" << " == " << s3 << endl;
    assert("that" != s3);

    cout << "Asserting (string literal rhs) " << s1 << " >= " << "hello" << endl;
    assert(s1 >= "hello");

    cout << "Asserting (string literal lhs) " << "hello" << " <= " << s1 << endl;
    assert("hello" <= s1);

    cout << "Asserting (string literal rhs) " << "this" << " >= " << s1 << endl;
    assert("this" >= s1);

    cout << "Asserting (string literal lhs) " << s1 << " <= " << "this" << endl;
    assert(s1 <= "this");

    return 0;
}

