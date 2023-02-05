def verifica_orizzontale(griglia, i, j, player):
    for k in range(4):
        if griglia[i][j + k] != player:
            return False

    return True

def verifica_verticale(griglia, i, j, player):
    for k in range(4):
        if griglia[i + k][j] != player:
            return False

    return True

def verifica_diagonale_mancina(griglia, i, j, player):
    for k in range(4):
        if griglia[i + k][j + k] != player:
            return False

    return True

def verifica_diagonale_destra(griglia, i, j, player):
    for k in range(4):
        if griglia[i + k][j - k] != player:
            return False

    return True

def main(griglia, player): # il metodo fa le verifiche solo negli spazi dove ha senso
    nr = len(griglia)
    nc = len(griglia[0])
    # verifica orizzontale
    for i in range(nr):
        for j in range(nc-3):
            if verifica_orizzontale(griglia, i, j, player):
                return True
    
    #verifica verticale
    for i in range(nr-3):
        for j in range(nc):
            if verifica_verticale(griglia, i, j, player):
                return True

    #verifica diagonale (primo in alto a sinistra-> ultimo in basso a destra - 3 posizioni diagonali)
    for i in range(nr-3):
        for j in range(nc-3):
            if verifica_diagonale_mancina(griglia, i, j, player):
                return True

    #verifica diagonale (il precedente specchiato)
    for i in range(nr - 3):
        for j in range(3, nc):
            if verifica_diagonale_destra(griglia, i, j, player):
                return True
    
    return False