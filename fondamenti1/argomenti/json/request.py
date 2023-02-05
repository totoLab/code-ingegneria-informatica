import json
import requests

URL = 'http://jsonplaceholder.typicode.com/todos'

response = requests.get(URL)
testo_pagina = response.text
dati = json.loads(testo_pagina)

for attivita in dati:
    utente = attivita['userId']
    completata = attivita['completed']
    if utente == 1 and not completata:
        titolo = attivita['title']
        print(titolo)