Steps di progettazione:
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