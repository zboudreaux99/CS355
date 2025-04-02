#!/bin/sh
/home/zeil/bin/gprolog --consult-file teaching.pro < $1 | sed -e '0,/^[|]/d' | grep '^[a-zA-Z]' | sort -u