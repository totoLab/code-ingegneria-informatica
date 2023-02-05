def bubble_sort(lista):
    n = len(lista)
    for i in range(n - 1):
        for j in range(n - 1, i, -1):
            if lista[j] < lista[j - 1]:
                temp = lista[j]
                lista[j] = lista[j - 1]
                lista[j - 1] = temp
        
def selection_sort(lista):
    n = len(lista)
    for i in range(n - 1):
        indice_min = i
        for j in range(i + 1, n):
            if lista[indice_min] > lista[j]:
                indice_min = j
        
        t = lista[i]
        lista[i] = lista[indice_min]
        lista[indice_min] = t

def insertion_sort(lista):
    n = len(lista)
    for i in range(1, n):
        valore = lista[i]
        j = i - 1
        while j >= 0 and lista[j] > valore:
            lista[j + 1] = lista[j]
            j -= 1

        lista[j + 1] = valore

lista = [3,7,1,3,9,4,6,10,13,8]
# selection_sort(lista)
# bubble_sort(lista)
# insert_sort(lista)
print(lista)