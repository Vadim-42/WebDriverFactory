#!/bin/bash

if [[ $# -ne 1 ]]; then
  echo "Введите параметр после файла!"
  exit 1
fi

(( $1 % 2 == 0 )) && half=$(( $1 / 2 )) || half=$(( $1 / 2 + 1 ))

mult=1
sum=0

for ((i=1; i<=$1-$half; i++)); do
  mult=$((mult * i))
done

for (( i = $half + 1; i<=$1; i++)); do
  sum=$((sum + i))
done

echo "mult: $mult"
echo "sum: $sum"