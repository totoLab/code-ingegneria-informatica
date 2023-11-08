if [ ! $# -eq 2 ] || [ ! -d $1 ] || [ ! -d $2 ]; then
    echo "Usage: $0 <src> <dest>"
fi

src=$1
dest=$2

for file in $src; do
    case $file
        *.java )
            cp $src/$file $dest
            echo "Backup up $file to $dest" ;;
        *      ) echo "$file, not a java file."
    esac
done