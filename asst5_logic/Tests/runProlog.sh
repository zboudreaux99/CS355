#!/bin/sh
/home/zeil/bin/gprolog --consult-file teaching.pro < $1 | grep -v -i compil | sed 's/^[^A-Za-z]*//' | grep '^[A-Za-z]' | grep -v '^ms)' | grep -v '^no$' | sort -u

