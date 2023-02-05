#verifica(CP,CM),  che  restituisce True se e  solo  se  ognuna  delle  auto  ha  percorso  un 
# numero  di  chilometri inferiore al massimo che può percorrere.

def verifica(CP, CM):
    for i in range(len(CP)):
        riga = CP[i]
        if somma_lista(riga, len(riga)) >= CM[i]:
            return False
    return True 

def somma_lista(lista, fino_a):
    somma = 0
    for i in range(fino_a):
        somma += lista[i]
    return somma

#assegna_auto(CP,CM,km,g), che
# a.verifica quali auto hanno disponibilità residua maggiore o uguale a km
# b.tra le auto di cui al punto a, sceglie quella con la massima disponibilità residua (in caso di ex-aequo, ne sceglie una qualsiasi)
# c.aggiorna la matrice CP aggiungendo
# ad essa le informazioni relative al fatto che l’auto scelta al punto b compirà km chilometri aggiuntivi il giorno g.
# Se  nessuna  delle  auto  ha  disponibilità  residua  maggiore  o  uguale  a km,
# la  funzione  non  effettua  nessuna operazionee restituisce -1, altrimenti restituisce il codice dell’auto assegnata.

def disponibilità_residua(CP, CM, i, fino_a):
    return CM[i] - somma_lista(CP[i], fino_a)

def assegna_auto(CP,CM,km,g):
    massima_disponibilita = 0
    macchina_massima_disponibilita = 0
    nessuna_valida = True
    for i in range(len(CP)):
        disponibilita_corrente = disponibilità_residua(CP, CM, i, g)
        if disponibilita_corrente >= km:
            if disponibilita_corrente > massima_disponibilita:
                massima_disponibilita = disponibilita_corrente
                macchina_massima_disponibilita = i
                nessuna_valida = False

    if nessuna_valida:
        return -1
    else:
        CP[macchina_massima_disponibilita][g] += km
        return macchina_massima_disponibilita