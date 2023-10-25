if [ $# -ne 2 ]; then
    echo -e "Passed $# arguments.\nUsage: $0 <num1> <num2>"
    exit 1
else
    let somma=$1+$2
    echo $1 + $2 = $somma
fi