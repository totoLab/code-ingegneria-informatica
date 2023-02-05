 # funzionano solo su liste già ordinate
def ricerca_binaria(lista, valore):
    inizio = 0
    fine = len(lista) - 1
    while inizio <= fine:
        centro = (inizio + fine) // 2
        if lista[centro] == valore:
            return True
        elif lista[centro] < valore:
            inizio = centro + 1
        elif lista[centro] > valore:
            fine = centro - 1
    
    return False

def ricerca_binaria_ricorsiva(lista, valore):
    middle_index = len(lista) // 2
    if lista[middle_index] == valore:
        return True
    elif lista[middle_index] < valore:
        ricerca_binaria_ricorsiva(lista[middle_index + 1:], valore)
    elif lista[middle_index] > valore:
        ricerca_binaria_ricorsiva(lista[:middle_index - 1], valore)
    else:
        return False

lista = [1,2,3,5,7,8,10,12,15,16,18,20]
print(ricerca_binaria(lista, 12))
print(ricerca_binaria_ricorsiva(lista, 12))