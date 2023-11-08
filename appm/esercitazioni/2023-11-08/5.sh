files="file1.txt file2.java file3.sh"
counter=0
for file in $files; do
    if [ -f "./$file" ]; then
        counter=$counter+1
    fi
done
echo $counter