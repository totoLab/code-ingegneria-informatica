let i=1
while [ $i -le 10 ]; do
    echo $i
    let i=$i+1
    sleep 1
done
echo "Counter is done!"