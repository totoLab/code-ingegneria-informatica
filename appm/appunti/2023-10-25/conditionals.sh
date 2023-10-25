#!/bin/bash
echo -n "Inserisci x"
read x
echo -n "Inserisci y"
read y

if [ $x -eq $y ]; then
    echo x is equal to y
elif [ $x -ge $y ]; then
    echo x is greater than y
elif [ $x -le $y ]; then
    echo x is less than y
else
    echo x is dumb
fi