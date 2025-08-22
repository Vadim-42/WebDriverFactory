#!/bin/bash

file=""
search=""

while [[ $# -gt 0 ]]; do
  case $1 in
    --file)
      file=$2
      shift
      ;;
    --search)
      search=$2
      shift
      ;;
    *)
      echo "Argument $1 not supported"
      exit 1
  esac
  shift
done

fullpath=$(realpath $file)
matches=$(grep -o "$search" "$file" | wc -l)


if [[ $matches -eq 0 ]]; then
  echo "Не найдено ни одного совпадения в файле $(realpath "$file")"
else
  echo "Количество найденных совпадений: $matches"
fi