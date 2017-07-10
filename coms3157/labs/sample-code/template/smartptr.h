#ifndef __SMARTPTR_H__
#define __SMARTPTR_H__

template <class T>
class SmartPtr {

    private:

	T *ptr;  // the underlying pointer

	int *count;  // the reference count

    public:

	// constructor
	//
	// - p is assumed to point to an object created by "new T(...)"
	// - we hold the pointer and initialize ref count to 1.
	//
	//   note: explicit keyword
	//   note: default argument
	//
	explicit SmartPtr(T *p = 0) 
	{
	    ptr = p;
	    count = new int(1);
	}

	// copy constructor
	//
	// - copy the data members and increment the reference count 
	//
	//   note: member initialization syntax
	//
	SmartPtr(const SmartPtr<T>& sp)
	    : ptr(sp.ptr), count(sp.count)
	{
	    ++*count;
	}

	// destructor
	//
	// - delete the underlying object if this was the last owner
	//
	~SmartPtr()
	{
	    if (--*count == 0) {
		delete count;
		delete ptr;
	    }
	}

	// assignment operator
	//
	// - detach this SmartPtr from the underlying object and
	//   attach to the object that sp is pointing to.
	//
	SmartPtr<T>& operator=(const SmartPtr<T>& sp)
	{
	    if (this != &sp) {
		// first, detach.
		if (--*count == 0) {
		    delete count;
		    delete ptr;
		}
		// attach to the new object.
		ptr = sp.ptr;
		count = sp.count;
		++*count;
	    }
	    return *this;
	}

	// operator*() and operator->() make SmartPtr class behave
	// just like a regular pointer.

	T& operator*() const { return *ptr; }

	T* operator->() const { return ptr; }

	// access to the underlying pointer for those cases when you
	// need it.
	
	T* getPtr() const { return ptr; }

	// operator void*() makes "if (sp) ..." possible.
	
	operator void*() const { return ptr; }

};

#endif
