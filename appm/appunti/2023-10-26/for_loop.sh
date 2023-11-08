nomi="barbie pippo topolino"
for i in $nomi; do
    echo "Hi $i"
done

arguments=$@
for arg in $arguments; do 
    echo $arg
done