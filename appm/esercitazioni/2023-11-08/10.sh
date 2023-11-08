if [ ! $# -eq 2 ] || [ ! -d $1 ] || [ ! -d $2 ]; then
    echo "Usage: $0 <src> <dest>"
fi

src=$1
dest=$2

for file in $src; do
    case $file
        *.java )
            grep -v "//*\n" $src/$file > $dest/$file
            echo "Backup up $file to $dest without comments" ;;
        *      ) echo "$file, not a java file."
    esac
done