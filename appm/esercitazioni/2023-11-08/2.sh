counter=0
for file in $(ls); do
    if [ -f $file ]; then
        counter=$counter+1
    fi
done