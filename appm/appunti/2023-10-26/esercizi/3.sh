
if [ $# -ne 2 ]; then
    echo "Error"; exit 1;
fi

file=$1/$2
if [ -f $file ]; then
    echo ok
fi
echo $file