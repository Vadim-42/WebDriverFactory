#!/bin/bash

FILES=()
EXTENSION=""
REPLACEMENT=""


while [[ $# -gt 0 ]]; do
        case $1 in
                --file)
                        FILES+=("$2")
                        shift
                        shift
                        ;;
                --extension)
                        EXTENSION=$2
                        shift
                        shift
                        ;;
                --replacement)
                        REPLACEMENT=$2
                        shift
                        shift
                        ;;
                *)
                        echo "Argument $1 with value $2 not supported"
                        exit 1
        esac
done

for file in ${FILES[@]};
do
        filebasedir=$(dirname $file)
        filename=$(basename $file)

        if [[ "$filename" =~ $EXTENSION$ ]]; then
                new_filename="${filename%.$EXTENSION}.$REPLACEMENT"
                mv "${filebasedir}/${filename}" "${filebasedir}/${new_filename}"
                echo "${filebasedir}/${new_filename}"
        fi
done