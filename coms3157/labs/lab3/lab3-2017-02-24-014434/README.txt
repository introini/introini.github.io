This file should contain:

  - Michael Introini
  - mbi2105
  - lab 3
  - Part 1: Working as per the requirements. 
  - Part 2: Working as per the requirements.
  Added a check for argument count. The program will exit if more than 1
  argument hasn't been passed to it.

---------------------------------------------------------------------
PART1:

==12501== Memcheck, a memory error detector
==12501== Copyright (C) 2002-2015, and GNU GPL'd, by Julian Seward et al.
==12501== Using Valgrind-3.11.0 and LibVEX; rerun with -h for copyright info
==12501== Command: ./mylist-test
==12501== 
testing addFront(): 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0 1.0 
testing flipSignDouble(): -9.0 -8.0 -7.0 -6.0 -5.0 -4.0 -3.0 -2.0 -1.0 
testing flipSignDouble() again: 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0 1.0 
testing findNode(): OK
popped 9.0, the rest is: [ 8.0 7.0 6.0 5.0 4.0 3.0 2.0 1.0 ]
popped 8.0, the rest is: [ 7.0 6.0 5.0 4.0 3.0 2.0 1.0 ]
popped 7.0, the rest is: [ 6.0 5.0 4.0 3.0 2.0 1.0 ]
popped 6.0, the rest is: [ 5.0 4.0 3.0 2.0 1.0 ]
popped 5.0, the rest is: [ 4.0 3.0 2.0 1.0 ]
popped 4.0, the rest is: [ 3.0 2.0 1.0 ]
popped 3.0, the rest is: [ 2.0 1.0 ]
popped 2.0, the rest is: [ 1.0 ]
popped 1.0, the rest is: [ ]
testing addAfter(): 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 
popped 1.0, and reversed the rest: [ 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0 ]
popped 9.0, and reversed the rest: [ 2.0 3.0 4.0 5.0 6.0 7.0 8.0 ]
popped 2.0, and reversed the rest: [ 8.0 7.0 6.0 5.0 4.0 3.0 ]
popped 8.0, and reversed the rest: [ 3.0 4.0 5.0 6.0 7.0 ]
popped 3.0, and reversed the rest: [ 7.0 6.0 5.0 4.0 ]
popped 7.0, and reversed the rest: [ 4.0 5.0 6.0 ]
popped 4.0, and reversed the rest: [ 6.0 5.0 ]
popped 6.0, and reversed the rest: [ 5.0 ]
popped 5.0, and reversed the rest: [ ]
==12501== 
==12501== HEAP SUMMARY:
==12501==     in use at exit: 0 bytes in 0 blocks
==12501==   total heap usage: 19 allocs, 19 frees, 4,384 bytes allocated
==12501== 
==12501== All heap blocks were freed -- no leaks are possible
==12501== 
==12501== For counts of detected and suppressed errors, rerun with: -v
==12501== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)


PART2:
==12985== Memcheck, a memory error detector
==12985== Copyright (C) 2002-2015, and GNU GPL'd, by Julian Seward et al.
==12985== Using Valgrind-3.11.0 and LibVEX; rerun with -h for copyright info
==12985== Command: ./revecho hello world dude
==12985== 
dude
world
hello

dude found
==12985== 
==12985== HEAP SUMMARY:
==12985==     in use at exit: 0 bytes in 0 blocks
==12985==   total heap usage: 4 allocs, 4 frees, 4,144 bytes allocated
==12985== 
==12985== All heap blocks were freed -- no leaks are possible
==12985== 
==12985== For counts of detected and suppressed errors, rerun with: -v
==12985== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)
