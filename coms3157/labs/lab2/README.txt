This file should contain:

  - Michael Introini 
  - mbi2105
  - lab2
  - descriptions:
  Part1: Working as intended.

  Part2: Working as intended.

VALGRIND: PART1
==9781== Memcheck, a memory error detector
==9781== Copyright (C) 2002-2015, and GNU GPL'd, by Julian Seward et al.
==9781== Using Valgrind-3.11.0 and LibVEX; rerun with -h for copyright info
==9781== Command: ./isort
==9781== 
original: 64 3 68 4 79 93 57 68 32 37 
ascending: 3 4 32 37 57 64 68 68 79 93 
descending: 93 79 68 68 64 57 37 32 4 3 
==9781== 
==9781== HEAP SUMMARY:
==9781==     in use at exit: 0 bytes in 0 blocks
==9781==   total heap usage: 5 allocs, 5 frees, 5,240 bytes allocated
==9781== 
==9781== All heap blocks were freed -- no leaks are possible
==9781== 
==9781== For counts of detected and suppressed errors, rerun with: -v
==9781== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)

VALGRIND: PART2
==10026== Memcheck, a memory error detector
==10026== Copyright (C) 2002-2015, and GNU GPL'd, by Julian Seward et al.
==10026== Using Valgrind-3.11.0 and LibVEX; rerun with -h for copyright info
==10026== Command: ./twecho one\ two\ three
==10026== 
one two three ONE TWO THREE
==10026== 
==10026== HEAP SUMMARY:
==10026==     in use at exit: 0 bytes in 0 blocks
==10026==   total heap usage: 4 allocs, 4 frees, 4,143 bytes allocated
==10026== 
==10026== All heap blocks were freed -- no leaks are possible
==10026== 
==10026== For counts of detected and suppressed errors, rerun with: -v
==10026== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)
