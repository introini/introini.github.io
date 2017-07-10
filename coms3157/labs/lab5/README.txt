This file should contain:

  - Michael Introini
  - mbi2105
  - lab 5 
  - description:

Working solution as per the lab requirements. 

PART1:
 1835 25963 25963 25963 ?           -1 Ss       0   0:00  \_ sshd: mbi2105 [priv]
25963 26531 25963 25963 ?           -1 S     1553   0:00  |   \_ sshd: mbi2105@pts/47
26531 26663 26663 26663 pts/47    8170 Ss    1553   0:00  |       \_ -bash
26663  8170  8170 26663 pts/47    8170 S+    1553   0:00  |           \_ ./mdb-lookup-server-nc-1 33553
 8170  8172  8170 26663 pts/47    8170 S+    1553   0:00  |               \_ /bin/sh ./mdb-lookup-server-nc.sh 33553
 8172  8177  8170 26663 pts/47    8170 S+    1553   0:00  |                   \_ cat mypipe-8172
 8172  8178  8170 26663 pts/47    8170 S+    1553   0:00  |                   \_ nc -l 33553
 8172  8179  8170 26663 pts/47    8170 S+    1553   0:00  |                   \_ /bin/sh /home/jae/cs3157-pub/bin/mdb-lookup-cs3157
 8179  8182  8170 26663 pts/47    8170 S+    1553   0:00  |                       \_ /home/jae/cs3157-pub/bin/mdb-lookup /home/jae/cs3157-pub/bin/mdb-cs3157

 FILES THAT ARE SHELL SCRIPTS:
mdb-lookup-server-nc.sh

