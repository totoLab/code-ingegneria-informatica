import json
import requests

def scegli_regione(d_regioni):
    lista = []
    for regione in d_regioni:
        nome = regione['denominazione_regione']
        lista.append(nome)

    for i in range(len(lista)):
        nome_regione = d_regioni[i]['denominazione_regione']
        print(i, nome_regione)
    
    scelta = int(input('input numero della regione: '))
    
    return scelta

def percentuale_positivi(regione, positivi):
    casi_testati = regione['casi_testati']
    percentuale = positivi / casi_testati * 100
    return percentuale

def dati_regioni(regioni, positivi):
    lista_totale_positivi = []
    for regione in regioni:
        positivi = regione['totale_positivi']
        lista_totale_positivi.append(positivi)

    return lista_totale_positivi    

def main():
    url = 'https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-regioni-latest.json'
    response = requests.get(url)
    dizionario_regioni = json.loads(response.text)

    scelta = scegli_regione(dizionario_regioni) # fa scegliere all'utente una regione dalla lista
    regione = dizionario_regioni[scelta]

    nome_regione = regione['denominazione_regione']
    positivi = regione['totale_positivi']
    print("la regione scelta è ", nome_regione, "con ", positivi, "positivi") # stampa il totale dei positivi di una regione


    percentuale = percentuale_positivi(regione, positivi)
    print("i positivi sui casi testati sono il ", percentuale, "%")

    input("Premere invio per vedere positivi di tutte le regioni") # positivi di tutte le regioni
    
    lista_regioni = []
    lista_positivi = []
    for regione in dizionario_regioni:
        nome = regione['denominazione_regione']
        lista_regioni.append(nome)
        numero_positivi = regione["totale_positivi"]
        lista_positivi.append(numero_positivi)

    for i in range(len(lista_positivi)):
        print(lista_regioni[i], lista_positivi[i])

main()