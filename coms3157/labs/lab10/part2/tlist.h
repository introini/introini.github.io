/* 
 * TList.h
 */

#ifndef __TLIST_H__
#define __TLIST_H__

#include <list>
#include <algorithm>
#include <iostream>

using namespace std;

template <typename T>
class TList;

template <typename T>
ostream& operator<<(ostream& os, const TList<T>& rhs);

template <typename T>
class TList {

    public:

        // isEmpty() function
        bool isEmpty() const { return l.empty(); }

        // returns the number of nodes in the list
        int size() const { return l.size(); }  

        // adds a string to the front of the list
        void addFront(const T& t) { l.push_front(t); }

        // Pops a string from the front of the list and returns it.
        // The result of popping from an empty list is undefined.
        T popFront();
       
        // reverse the elements in the list
        void reverse();

        // TODO: operator+=
        // The result of "sl += sl" is undefined.
        TList& operator+=(const TList<T>& rhs);

        
        // TODO: operator<<
        // Prints the content of the given list in the following
        // format: 
        //
        //     { one two three }
        // 
        // assuming you had the three strings ("one", "two", "three")
        // in the list.
        friend ostream& operator<< <T>(ostream& os, const TList<T>& rhs);

        // TODO: operator[] 
        // This function takes O(n) time.
        // The result of accessing beyond the last element is undefined.
	T& operator[](int i);

        // TODO: operator[] const
        // This function takes O(n) time.
        // The result of accessing beyond the last element is undefined.
	const T& operator[](int i) const;

    private:

        // This class contains the old C list structure as its single
        // data member.  Do NOT add any data member.

        list<T> l;
};

template <typename T>
void TList<T>::reverse()
{
    ::reverse(l.begin(), l.end());
}

template <typename T>
T TList<T>::popFront()
{
    T t = l.front();
    l.pop_front();
    return t;
}

template <typename T>
ostream& operator<<(ostream& os, const TList<T>& rhs)
{
    os << "{ ";
    for (auto p = rhs.l.begin(); p != rhs.l.end(); ++p)
        os << *p << " ";
    os << " }";
    return os;
}


template <typename T>
TList<T>& TList<T>::operator+=(const TList<T>& rhs)
{
    reverse();
    for (auto p = rhs.l.begin(); p != rhs.l.end(); ++p)
        addFront(*p);
    reverse();

    return *this;
}

template <typename T>
T& TList<T>::operator[](int i)
{
    int count = 0;
    for (auto p = l.begin(); p != l.end(); ++p,count++) 
        if ( count == i )
            return (T&)*p;
    return l.front();
} 

template <typename T>
const T& TList<T>::operator[](int i) const
{
    return ((TList<T>&)*this)[i];
}

template <typename T>
TList<T> operator+(const TList<T>& l1, const TList<T>& l2)
{
    TList<T> temp = l1;
    temp += l2;
    return temp;
}

#endif
