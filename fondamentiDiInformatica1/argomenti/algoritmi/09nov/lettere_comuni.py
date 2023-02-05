def lettere_comuni(lista):
    stringa = lista[0]
    risultato = []
    for lettera in stringa:
        if presente_in_tutte(lettera, lista):
            risultato.append(lettera)


def presente_in_tutte(lettera, lista):
    for stringa in lista:
        if lettera not in stringa:
            return False

        return True