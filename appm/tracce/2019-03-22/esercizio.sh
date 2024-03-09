if [ $# -ne 3 ]; then
    echo "Usage: $0 <cartella> <intero A> <intero B>"; exit 1
fi

cartella=$1
A=$2
B=$3

reg=^[0-9]*$
if [[ ! -d $cartella ]]; then 
    echo "Il primo parametro non è una cartella"; exit 1
elif [[ ! $A =~ $reg ]]; then
    echo "Il secondo parametro non è un intero positivo"; exit 1
elif [[ ! $B =~ $reg ]]; then
    echo "Il terzo parametro non è un intero positivo"; exit 1
fi 

prodotto=0
i=0
while [[ $i -lt $B ]]; do
    let prodotto=prodotto+$A
    let i=i+1
done
echo "prodotto = $prodotto"

for file in $cartella/*; do
    filename="$(basename "$file")"
    echo $filename
    if [[ "$filename" == *.txt ]]; then
        echo "$prodotto" >> "$file" 
        new_filename="${filename%.*}.bak"
        mv "$file" "$cartella/$new_filename"
    fi
done