if [[ $# -ne 3 ]]; then
    echo "Usage: $0 <src> <dest> <file>"; exit 1
fi

src=$1
dest=$2
canzoni=$3

if [[ ! -d $src ]]; then
    echo "$src non è una directory"; exit 1
elif [[ ! -d $dest ]]; then
    echo "$dest non è una directory"; exit 1
elif [[ ! -f $file ]]; then
    echo "$file non è un file"; exit 1
fi 

tot=0
copiate=0

mp3=0
aac=0
opus=0

cat $canzoni | while read line; do
    for file in $src/*; do
        let "tot=tot+1"
        case "$file" in
        *.mp3) let "mp3++" ;;
        *.aac) let "aac++" ;;
        *.opus) let "opus++" ;;
        *) ;;
        esac

        filename="$(basename $file)"
        if [[ $filename == $line* ]]; then
            cp "$file" "$dest" &&
            let copiate=$copiate+1
        fi
    done
done
no_copiate=0
let no_copiate=$tot-$copiate
echo "$copiate canzoni copiate, di cui:"
echo "- mp3: $mp3\n- aac: $aac\n- opus: $opus\n"
echo "$no_copiate canzoni non copiate"
