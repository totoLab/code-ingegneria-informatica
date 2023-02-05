import json
import requests

regioni = 'https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-regioni-latest.json'
italia = 'https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-andamento-nazionale.json'

URL = italia

response = requests.get(URL)
testo_pagina = response.text
dati = json.dumps(testo_pagina)

for regione in dati:
    nome_regione = regione['denominazione_regione'] 
    if nome_regione == 'Calabria':
        totale_ospedalizzati = regione['totale_ospedalizzati']
        print(totale_ospedalizzati)