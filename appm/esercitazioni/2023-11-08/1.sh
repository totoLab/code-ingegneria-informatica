if [ ! $# -eq 1 || ! -f $1 ]; then
    echo "Error, pass one file as parameter"
fi

db=$1
counter=0
for file in $db; do
    if [ -f $file ]; then
        counter=$counter+1
    fi
done