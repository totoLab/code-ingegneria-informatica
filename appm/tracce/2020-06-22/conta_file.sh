if [[ $# -ne 1 ]]; then
    echo "Usage: $0 <cartelle.txt>"; exit 1
fi

indice=$1

if [[ ! -f $indice ]]; then
    echo "Il parametro $indice non è un file"; exit 1
fi

output="risultato.txt"

for "$folder" in "$(cat $indice)"; do
    count=0
    if [[ -d "$folder" ]]; then
        for file in "$folder/"*; do 
            if [[ -f file ]]; then
                let count=count+1
            fi
        done
        echo "$(basename "$folder"): $count" >> "$output"
    fi
done