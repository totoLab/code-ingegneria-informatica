def istogramma(s):
    d = {}
    for carattere in s:
        if carattere not in d:
            d[carattere] = 1
        else:
            valore = d[carattere]
            valore += 1
            d[carattere] = valore

    return d

def moda(s):
    d = istogramma(s)
    c_max = ''
    n_max = 0
    for carattere in d:
        if d[carattere] > n_max:
            c_max = carattere
            n_max = d[carattere]
    
    return c_max

print(moda('abracadabra'))