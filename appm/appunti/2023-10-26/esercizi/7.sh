if [ $# -ne 2 ] ; then
    echo "$0 letter times"; exit 1;
fi

re='^[0-9]+$'
if [ $1 =~ $re ] ; then
    echo "$1 not a number"; exit 1;
fi

i=0
while [ $i -lt $2 ]; do
    echo $1
    let i=$i+1
done