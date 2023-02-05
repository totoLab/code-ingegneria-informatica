import generate as gen
def controlla_lista(lista):
    p = lista[0]
    i = 0
    for i in lista:
        if i != p:
            print("Il massimo è: ", max(lista))
            print("Il minimo è: ", min(lista))
            print("L'indice del massimo è: ", lista.index(max(lista)))
            print("L'indice del minimo è: ", lista.index(min(lista)))
            break
    return p

length = int(input("Insert list length: "))
lista = gen.list_gen(length)
print(controlla_lista(lista))