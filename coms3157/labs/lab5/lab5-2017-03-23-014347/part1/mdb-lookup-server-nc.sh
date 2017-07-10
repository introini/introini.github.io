#!/bin/sh
on_ctrl_c() {
    echo "Ignore Ctrl-C"
}

PORT=$1
PID=$$
PIPE=mypipe-$PID
mkfifo $PIPE 

cat $PIPE | nc -l $PORT | /home/jae/cs3157-pub/bin/mdb-lookup-cs3157 > $PIPE

rm $PIPE
