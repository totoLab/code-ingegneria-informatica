import forza4

def main():
    max_score = int(input("Decidi il massimo numero di punti da raggiungere per vincere: "))
    punti = [0, 0]
    game_number = 0
    while game_number < max_score * 2:
        if forza4.main() == 1:
            punti[0] += 1
        else:
            punti[1] += 1

        for i, punteggio in enumerate(punti):
            if punteggio == max_score:
                return f"Giocatore {i + 1} ha vinto DEFINITIVAMENTE."

    return "PAREGGIO. MA COME SI FA."

main()