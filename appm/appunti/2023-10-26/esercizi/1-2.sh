# esattamente 3 parametri
if [ $# -eq 3 ]; then exit 0;
# 2 parametri, il primo un file, il secondo una directory
if [ $# -ne 2 ]; then
    echo "Error"; exit 1;
else
    if [ ! [ -f $1 && -d $2 ] ]; then
        echo "$1 non è un file o $2 non è una directory"
    else 
        echo "Error"; exit 2;
    fi
fi