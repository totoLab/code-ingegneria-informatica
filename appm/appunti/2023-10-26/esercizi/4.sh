if [ $# -ne 4 ]; then
    echo "Usage $0 num1 num2 num3 num4"; exit 1
fi
let prodotto=$1*$2*$3*$4
echo prodotto = $prodotto