#!/bin/sh

numreps=$1
message="$2"

re='^[0-9]+$'
if ! [[ $numreps =~ $re ]] ; then
   echo "error: parameter $numreps is Not a number" >&2; exit 1
fi

for i in $( eval echo {1..$numreps} )
do
  curl "http://localhost:8080/api/send?message="+$message+$i;
done
