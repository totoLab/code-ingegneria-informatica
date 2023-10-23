## Statement condizionali
Non esiste uno statement if then else concorrente in vhdl. Come implementiamo, ad esempio, un multiplexer senza poter prendere decisioni in questo modo?

### `when-else`
Statement condizionale <b>sequenziale</b>. Supponendo 
```vhdl
S(1 downto 0) = S(1)S(0)
```
l'output del multiplexer sarà definito <b>in un process</b> così:
```vhdl
O <= I0 when S = "00" else
     I1 when S = "01" else
     I2 when S = "10" else
     I3;
```
NB: qua posso inserire espressioni logiche sui singoli bit S(1) = "0" and S(0) = "1"

### `with-select`
Statement condizionale <b>concorrente</b>
```vhdl
with S select
0 <= I0 when "00",
     I1 when "01",
     I2 when "10",
     I3 when others; 
```
NB: vanno specificati esplicitamente i valori di S

## Progetto 1
Progettare un sommatore carry-select a 16 bit unsigned (implementazione pura, vista a lezione). Successivamente scrivere un testbench con ingressi arbitrari per la simulazione (no simulazione esaustiva). Con relazione in pdf contenente i nomi dei componenti del gruppo. <br>
Consegna entro 06/11/2023. 

## Vivado
- RTL project
- Chip xc7z* (esempio xc7z020cpg400-3)