This file should contain:

  - Michael Introini
  - mbi2105
  - lab 4 
  - description:

  The Program is working as intended, and I have not seen any errors
  in the tests I've performed so far.
  
The description should indicate whether your solution for the part is
working or not.  You may also want to include anything else you would
like to communicate to the grader such as extra functionalities you
implemented or how you tried to fix your non-working code.

PART1:
95: {contigo} said {the mug of choice}


VALGRIND:
==1017== Memcheck, a memory error detector
==1017== Copyright (C) 2002-2015, and GNU GPL'd, by Julian Seward et al.
==1017== Using Valgrind-3.11.0 and LibVEX; rerun with -h for copyright info
==1017== Command: ./mdb-lookup my-mdb
==1017== 
lookup: mike
   2: {mike} said {testsasdasdad}

lookup: test
   1: {test} said {etertert}
   2: {mike} said {testsasdasdad}

lookup: 
   1: {test} said {etertert}
   2: {mike} said {testsasdasdad}

lookup: ==1017== 
==1017== HEAP SUMMARY:
==1017==     in use at exit: 0 bytes in 0 blocks
==1017==   total heap usage: 9 allocs, 9 frees, 6,848 bytes
allocated
==1017== 
==1017== All heap blocks were freed -- no leaks are possible
==1017== 
==1017== For counts of detected and suppressed errors, rerun
with: -v
==1017== ERROR SUMMARY: 0 errors from 0 contexts (suppressed:
0 from 0)

