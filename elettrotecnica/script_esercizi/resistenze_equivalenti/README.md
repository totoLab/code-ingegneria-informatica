## Verifica esericizi resistenze equivalenti

🚧 work in progress: Semplice script per la verifica dei calcoli degli esercizi sulla resistenza equivalente.

### Usage

Il file template.om contiene la struttura tipica del sorgente:

- sezione dati: ogni riga contiene `nome = valore`, con sovrascrittura dei nomi ripetuti (in ordine)
- un simbolo `#` come divisore delle due sezioni
- sezione equazioni: ogni riga contiene `nome = equazione`, esempi di equazioni vengono forniti nel template; vengono valutate tutte le equazioni una alla volta, per poi stamparne il nome e il risultato.

### TODO

- [ ] aggiunta parentesi mancanti
- [ ] parser per valutazione ricorsiva
- [ ] riutilizzo delle equazioni valutate come valori di normali resistenze
