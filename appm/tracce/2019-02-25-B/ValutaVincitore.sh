if [ $# -ne 2 ]; then
    echo "Usage: $0 <cartellaCandidati> <FileVinc>"; exit 1
fi

cartellaCandidati=$1
fileVinc=$2

if [ ! -d cartellaCandidati ]; then
    echo "Il primo parametro non è una cartella"; exit 1
elif [ ! -f fileVinc ]; then
    echo "Il secondo parametro non è un file"; exit 1
fi

for file in $cartellaCandidati; do 
    name="${file%.*}"
    points=0
    for value in $file; do
        let points=points+value
    done
    echo "$name $points" >> $fileVinc
done

temp="$(sort -nr $fileVinc)"
echo "$temp" > $fileVinc