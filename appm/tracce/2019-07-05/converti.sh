file_testo=$1
output=$2

if [ $# -ne 2 ]; then
    echo "Usage: $0 <istruzioni.txt> <cartella_output>"; exit 1
else
    if [ ! -f $file_testo ]; then 
        echo "Il primo parametro non è un file."; exit 1
    elif [ ! -d $output ]; then
        echo "Il secondo parametro non è una directory."; exit 1
    fi
fi

triple=$(cat $file_testo)
counter=0
counter_mv=0
for tripla in $triple; do
    folder=$(echo $tripla | cut -f 1)
    txt=$(echo $tripla | cut -f 2)
    ext=$(echo $tripla | cut -f 3)
    input_file="$folder/$txt"
    if [ -f "$input_file" ]; then
        let counter=counter + 1
        if [ ! "$input_file" == *.$ext ]; then 
            counter_mv=$counter_mv + 1
            new_filename="${txt%.*}.$ext"
            cp $input_file $output/$new_filename 
        fi
    fi
done

echo "File rinominati $counter_mv, file inalterati $($counter-$counter_mv)"