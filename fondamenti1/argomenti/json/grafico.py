import requests
import json
import covid
import matplotlib.pyplot as plt
# URL del repository: https://github.com/pcm-dpc/COVID-19
URL_dati_oggi_regioni = 'https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-regioni-latest.json'
URL_dati_completi_italia = 'https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-andamento-nazionale.json'
URL_dati_completi_regioni = 'https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-regioni.json'

response = requests.get(URL_dati_completi_italia)
dati = json.loads(response.text)

totale_casi_per_giorno = covid.casi_per_giorno(dati)
for giorno in totale_casi_per_giorno:
    print('Data:',giorno,'Casi totali:',totale_casi_per_giorno[giorno])