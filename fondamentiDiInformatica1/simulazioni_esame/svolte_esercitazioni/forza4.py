import random
import ulm

# setup
def inizializza_griglia(testing):
    if testing:
        griglia = [
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 1],
            [0, 0, 0, 0, 0, 1, 2],
            [0, 0, 0, 0, 1, 2, 2],
            [0, 0, 0, 1, 2, 1, 2]
        ]
        return griglia
    else:
        griglia = []
        for i in range(6):
            griglia.append([0] * 7)

        return griglia

# validity checks
def griglia_piena(griglia):
    colonne_piene = 0
    for elemento in griglia[0]:
        if elemento != 0:
            colonne_piene += 1

    if colonne_piene < 7:
        return False
    return True 

def colonna_piena(griglia, colonna):
    value = griglia[0][colonna] != 0
    return value

# input control
def scegli_colonna(griglia, giocatore):
    colonna = input(f"Inserire colonna per la prossima mossa del giocatore {giocatore} [1-7]: ")

    if not colonna.isnumeric():
        print("not a number")
        return scegli_colonna(griglia, giocatore)
    
    colonna = int(colonna)
    if colonna < 1 or colonna > 7:
        print("inserisci un numero tra 1 e 7")
        return scegli_colonna(griglia, giocatore)
    
    colonna -= 1 # to match real world counting
    if colonna_piena(griglia, colonna):
        print("la colonna selezionata è già piena")
        return scegli_colonna(griglia, giocatore)

    return colonna

# matrix editing
def prima_casella_libera(griglia, colonna):
    for riga in range(len(griglia)):
        if griglia[riga][colonna] != 0:
            return riga - 1

    return len(griglia) - 1

def aggiungi_pedina(griglia, giocatore):
    colonna = scegli_colonna(griglia, giocatore)
    riga_libera = prima_casella_libera(griglia, colonna)
    griglia[riga_libera][colonna] = giocatore

    return griglia

#exit conditions
def tutti_uguali(sequenza):
    if sequenza[0] != 0:
        primo = sequenza[0]    
    else:
        return False

    for i in range(1, len(sequenza)):
        if primo != sequenza[i]:
            return False
    
    return True

def verifica_orizzontale(griglia, giocatore):
    i = 0
    riga = len(griglia)
    while i < len(griglia): # skips impossible matching patterns
        if griglia[i][3] != 0:
            riga = i
            break

        i += 1
    
    if riga < len(griglia):
        #print("checking horizontal")
        for i in range(riga, len(griglia)):
            for j in range(len(griglia[0]) - 3):
                sequenza = griglia[i][j:j+4]
                if tutti_uguali(sequenza):
                    return True

    return False

def verifica_verticale(griglia, giocatore):
    i = 0
    colonna = len(griglia[0])
    while i < len(griglia[0]): # skips impossible matching patterns
        if griglia[2][i] != 0:
            colonna = i
            break

        i += 1

    if colonna < len(griglia[0]):
        #print("checking vertical")
        for j in range(colonna, len(griglia[0])):
            for i in range(len(griglia) - 3):
                sequenza = ulm.copia_colonna(griglia, j)[i:i + 4]
                if tutti_uguali(sequenza):
                    return True

    return False

def verifica_diagonale_sinistra(griglia, giocatore): # da sinistra verso il basso
    i = 0
    colonna = len(griglia[0])
    while i < len(griglia[0]) - 3: # skips impossible matching patterns
        if griglia[2][i] != 0:
            colonna = i
            break

        i += 1
    
    if colonna < len(griglia[0]):
        #print("checking left diagonal")
        for j in range(colonna, len(griglia[0]) - 3):
            for i in range(len(griglia) - 4):
                el1, el2, el3, el4 = griglia[i][j], griglia[i+1][j+1], griglia[i+2][j+2], griglia[i+3][j+3]
                sequenza = [el1, el2, el3, el4]
                if tutti_uguali(sequenza):
                    return True

    return False

def verifica_diagonale_destra(griglia, giocatore): # dal basso verso destra
    i = 3
    colonna = len(griglia[0])
    while i < len(griglia[0]): # skips impossible matching patterns
        if griglia[2][i] != 0:
            colonna = i
            break

        i += 1
    
    if colonna < len(griglia[0]):
        #print("checking right diagonal")
        for j in range(colonna, len(griglia[0])):
            for i in range(len(griglia) - 3):
                el1, el2, el3, el4 = griglia[i][j], griglia[i+1][j-1], griglia[i+2][j-2], griglia[i+3][j-3]
                sequenza = [el1, el2, el3, el4]
                if tutti_uguali(sequenza):
                    return True

    return False

def verifica_diagonale(griglia, giocatore):
    return verifica_diagonale_sinistra(griglia, giocatore) or verifica_diagonale_destra(griglia, giocatore)

def vittoria(griglia, giocatore):
    return verifica_orizzontale(griglia, giocatore) or verifica_verticale(griglia, giocatore) or verifica_diagonale(griglia, giocatore)

# algorithm
def game():
    griglia = inizializza_griglia(testing=False)
    ulm.stampa_matrice_incolonnata(griglia, 2)
    giocatore = random.randint(1, 2)
    while not griglia_piena(griglia):
        griglia = aggiungi_pedina(griglia, giocatore)

        ulm.stampa_matrice_incolonnata(griglia, 2)

        if vittoria(griglia, giocatore):
            return griglia, giocatore

        if giocatore == 1:
            giocatore = 2
        else: 
            giocatore = 1

    return griglia, 3

def main():
    griglia, giocatore = game()
    messaggio = f"ha vinto il giocatore {giocatore}"
    if giocatore == 3:
        messaggio = "pareggio"
    print(messaggio)
    return griglia, giocatore