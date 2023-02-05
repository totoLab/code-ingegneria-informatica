import generate as gen

def controlla_lista(lista_interi):
    uguali = True
    max_num = lista_interi[0], 0
    min_num = lista_interi[0], 0
    for i in range(1, len(lista_interi)):
        current = lista_interi[i]
        previous = lista_interi[i - 1]
        if uguali and current != previous:
            uguali = False

        if lista_interi[i] > max_num[0]:
            max_num = lista_interi[i], i
        elif lista_interi[i] < min_num[0]:
            min_num = lista_interi[i], i

    if uguali:
        return lista_interi[0]
    else:
        return max_num, min_num
    
length = int(input("Insert list length: "))
la_lista = []
la_lista = gen.list_gen(length)
#la_lista = gen.listSameElement(length)

output = controlla_lista(la_lista)
if isinstance(output, tuple):
    print("Lista: {}, max: {}, min: {}".format(la_lista, output[0], output[1]))
else:
    print("Lista: {}, same element: {}".format(la_lista, output))    
