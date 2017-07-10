This file should contain:

  - Michael Introini
  - mbi2105
  - lab 9
  - description:

  - Part 2: Working as per the requirements.

############################ PART 1(A) ###############################
 
BASIC4TRACE:(0x7ffddcb79a50)->MyString(const char *)    [1] 13,constructor,x
BASIC4TRACE:(0x7ffddcb79a60)->MyString(const char *)    [2] 14,constructor,y
BASIC4TRACE:(0x7ffddcb79a90)->MyString(const MyString&) [3] 5,copy constructor,b
BASIC4TRACE:(0x7ffddcb79a80)->MyString(const MyString&) [4] 5,copy constructor,a
BASIC4TRACE:(0x7ffddcb799f0)->MyString(const char *)    [5] 7,constructor,t
BASIC4TRACE:op+(const MyString&, const MyString&)       [6] Entering operator+
BASIC4TRACE:(0x7ffddcb79990)->MyString()                [7] 8,default constructor,temp
BASIC4TRACE:(0x7ffddcb79a00)->MyString(const MyString&) [8] 8,copy constructor, 
                                                            u1 from return temp
BASIC4TRACE:(0x7ffddcb79990)->~MyString()               [9] 8,destructor,temp
BASIC4TRACE:op+(const MyString&, const MyString&)       [10] Entering operator+
BASIC4TRACE:(0x7ffddcb79990)->MyString()                [11] 8,default constructor,temp 
BASIC4TRACE:(0x7ffddcb79a10)->MyString(const MyString&) [12] 8,copy constructor,
                                                             u2
BASIC4TRACE:(0x7ffddcb79990)->~MyString()               [13] 8,destructor,temp
BASIC4TRACE:(0x7ffddcb79aa0)->MyString(const MyString&) [14] 16,copy constructor,
                                                             u3 return from add
BASIC4TRACE:(0x7ffddcb79a10)->~MyString()               [15] 9,destructor,u2
BASIC4TRACE:(0x7ffddcb79a00)->~MyString()               [16] 9,destructor,u1
BASIC4TRACE:(0x7ffddcb799f0)->~MyString()               [17] 9,destructor,t
BASIC4TRACE:(0x7ffddcb79a70)->MyString(const MyString&) [18] 16,copy constructor,z
BASIC4TRACE:(0x7ffddcb79aa0)->~MyString()               [19] 9,destructor,u3
BASIC4TRACE:(0x7ffddcb79a80)->~MyString()               [20] 9,destructor,a
BASIC4TRACE:(0x7ffddcb79a90)->~MyString()               [21] 9,destructor,b
one and two                                             [22] cout << z << endl;
BASIC4TRACE:(0x7ffddcb79a70)->~MyString()               [23] 19,destructor,z
BASIC4TRACE:(0x7ffddcb79a60)->~MyString()               [24] 19,destructor,y
BASIC4TRACE:(0x7ffddcb79a50)->~MyString()               [25] 19,destructor,x

############################ PART 1(B) ###############################

Passing references to a and b remove the need for the copy constructor
to be called. This allows the program to reduce the amount of
memory allocated during each function call.


############################ PART 1(C) ###############################

Removing the flag allows our program to omit calling the copy constructor
when we need to use a temporary object to initiate a new MyString object.


BASIC4TRACE: (0x7ffe82ca1f30)->MyString(const char *)
BASIC4TRACE: (0x7ffe82ca1f40)->MyString(const char *)
BASIC4TRACE: (0x7ffe82ca1ee0)->MyString(const char *)
BASIC4TRACE: op+(const MyString&, const MyString&)
BASIC4TRACE: (0x7ffe82ca1ef0)->MyString()
BASIC4TRACE: op+(const MyString&, const MyString&)
BASIC4TRACE: (0x7ffe82ca1f50)->MyString()
BASIC4TRACE: (0x7ffe82ca1ef0)->~MyString()
BASIC4TRACE: (0x7ffe82ca1ee0)->~MyString()
one and two
BASIC4TRACE: (0x7ffe82ca1f50)->~MyString()
BASIC4TRACE: (0x7ffe82ca1f40)->~MyString()
BASIC4TRACE: (0x7ffe82ca1f30)->~MyString()
