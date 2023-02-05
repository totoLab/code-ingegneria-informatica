def visualizza_listino(articoli, prezzi):
    for (articolo, prezzo) in zip(articoli,prezzi):
        print("{}: €{}".format(articolo, prezzo))

def visualizza_ordine(articoli, prezzi, ordine):
    totale_ordine = 0
    for (articolo, prezzo, quantita) in zip(articoli, prezzi, ordine):
        if quantita > 0:
            prezzo_totale = quantita * prezzo
            print("Articolo: {}, prezzo €{}, quantità: {}. Totale {}".format(articolo, prezzo, quantita, prezzo_totale))
            totale_ordine += prezzo_totale

    print("Totale ordine: €{}".format(totale_ordine))

def zipStringhe(stringa1, stringa2):
    for (elemento1, elemento2) in zip(stringa1, stringa2):
        print(elemento1, elemento2)


articoli = ["Pinza", "Martello", "Trapano", "Chiodo", "Vite"]
prezzi = [15, 10, 70, 0.1, 0.2]
#visualizza_listino(articoli, prezzi)

ordine = [1, 2, 1, 10, 10]
#visualizza_ordine(articoli, prezzi, ordine)