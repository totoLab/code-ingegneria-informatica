

##### VISUALIZZAZIONE DATI COMPLETI ITALIA #####

def casi_per_giorno(dati):
    totale_casi_per_giorno = {}
    for dati_giorno in dati:
        giorno = dati_giorno['data'][:10]
        totale_casi = dati_giorno['totale_casi']
        totale_casi_per_giorno[giorno] = totale_casi

    return totale_casi_per_giorno
    '''for giorno in totale_casi_per_giorno:
        print('Data:',giorno,'Casi totali:',totale_casi_per_giorno[giorno])'''