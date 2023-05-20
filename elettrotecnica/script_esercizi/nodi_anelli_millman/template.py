import math
import sys
import numpy as np

def todo():
    print("Not yet implemented")
    sys.exit()

# ---------- support methods ----------
mode = "default"

def pr(v, r):
    global mode
    if mode == "n":
        return math.pow(v, 2) / r
    elif mode == "a":
        return math.pow(v, 2) * r
    else:
        print("Parameter not found. Enter a or n as parameter.")
        sys.exit()

def pg(I, V):
    return V * I

def solve(M, N):
    global mode

    for i in range(len(M)):
        for j in range(len(M[0])):
            resistenze = M[i][j]
            if mode == "n": # usa conduttanze
                M[i][j] = sum([1/R if R != 0 else 0 for R in resistenze])
            elif mode == "a": # usa resistenze
                M[i][j] = sum(resistenze)
            else:
                print("Parameter not found. Enter a or n as parameter.")
                sys.exit()

    print(M)
    level = det(M)
    result = []
    for j in range(len(M[0])):
        result.append(
            cramer(M, N, j) / level
        )
    return result

def det(M):
    array = np.array(M)
    return np.linalg.det(array)


def cramer(M, N, j):
    T = [row[:] for row in M]

    for i in range(len(T)):
        T[i][j] = N[i][0]

    return det(T)


# ---------- dati del problema ----------
i1, i2 = 2, -1
v1, v2 = 3, -2
r1, r2, r3, r4 = 1, 2, 3, 1

# ---------- metodi ----------
def nodi():
    print("Metodo dei nodi:")

    # config
    global mode
    mode = "n"

    # potenziali noti
    ec = Vg
    ed = 0

    # potenziali rimanenti
    ea = Ig * r1 + ec
    eb = (ec/r3)/(1/r3 + 1/r4) 
    Pr1 = pr(ea - ec, r1)
    Pr2 = pr(ec, r2)
    Pr3 = pr(eb - ec, r3)
    Pr4 = pr(eb, r4)
    Pr = Pr1 + Pr2 + Pr3 + Pr4
    print(f"{Pr1=} + {Pr2=} + {Pr3=} + {Pr4=} = {Pr=}")

    Ivg = (ec - eb) / r3 + (ec - ea) / r1 + (ec - ed) / r2
    print(f"{Ivg=}")

    Vig = ea
    Pvg = pg(Ivg, Vg)
    Pig = pg(Ig, Vig)
    Pg = Pvg + Pig
    print(f"{Pig=} + {Pvg=} = {Pg=}")

    print(f"Error: {abs(Pr - Pg) / Pr}%")

def anelli():
    print("Metodo degli anelli:")

    # config
    global mode
    mode = "a"

    # correnti note
    i1 = -Ig

    # correnti rimanenti
    i3 = -Vg / (r3 + r4)
    i2 = (Vg + i1 * r2) / r2

    print(f"{i2=}, {i3=}")
    Pr1 = pr(i1, r1)
    Pr2 = pr(i1 - i2, r2)
    Pr3 = pr(i3, r3)
    Pr4 = pr(i3, r4)
    Pr = Pr1 + Pr2 + Pr3 + Pr4
    print(f"{Pr1=} + {Pr2=} + {Pr3=} + {Pr4=} = {Pr=}")

    Vig = i2 * r2 + - i1 * (r1 + r2)
    print(f"{Vig=}")

    Ivg = i2 - i3
    Pvg = pg(Ivg, Vg)
    Pig = pg(Ig, Vig)
    Pg = Pvg + Pig
    print(f"{Pig=} + {Pvg=} = {Pg=}")

    print(f"Error: {abs(Pr - Pg) / Pr}%")

def millman():
    print("Millman:")
    # config
    # R in serie con Vg
    Rs = [r3, r4]

    # R in parallelo con Ig !!! IGNORARLE !!!
    Rp = []

    # R sul ramo singolo
    Rc = []

    # Vg
    V = [v1, v2]

    # Ig
    I = [i1, i2]

    assert(len(Rs) == len(V))
    
    correnti_fake = [ V[i]/Rs[i] for i in range(len(V)) ]
    reciproci_rs =  [ 1/Rs[i] for i in range(len(Rs)) ]
    reciproci_rc =  [ 1/Rc[i] for i in range(len(Rc)) ]

    totale_correnti    = sum(correnti_fake) + sum(I)
    totale_conduttanze = sum(reciproci_rs) + sum(reciproci_rc) 

    assert(totale_conduttanze != 0)
    
    VM = totale_correnti / totale_conduttanze
    print(f"{VM=}")

# ---------- entrypoint ----------
if __name__ == "__main__":
    args = sys.argv
    assert(len(args) > 1)
    index = args[1]
    assert(index.isnumeric())
    index = int(index)

    if index == 0:
        nodi()
    elif index == 1:
        anelli()
    elif index == 2:
        millman()
    else:
        print("No functions with that index.")
    
