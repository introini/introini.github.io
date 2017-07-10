/* StrList Implementation */
#include <cstring>
#include "mystring.h"
#include "strlist.h"
#include <iomanip>
using namespace std;
extern "C" {
#include "mylist.h"
}

// Default Constructor
StrList::StrList()
{
    ::initList(&list);
}

// Destructor
StrList::~StrList()
{
    Node *n = list.head;

    while (n) {
        delete (MyString *)n->data;
        n=n->next;
    }

    ::removeAllNodes(&list);
}

// Copy Constructor
StrList::StrList(const StrList& l) 
{
    // We initialize a new list
    ::initList(&list);

    // Creating a node pointer will
    // allow us to walk the list and 
    // add each element to the back of
    // our copy constructed list.

    l.reverse();
    Node *n = l.list.head;
    
    while (n) {
        MyString *t = (MyString *)n->data;
        MyString *ns = new MyString(*t);
        ::addFront(&list,ns);
        n = n->next;
    }
    
    l.reverse();
}

// Copy Assignment
StrList& StrList::operator=(const StrList& l)
{
    if (this == &l)
        return *this;

    Node *ns = list.head;

    while (ns) {
        delete (MyString *)ns->data;
        ns=ns->next;
    }
    
    ::removeAllNodes(&list);
    ::initList(&list);

    l.reverse();
    Node *n = l.list.head;
    
    while (n) {
        MyString *t = (MyString *)n->data;
        MyString *ns = new MyString(*t);
        ::addFront(&list,ns);
        n = n->next;
    }

    l.reverse(); 

    return *this;
}

// Walk the list and return its size
int StrList::size() const
{
    int count = 0;

    Node *n = list.head;

    while(n) {
        count++;
        n = n->next;
    }
    
    return count;
}

void StrList::addFront(const MyString& s) 
{ 
     MyString *t = new MyString(s);
     ::addFront(&list,t);
}

MyString StrList::popFront()
{
    MyString *t = (MyString *) ::popFront(&list); 
    MyString temp = *t;
    delete t;
    return temp;
} 

StrList& StrList::operator+=(const StrList& l)
{
    if (this == &l) {
        perror("Functionality not yet implemented");
        exit(1);
    }

    reverse();

    Node *n = l.list.head;

    while (n) {
        MyString *t = (MyString *)n->data;
        MyString *ns = new MyString(*t);
        ::addFront(&list,ns);
        n = n->next;
    }

    reverse();
     
    return *this;

}

ostream& operator<<(ostream& os, const StrList& l) 
{
    Node *n = l.list.head;

    os << "{";
    while (n) {
        os << " " << *(MyString*)n->data;
        n = n->next;
    }
    os << " }";
    
    
    return os;
}

MyString& StrList::operator[](int i)
{
    int count=0;
    Node *n = list.head;
    MyString *temp = (MyString*)n->data;

    // Needs error checking 
    for(; count < size()+1; ++count){
        if(count == i) {
            temp = (MyString*)n->data;
            return *temp;
        }
        n=n->next;
    }
    
    return (MyString&)temp;
}

const MyString& StrList::operator[](int i) const
{
    return ((StrList&)*this)[i];
}

StrList operator+(const StrList& l1, const StrList& l2)
{
    StrList temp(l1);
    temp += l2;
    return temp;
}
