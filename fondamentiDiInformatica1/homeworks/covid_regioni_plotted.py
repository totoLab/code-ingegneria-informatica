import json
import requests
import matplotlib.pyplot as plt
import numpy as np

def scarica_dati_regioni(url):
    response = requests.get(url)
    dati_regioni = json.loads(response.text)

    return dati_regioni

def genera_lista_regioni(regioni):
    lista_regioni = []
    for regione in regioni:
        nome_regione = regione['denominazione_regione']
        lista_regioni.append(nome_regione)

    return lista_regioni

def scegli_regione(lista_regioni, regioni):
    error = True
    i = 0
    while error:
        for i in range(len(lista_regioni)):
            nome_regione = regioni[i]['denominazione_regione']
            print(str(i + 1) + ')', nome_regione)
        
        scelta = int(input('Inserisci numero corrispondente alla regione: ')) - 1 # per l'estetica togliamo 1 dopo averlo stampato maggiorato prima

        if scelta in range(len(lista_regioni)):
            return scelta
        else:
            print("Nessuna corrispondenza, RIPROVA.")

def genera_dati_regioni(regioni):
    lista_totale_positivi = []
    for regione in regioni:
        totale_positivi = regione['totale_positivi']
        lista_totale_positivi.append(totale_positivi)

    return lista_totale_positivi


def grafico(lista_regioni, lista_totale_positivi, scelta):
    colors = ['b'] * len(lista_regioni)
    colors[scelta] = 'r'
 
    fig, ax = plt.subplots()
    np.random.seed(19680801)
    y_pos = np.arange(len(lista_regioni))
    ax.barh(y_pos, lista_totale_positivi, align='center', color=colors)
    ax.set_yticks(y_pos, labels=lista_regioni)

    ax.invert_yaxis()
    plt.xlabel('Regioni')
    plt.ylabel('Totale positivi')
    plt.title('Totale positivi per regione')
    plt.show()


def main(url):
    regioni = scarica_dati_regioni(url)
    while True:
        # scelta regione singola
        lista_regioni = genera_lista_regioni(regioni)
        scelta = scegli_regione(lista_regioni, regioni)

        regione = regioni[scelta]
        nome_regione = regione['denominazione_regione']
        print("\nLa regione scelta è ", nome_regione)

        totale_positivi = regione['totale_positivi']
        print("Il totale dei positivi in", nome_regione, "è ", totale_positivi)

        casi_testati = regione['casi_testati']
        percentuale_positivi_su_testati = totale_positivi / casi_testati * 100
        print("Percentuale positivi sui casi testati è:", round(percentuale_positivi_su_testati, 3))

        # positivi tutte le regioni
        lista_totale_positivi = genera_dati_regioni(regioni)

        grafico(lista_regioni, lista_totale_positivi, scelta)

        # fine loop
        input('Premere invio per continuare')

url = 'https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-regioni-latest.json'
main(url)