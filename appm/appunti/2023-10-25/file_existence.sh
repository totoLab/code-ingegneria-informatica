directory=$1
file_name=$2

if [ $# -ne 2 ]; then
    echo -e "Passed $# arguments.\nUsage: $0 <dir> <file_name>"
    exit 1
elif [ ! -d $directory ]; then
    echo "$directory does not exists"
    exit 1
else
    cd $directory
    if [ ! -f $file_name ]; then
        echo "$file_name does not exists in $directory"
        exit 1
    else
        echo "$file_name exists in $directory"
    fi
fi