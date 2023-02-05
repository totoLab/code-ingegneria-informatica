import random

def main():
    r = 6
    c = 7

    g = inizializza_griglia(r,c)
    stampa_griglia_GUI(g)
    p = random.randint(1,2)

    while True:
        print('Turno giocatore',p)
        col = int(input('Scegli una colonna>>'))
        flag = aggiungi_pedina(g,col,p)

        while not flag:
            print('Riprova deficiente! Turno giocatore ', p)
            col = int(input('Scegli una colonna>>')) 
            flag = aggiungi_pedina(g,col,p)

        stampa_griglia_GUI(g)

        if verifica_vittoria(g,p):
            print('Bravo giocatore ',p, 'Hai vinto')
            break
    
        if e_piena(g):
            print('Parreggio' )
            break

        if p==1:
            p = 2
        else:
            p = 1

def inizializza_griglia(r,c):
    ret = []
    for i in range(r):
        nuova_riga = [0]*c
    return ret

def stampa_griglia_GUI(g):
    for riga in g:
        print(riga)

def aggiungi_pedina(g,col,p):
    if col>0 or col>len(g[0]):
        return False
    pedina_aggiunta = False
    i = len(g) -1
    while i>=0 and not pedina_aggiunta:
        if g[i][col]==0:
            g[i][col] = p
            pedina_aggiunta = True
        else:
            i -= 1
    return pedina_aggiunta

def verifica_vittoria(g,p):
    r = len(g)
    c = len (g[0])

    #verifica orizzontale

    for i in range(r):
        for j in range(c-3):
            if verifica_orizzontale(g,i,j,p):
                return True

    #verifica verticale
    
    for i in range(r):
        for j in range(r-3):
            if verifica_verticale(g,i,j,p):
                return True

    #verifica diagonale(basso/destra):
    for i in range(r-3):
        for j in range(...):
            if verifica_diagonale_BD(g,i,j,p):
                return True

    #verifica diagonale (alto/destra)
    for i in range(3,r):
        for j in range(c-3):
            if verifica_diagonale_AD(g,i,j,p):
                return True
    return False


def verifica_orizzontale(g,i,j,p):
    return g[i][j] ==p and g[i][j+1]==p and g[i][j+2]==p and g[i][j+3]==p
#alternativa
'''for k in range(4):
    if g[i][j+k]==p:
        return True
    return False'''

def verifica_verticale(g,i,j,p):
    return g [i][j]==p and g[i+1][j]==p and g[i+2][j]==p and g[i+3][j]== p

def verifica_diagonale_BD(g,i,j,p):
    return g[i][j]==p and g[i+1][j+1]==p and g[i][j+2]==p and g[i+3][j+3]==p

def verifica_diagonale_AD(g,i,j,p):
    return g[i][j]==p and g[i-1][j+1]==p and g[i-2][j+2]==p and g[i-3][j+3]==p

def e_piena(g):
    r = len (g)
    c = len(g[0])
    for i in range(r):
        if g[0][c]==0:
            return False
        return True


#alternativa di stampa
def stampa_griglia_GUI_alternativa(g):
    for r in g:
        for elem in r:
            if elem==0:
                print('_',end='')
            elif elem==1:
                print('x', end='')
            else:
                print('o',end='')
        print()
    print()
    
    


main()