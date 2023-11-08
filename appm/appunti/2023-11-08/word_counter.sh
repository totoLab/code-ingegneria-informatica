# count every instance of string in file
if [ ! $# -eq 2 ]; then
    echo "Usage: $0 <string> <filepath>"; exit
fi

str_to_find=$1
filepath=$2
counter=0
strings=$(cat $filepath)
for str in $strings; do
    if [ $str = $str_to_find ]; then
        let counter=$counter+1
    fi
done
echo $counter