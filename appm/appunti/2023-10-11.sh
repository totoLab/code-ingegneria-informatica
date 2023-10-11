# redirezione input-output
ls -l 1> file.txt
ls -l 2> error.txt
ls -l 1> file.txt 2> error.txt
ls -l 2> /dev/null
ls -l 2>&1 file.txt 
grep -n < file.txt
cat pippo1.txt pippo2.txt pippo3.txt > pippo_concatenati.txt
wc -l < studenti.txt
grep -c Giuseppe < studenti.txt
head -n 50 studenti.txt | grep -c Giuseppe
sort studenti.txt > studenti.txt
uniq studenti.txt | sort | wc -l
#TODO 13
ls -lhR ../../ > file.txt

# permessi sui file
#1. 644 -> 110 100 100 -> rw-r--r--
#2. 
# 2.1 rwx rwx rwx 
chmod a+rwx pippo.txt
chmod 777 pippo.txt
# 2.2 rw- rw- r-- 
chmod a+r,ug+w pippo.txt
chmod 664 pippo.txt
# 2.3 rwx r-- r-- 
chmod a+r,u+wx pippo.txt
chmod 744 pippo.txt

# 3. x implica l'accesso alla cartella, non posso nemmeno navigarci all'interno. r invece mi permette di navigarci ma senza leggere quali sono i file al suo interno