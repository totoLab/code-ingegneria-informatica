if [ ! $# -eq 3 ] || [ ! -f $1 ]; then
    echo "Usage: $0 <file> <word_to_sub> <substitute_word>"
fi
file=$1
word_to_sub=$2
substitute_word=$3
output=""
for word in $(cat $file); do
    if [ $word = $word_to_sub ];
        output="$output $substitute_word"
    else
        output="$output $word"
    fi
done
# echo output > $file
echo $output