if [ ! $# -eq 2 ] || [ ! -d $1 ] || [ ! -d $2 ]; then
    echo "Usage: $0 <src> <dest>"
fi

src=$1
dest=$2

for file in $src; do
    case $file
        *.java )
            head -n 50 $src/$file > $dest/$file
            echo "Backup up first 50 lines of $file to $dest" ;;
        *      ) echo "$file, not a java file."
    esac
done