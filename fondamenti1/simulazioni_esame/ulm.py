import random

######## INIZIO FUNZIONI DI COSTRUZIONE ########

def costruisci_matrice(n_righe,n_colonne,valore):
    risultato = []
    for i in range(n_righe):
        riga = [valore] * n_colonne
        risultato.append(riga)
    return risultato

def costruisci_matrice_nulla(n_righe,n_colonne):
    return costruisci_matrice(n_righe,n_colonne,0)

def costruisci_matrice_quadrata_nulla(ordine):
    return costruisci_matrice_nulla(ordine,ordine)      
    
def costruisci_matrice_identita(ordine):
    risultato = costruisci_matrice_quadrata_nulla(ordine)
    for i in range(ordine):
        risultato[i][i] = 1
    return risultato

def costruisci_matrice_valori_casuali(n_righe,n_colonne,max_valore):
    risultato = costruisci_matrice_nulla(n_righe,n_colonne)
    for i in range(n_righe):
        for j in range(n_colonne):
            risultato[i][j] = random.randint(0,max_valore)
    return risultato
    
######## FINE FUNZIONI DI COSTRUZIONE ########

######## INIZIO FUNZIONI DI INPUT-OUTPUT ########

def leggi_lista_int_lunghezza(n):
    ret = []
    for i in range(n):
        v = int(input("Inserisci l'elemento di indice "+str(i)+":"))
        ret.append(v)
    return ret

def leggi_lista_int():
    n = int(input('Lunghezza:'))
    return leggi_lista_int_lunghezza(n)

def leggi_lista_float_lunghezza(n):
    ret = []
    for i in range(n):
        v = float(input("Inserisci l'elemento di indice "+str(i)+":"))
        ret.append(v)
    return ret

def leggi_lista_float():
    n = int(input('Lunghezza:'))
    return leggi_lista_float_lunghezza(n)

def leggi_matrice_int_dimensioni(nr,nc):
    matrice = []
    for i in range(nr):
        print('-- Riga '+str(i)+' --')
        riga = leggi_lista_int_lunghezza(nc)
        matrice.append(riga)
    return matrice

def leggi_matrice_int():
    r = int(input('Numero righe = '))
    c = int(input('Numero colonne = '))
    return leggi_matrice_int_dimensioni(r,c)

def leggi_matrice_float_dimensioni(nr,nc):
    matrice = []
    for i in range(nr):
        print('-- Riga '+str(i)+' --')
        riga = leggi_lista_float_lunghezza(nc)
        matrice.append(riga)
    return matrice

def leggi_matrice_float():
    r = int(input('Numero righe = '))
    c = int(input('Numero colonne = '))
    return leggi_matrice_float_dimensioni(r,c)

def stampa_matrice(m):
    for riga in m:
        print(riga)

def stampa_matrice_incolonnata(matrice,n_cifre):
    for riga in matrice:
        for elemento in riga:
            print(str(elemento).rjust(n_cifre+1), end='')
        print()
        
######## FINE FUNZIONI DI INPUT-OUTPUT ########

######## INIZIO FUNZIONI DI CALCOLO ########

def somma_riga(matrice,indice_riga):
    return sum(matrice[indice_riga])

def somma_colonna(matrice,indice_colonna):
    s = 0
    for i in range(len(matrice)):
        s += matrice[i][indice_colonna]
    return s

def somma_vettori(vettore_1,vettore_2):
    risultato = []
    for i in range(len(vettore_1)):
        risultato.append(vettore_1[i] + vettore_2[i])
    return risultato

def prodotto_vettore_scalare(vettore,scalare):
    risultato = []
    for valore in vettore:
        risultato.append(valore * scalare)
    return risultato

def prodotto_scalare(vettore_1,vettore_2):
    risultato = 0
    for i in range(len(vettore_1)):
        risultato += vettore_1[i] * vettore_2[i]
    return risultato

def prodotto_matrice_scalare(matrice,scalare):
    n_righe = len(matrice)
    n_colonne = len(matrice[0])
    risultato = costruisci_matrice_nulla(n_righe,n_colonne)
    for i in range(n_righe):
        for j in range(n_colonne):
            risultato[i][j] = matrice[i][j] * scalare
    return risultato

def prodotto_matrice_vettore(matrice,vettore):
    risultato = []
    for i in range(len(matrice)):
        risultato.append(prodotto_scalare(copia_riga(matrice,i),vettore))
    return risultato

def prodotto_matrici(A,B):
    n = len(A)
    p = len(B[0])
    R = costruisci_matrice_nulla(n,p)
    for i in range(n):
        for j in range(p):
            Ai = copia_riga(A,i)
            Bj = copia_colonna(B,j)
            prod = prodotto_scalare(Ai,Bj)
            R[i][j] = prod
    return R

def trasposta(M):
    n_righe_M = len(M)
    n_colonne_M = len(M[0])
    MT = costruisci_matrice_nulla(n_colonne_M,n_righe_M)
    for i in range(n_righe_M):
        for j in range(n_colonne_M):
            MT[j][i] = M[i][j]
    return MT

######## FINE FUNZIONI DI CALCOLO ########

######## INIZIO FUNZIONI DI COPIA E MODIFICA ########

def elimina_riga(matrice,indice_riga):
    del matrice[indice_riga]

def elimina_colonna(matrice,indice_colonna):
    for riga in matrice:
        del riga[indice_colonna]

def copia_riga(matrice,i):
    return matrice[i][:]

def copia_colonna(matrice,j):
    risultato = []
    for riga in matrice:
        risultato.append(riga[j])
    return risultato

def scambia_righe(matrice,i1,i2):
    t = matrice[i1]
    matrice[i1] = matrice[i2]
    matrice[i2] = t

def scambia_colonne(matrice,j1,j2):
    for riga in matrice:
        t = riga[j1]
        riga[j1] = riga[j2]
        riga[j2] = t
    
def copia_matrice(matrice):
    risultato = []
    for i in range(len(matrice)):
        risultato.append(copia_riga(matrice,i))
    return risultato

######## FINE FUNZIONI DI COPIA E MODIFICA ########

######## INIZIO FUNZIONI DI VERIFICA ########

def e_quadrata(matrice):
    return len(matrice) == len(matrice[0])

def e_nulla(matrice):
    n_righe = len(matrice)
    n_colonne = len(matrice[0])
    for i in range(n_righe):
        for j in range(n_colonne):
            if matrice[i][j] != 0:
                return False
    return True

def e_diagonale(matrice):
    if not e_quadrata(matrice):
        return False
    ordine = len(matrice)
    for i in range(ordine):
        for j in range(ordine):
            if i != j and matrice[i][j] != 0:
                return False
    return True

def e_scalare(matrice):
    if not e_diagonale(matrice):
        return False
    ordine = len(matrice)
    for i in range(ordine - 1):
        if matrice[i][i] != matrice[i + 1][i + 1]:
            return False
    return True

def e_identita(matrice):
    return e_scalare(matrice) and matrice[0][0] == 1

def matrici_uguali(matrice_1,matrice_2):
    if len(matrice_1) != len(matrice_2) or len(matrice_1[0]) != len(matrice_2[0]):
        return False
    for i in range(len(matrice_1)):
        for j in range(len(matrice_1[0])):
            if matrice_1[i][j] != matrice_2[i][j]:
                return False
    return True

######## FINE FUNZIONI DI VERIFICA ########
