def visualizza_listino(articoli, prezzi):
    for i in range(len(articoli)):
        articolo, prezzo = articoli[i], prezzi[i]
        print("{}: €{}".format(articolo, prezzo))

def visualizza_ordine(articoli, prezzi, ordine):
    totale_ordine = 0
    for i in range(len(articoli)):
        quantità = ordine[i]
        if quantità > 0:    
            articolo = articoli[i]
            prezzo = prezzi[i]
            prezzo_totale = quantità * prezzo
            print("Articolo: {}, prezzo €{}, quantità: {}. Totale {}".format(articolo, prezzo, quantità, prezzo_totale))
            totale_ordine += prezzo_totale

    print("Totale ordine: €{}".format(totale_ordine))

articoli = ["Pinza", "Martello", "Trapano", "Chiodo", "Vite"]
prezzi = [15, 10, 70, 0.1, 0.2]
#visualizza_listino(articoli, prezzi)

ordine = [1, 2, 1, 10, 10]
visualizza_ordine(articoli, prezzi, ordine)