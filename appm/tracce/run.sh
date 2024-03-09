file=$1
force_recompile=$2
out="traccia"
if [ -f $file ]; then
    if [ ! -f $out ] || [ $file -nt $out ]; then 
        gcc -std=gnu99 $file -o $out 
    fi
else
    echo "No file ${file}. Select an existing file for input." && exit 1;
fi

./$out