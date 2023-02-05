import ulm
import random 
import verifica_vittoria

def inizializza_griglia(nr, nc):
    griglia = []
    for i in range(nr):
        griglia.append([0] * nc)
    
    return griglia

def aggiungi_pedina(griglia, player):
    colonna = int(input("Scegli colonna: "))
    if colonna not in list(range(len(griglia[0]))) or griglia[0][colonna] != 0:
        print("Mossa non valida, riprova.")
        return aggiungi_pedina(griglia, player)
    else:

        if griglia[len(griglia) - 1][colonna] == 0:
            griglia
        for i in range(len(griglia) - 1, -1, -1): # reverse loop to find the first usable spot
            if griglia[i][colonna] == 0:
                griglia[i][colonna] = player
                return griglia

def e_piena(griglia):
    nr = len(griglia)
    nc = len(griglia[0])
    for i in range(nr):
        for j in range(nc):
            if griglia[i][j] == 0:
                return False

    return True

def main():
    nr = 6
    nc = 7
    griglia = inizializza_griglia(nr, nc)
    ulm.stampa_matrice_incolonnata(griglia, 2)

    player = random.randint(1, 2)
    while True:
        print("Turno giocatore", player)
        griglia = aggiungi_pedina(griglia, player)
        
        ulm.stampa_matrice_incolonnata(griglia, 2)

        if verifica_vittoria.main(griglia, player):
            print("il giocatore", player, "ha vinto!")
            break

        if e_piena(griglia):
            print("pareggio!")
            break

        if player == 1:
            player = 2
        elif player == 2:
            player = 1

main()