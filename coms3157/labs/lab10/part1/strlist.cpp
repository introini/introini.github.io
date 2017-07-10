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
    // Initialize and empty list, this could be inline
    ::initList(&list);
}

// Destructor
StrList::~StrList()
{
    // Walk the cpp list and delete each node
    Node *n = list.head;

    while (n) {
        delete (MyString *)n->data;
        n=n->next;
    }

    // remove all nodes from the c list
    ::removeAllNodes(&list);
}

// Copy Constructor
StrList::StrList(const StrList& l) 
{
    // We initialize a new list
    ::initList(&list);

    // Here we can reverse the list and 
    // add to the front of the list node
    // by node

    l.reverse();
    Node *n = l.list.head;
    
    while (n) {
        // Store a pointer to the data
        MyString *t = (MyString *)n->data;
        // allocate space on the head for the 
        // new data in our list.
        MyString *ns = new MyString(*t);
        // add the new data to our list
        ::addFront(&list,ns);
        n = n->next;
    }

    // Return the list to its original state
    l.reverse();
}

// Copy Assignment
StrList& StrList::operator=(const StrList& l)
{
    if (this == &l)
        return *this;

    // Here we will declare a pointer
    // to delete all nodes inside of our
    // "old list"
    Node *ns = list.head;

    // Delete all nodes in the old list here
    while (ns) {
        delete (MyString *)ns->data;
        ns=ns->next;
    }
    
    ::removeAllNodes(&list);
    ::initList(&list);

    // Now we can add nodes from the right
    // hand side like we did in the copy
    // constructor.

    l.reverse();
    Node *n = l.list.head;
    
    while (n) {
        MyString *t = (MyString *)n->data;
        MyString *ns = new MyString(*t);
        ::addFront(&list,ns);
        n = n->next;
    }

    l.reverse(); 

    // Return a reference to list.
    return *this;
}

// Walk the list and return its size
int StrList::size() const
{
    // This simply walks the list and
    // counts the amount of nodes are in it.
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
    // Here we'll heap alloc some space to
    // save a copy of MyString to our local list
    // then we'll pass it's address to addFront for
    // storage.
     MyString *t = new MyString(s);
     ::addFront(&list,t);
}

MyString StrList::popFront()
{
    // This function will allow us to 
    // save a reference to the node popped from
    // list. Then we can assign it to a temp MyString
    // and return it aftr deleting the pointer we created.
    MyString *t = (MyString *) ::popFront(&list); 
    MyString temp = *t;
    delete t;
    return temp;
} 

StrList& StrList::operator+=(const StrList& l)
{
    // This function follows the same principle
    // as the copy constructor, though it appends
    // to the left hand side, rather than creating it.
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
    // Walking the list here we can print out each
    // node side by side using an ostream&

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
