main_folder=$1
output=$2

if [ $# -ne 2 ]; then
    echo "Usage: $0 <cartella_sorgente> <cartella_destinazione>"; exit 1
else
    if [ ! -d $main_folder ]; then
        echo "Il primo parametro non è una cartella"; exit 1
    elif [ ! -d $output ]; then
        echo "Il secondo parametro non è una cartella"; exit 1
    fi
fi

for filefolder in $(ls $main_folder); do
    if [ -f $filefolder ]; then
        if [ $filefolder == *.exe ]; then
            new_file="${$filefolder%.exe}.sh"
            cp $main_folder/$filefolder $output/$new_file
        else 
            cp $main_folder/$filefolder $output/
        fi
    else if [ -d $filefolder ]; then
        mkdir 
        for filefolder2 in $(ls $main_folder/$filefolder); do
            if [ -f filefolder ]; then
                if [ filefolder == *.exe ]; then
                    new_file="${$filefolder2%.exe}.sh"
                    cp $main_folder/$filefolder/$filefolder2 $output/$new_file
                else 
                    cp $main_folder/$filefolder/$filefolder2 $output/$filefolder/
                fi
            else if [ -d $filefolder2 ]; then
                mkdir -p $output/$filefolder/$filefolder2
            fi
        done
    fi
done