import json 
import requests
import matplotlib.pyplot as plt
import numpy as np
import textwrap

def raccogli_dati(url):
    response = requests.get(url)
    dati = json.loads(response.text)
    return dati

def confronto_carceri(dati_carceri):
    dizionario_carceri = {}
    for carcere in dati_carceri:
        nome = carcere["casa circondariale"]
        if nome not in dizionario_carceri:
            dizionario_carceri[nome] = 0
        numero_detenuti = carcere['detenuti']
        if type(numero_detenuti) == int:  # a volte i detenuti sono 0 ma rappresentati come '', quindi non li conto
            dizionario_carceri[nome] += numero_detenuti

    return dizionario_carceri

def confronto_regioni(detenuti_regione):
    dizionario_regioni = {}
    for dati in detenuti_regione:
        regione = dati['detenuti_regione_nascita']
        if regione not in dizionario_regioni:
            dizionario_regioni[regione] = 0
        numero_detenuti = dati['detenuti']
        if type(numero_detenuti) == int: 
            dizionario_regioni[regione] += numero_detenuti

    del dizionario_regioni['Stato estero'] # nel dizionario delle regioni non serve questo dato
    return dizionario_regioni

def confronto_anni(dati_carceri):
    detenuti_per_anno = {}
    for dati in dati_carceri:
        anno = dati['anno_rilevamento_detenuti']
        if anno not in detenuti_per_anno:
            detenuti_per_anno[anno] = 0
        numero_detenuti = dati['detenuti']
        if type(numero_detenuti) == int: 
            detenuti_per_anno[anno] += numero_detenuti

    return detenuti_per_anno

def totale_detenuti(lista_dizionari):
    dizionario_totale = {}
    for dizionario in lista_dizionari:
        for chiave in dizionario:
            if chiave not in dizionario_totale:
                dizionario_totale[chiave] = 0
            dizionario_totale[chiave] += dizionario[chiave] # aggiungo per ogni dizionario il valore al totale

    return dizionario_totale

def crea_liste(dizionario):
    lista_chiavi = []
    lista_valori = []
    for chiave in dizionario:
        lista_chiavi.append(chiave)
        lista_valori.append(dizionario[chiave])

    return lista_chiavi, lista_valori #restituisco una tupla

def grafico(x, y, etichetta, titolo):
    np.arange(len(x)) #dice quanti elementi ci sono sull'asse x
    # x = [ '\n'.join(textwrap.wrap(l, 10)) for l in x ]
    plt.bar(x, y, label=etichetta)
    plt.xticks(x)
    plt.title(titolo)
    plt.show()

def main():
    url_detenuti_regione = 'https://dati.comune.milano.it/dataset/1e3554c8-2884-417c-952e-6f61830d0a6f/resource/a9c05070-cd8f-40d6-8fc2-9a554fd0a013/download/ds579_detenuti_regione_nascita_2010-20.json'
    url_detenuti_stranieri = 'https://dati.comune.milano.it/dataset/6af65aa1-e2c2-452b-b3d3-991dc92c7e44/resource/d796592e-384f-474b-b777-cbb775a9ca95/download/ds566_detenuti_stranieri.json'

    detenuti_regione = raccogli_dati(url_detenuti_regione)
    detenuti_stranieri = raccogli_dati(url_detenuti_stranieri)

    carcere_italiani = confronto_carceri(detenuti_regione)
    carcere_stranieri = confronto_carceri(detenuti_stranieri)

    numero_per_regione = confronto_regioni(detenuti_regione)

    anni_italiani = confronto_anni(detenuti_regione)
    anni_stranieri = confronto_anni(detenuti_stranieri)

    totale_per_carcere = totale_detenuti([carcere_italiani, carcere_stranieri])
    totale_per_anno = totale_detenuti([anni_italiani, anni_stranieri])

    carcere = crea_liste(totale_per_carcere)
    grafico(carcere[0], carcere[1], "Carceri", "Totale detenuti per carcere")
   
    regione = crea_liste(numero_per_regione)
    grafico(regione[0], regione[1], "Regioni", "Totale detenuti per regione")

    anno = crea_liste(totale_per_anno)
    grafico(anno[0], anno[1], "Anni", "Totale detenuti per anno")

main()