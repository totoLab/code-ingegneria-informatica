if [ ! $# -eq 1 ] || [ ! -d $1 ]; then
    echo "Pass a directory as parameter."
fi
directory=$1
for file in $(ls $directory); do
    abs_path="$directory/$file"
    lines=$(wc -l $abs_path | cut -d ' ' -f 1 ) # oppure $(cat $abs_path | wc -l )
    echo "$file $lines"
done