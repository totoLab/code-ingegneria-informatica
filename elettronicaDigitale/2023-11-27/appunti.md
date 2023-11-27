## Steps di progettazione
``` python 
while not ok4:
    while not ok3:
        while not ok2:
            while not ok1:
                codice = descrizione_con_vhdl()
                ok1 = logic_simulation(codice) # circuito ideale

            synthesis_constraints = synthesis()
            ok2 = post-synthesis_simulation(constraints) # tiene conto delle caratteristiche delle porte (fan_in, fan_out, ritardi, ecc) 

        implementation_constraints = implementation()
        ok3 = post-implementation_simulation(implementation_constraints) # tiene conto dei ritardi dei collegamenti e della topologia del circuito

    ok4 = lab_simulation() # circuito reale in laboratorio
```

NB: "Set As Top" per testare una parte del progetto più in basso nella gerarchia 

## Progetto 2
Descrizione circuito che riceve in ingresso 3 input a n bit (parametrico) in complemento a 2: A, B, C, per calcolarne la somma A + B + C.
Il circuito dev'essere dotato di registri in ingresso e uscita, come intermediari tra i segnali A, B, C e il sommatore (analogamente per l'uscita).
I segnali in ingresso saranno campionati dai rising edge del clock tramite tali registri, per l'uscita verrà usati i falling_edge???.