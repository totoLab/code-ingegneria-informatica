def isPalindrome(lista):
    for i in range(len(lista) // 2):
        if lista[i] != lista[len(lista) - i]:
            return False

    return True

lista = [10, 20, 30, 5, 30, 20, 10]
isPalindrome(lista)