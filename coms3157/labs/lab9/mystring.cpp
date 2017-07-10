#include <cstring>
#include <cstdio>

#include "mystring.h"

// default constructor

MyString::MyString() 
{
#ifdef BASIC4TRACE
    fprintf(stderr, "BASIC4TRACE: (%p)->MyString()\n", this);
#endif

    data = new char[1];
    data[0] = '\0';
    
    len = 0;
}

// constructor

MyString::MyString(const char* p)
{
#ifdef BASIC4TRACE
    fprintf(stderr, "BASIC4TRACE: (%p)->MyString(const char *)\n", this);
#endif

    if (p) {
	len = strlen(p);
	data = new char[len+1];
	strcpy(data, p);
    } else {
	data = new char[1];
	data[0] = '\0';
	len = 0;
    }
}

// destructor

MyString::~MyString() 
{
#ifdef BASIC4TRACE
    fprintf(stderr, "BASIC4TRACE: (%p)->~MyString()\n", this);
#endif

    delete[] data;
}

// copy constructor 

MyString::MyString(const MyString& s) 
{
#ifdef BASIC4TRACE
    fprintf(stderr, "BASIC4TRACE: (%p)->MyString(const MyString&)\n", this);
#endif

    len = s.len;
    
    data = new char[len+1];
    strcpy(data, s.data);
}

// copy assignment

MyString& MyString::operator=(const MyString& rhs)
{
#ifdef BASIC4TRACE
    fprintf(stderr, "BASIC4TRACE: (%p)->op=(const MyString&)\n", this);
#endif

    if (this == &rhs) {
	return *this;
    }

    // first, deallocate memory that 'this' used to hold

    delete[] data;

    // now copy from rhs
    
    len = rhs.len;

    data = new char[len+1];
    strcpy(data, rhs.data);

    return *this;
}

// operator<
int operator<(const MyString& s1, const MyString& s2)
{
    int i = strcmp(s1.data,s2.data);
    return (i < 0); 
}

// operator>
int operator>(const MyString& s1, const MyString& s2)
{
    int i = strcmp(s1.data,s2.data);
    return (i > 0); 
}

// opertator==
int operator==(const MyString& s1, const MyString& s2)
{
    int i = strcmp(s1.data,s2.data);
    return (i == 0); 
}

// opertator!=
int operator!=(const MyString& s1, const MyString& s2)
{
    return !(s1==s2); 
}

// opertator<=
int operator<=(const MyString& s1, const MyString& s2)
{
    return (s1 < s2  || s1 == s2); 
}

// opertator>=
int operator>=(const MyString& s1, const MyString& s2)
{
    return (s1 > s2 || s1 == s2); 
}

// operator+=
MyString& MyString::operator+=(const MyString& rhs)
{
    // Create a temp string for the new string

    MyString temp;
    delete [] temp.data;
    
    // Calculate new length
    temp.len = len + strlen(rhs.data);
    temp.data = new char[temp.len+1];
    // Copy over original lhs
    strcpy(temp.data,data);
    // concat the rhs data
    strcat(temp.data,rhs.data);
   
    // Copy the new length and data to member 
    len = len + strlen(rhs.data);  
    *this = temp;

    return *this;
}
// put-to operator

ostream& operator<<(ostream& os, const MyString& s)
{
    os << s.data;
    return os;
}

// get-from operator

istream& operator>>(istream& is, MyString& s)
{
    // this is kinda cheating, but this is just to illustrate how this
    // function can work.
    
    string temp;
    is >> temp;

    delete[] s.data;

    s.len = strlen(temp.c_str());
    s.data = new char[s.len+1];
    strcpy(s.data, temp.c_str());

    return is;
}

// operator[] - in real life this function should be declared inline

char& MyString::operator[](int i) 
{
    return data[i];
}

// operator[] const - in real life this should be inline

const char& MyString::operator[](int i) const
{
    // illustration of casting away constness
    return ((MyString&)*this)[i];
}

