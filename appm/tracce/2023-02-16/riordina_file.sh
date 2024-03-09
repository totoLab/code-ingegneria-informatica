if [ $# -ne 3 ]; then
    echo "$0 <cartellaA> <cartellaB> <istruzioni.txt>"; exit 1
fi

cartellaA=$1
cartellaB=$2
instructions=$3

if [ ! -d $cartellaA ]; then
    echo "Il primo parametro non è una cartella"; exit 1
elif [ ! -d $cartellaB ]; then
    echo "Il secondo parametro non è una cartella"; exit 1
elif [ ! -f $instructions ]; then 
    echo "Il terzo parametro non è un file"; exit 1
fi

cat $instructions | while read entry; do
    name=$(echo $entry | cut -d ' ' -f 1)
    rows=$(echo $entry | cut -d ' ' -f 2)
    enst=$(echo $entry | cut -d ' ' -f 3)

    fullpath="$cartellaA/$name"
    if [ -f "$fullpath" ]; then
        if [ $enst == "inizio" ]; then
            head -n $rows > "$cartellaB/$name"
        elif [ $enst == "fine" ]
            tail -n $rows > "$cartellaB/$name"
        fi
    fi
    base="$(name%.*)"
    for file in "$cartellaA/$base.*"; do
        if [ $file != $name ]; then
            rm "$file"
        fi
    done
done