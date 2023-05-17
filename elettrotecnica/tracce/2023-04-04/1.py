import analisi as an
import sys

# dati
i1, i2 = 2, -1
v1, v2 = 3, -2
r1, r2, r3, r4 = 1, 2, 3, 1

def nodi():
    print("Metodo dei nodi:")

    # config
    an.mode = "n"

    # potenziali noti
    ec = Vg
    ed = 0

    # potenziali rimanenti
    ea = Ig * r1 + ec
    eb = (ec/r3)/(1/r3 + 1/r4) 
    Pr1 = an.pr(ea - ec, r1)
    Pr2 = an.pr(ec, r2)
    Pr3 = an.pr(eb - ec, r3)
    Pr4 = an.pr(eb, r4)
    Pr = Pr1 + Pr2 + Pr3 + Pr4
    print(f"{Pr1=} + {Pr2=} + {Pr3=} + {Pr4=} = {Pr=}")

    Ivg = (ec - eb) / r3 + (ec - ea) / r1 + (ec - ed) / r2
    print(f"{Ivg=}")

    Vig = ea
    Pvg = an.pg(Ivg, Vg)
    Pig = an.pg(Ig, Vig)
    Pg = Pvg + Pig
    print(f"{Pig=} + {Pvg=} = {Pg=}")

    print(f"Error: {abs(Pr - Pg) / Pr}%")

def anelli():
    print("Metodo degli anelli:")

    # config
    an.mode = "a"

    # correnti note
    i1 = -Ig

    # correnti rimanenti
    i3 = -Vg / (r3 + r4)
    i2 = (Vg + i1 * r2) / r2

    print(f"{i2=}, {i3=}")
    Pr1 = an.pr(i1, r1)
    Pr2 = an.pr(i1 - i2, r2)
    Pr3 = an.pr(i3, r3)
    Pr4 = an.pr(i3, r4)
    Pr = Pr1 + Pr2 + Pr3 + Pr4
    print(f"{Pr1=} + {Pr2=} + {Pr3=} + {Pr4=} = {Pr=}")

    Vig = i2 * r2 + - i1 * (r1 + r2)
    print(f"{Vig=}")

    Ivg = i2 - i3
    Pvg = an.pg(Ivg, Vg)
    Pig = an.pg(Ig, Vig)
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
    
