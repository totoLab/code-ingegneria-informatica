if [ ! $# -eq 1 ]; then
    echo "Usage $0 <filename>"
elif [ ! -f $1 ]; then
    echo "Not a valid file"
fi
filename=$1
case $filename in
*.java ) echo "Java file";;
*.sh   ) echo "Shell file";;
*.txt  ) echo "Text file";;
*      ) echo "Other type"
esac