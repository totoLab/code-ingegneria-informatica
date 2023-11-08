if [ ! $# -eq 1 ] || [ ! -d $1 ]; then
    echo "Error, pass a directory as parameter"
fi
ext=""
read -p "Enter extension: " $ext
echo $ext ; exit

rm $1/*.$ext

function with_for() {
    directory=$1
    for file in $(ls $directory); do
        case $file in
            *.$ext ) echo "REMOVING $file";;
            *)
        esac
    done
}