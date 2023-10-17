### Ripple-Carry 4 bit unsigned

``` vhdl
entity RCA4_U is
    port(
        A3, A2, A1, A0 : in bit;
        B3, B2, B1, B0 : in bit;
        C0             : in bit;
        C4             : out bit;
        S3, S2, S1, S0 : out bit;
    );
end RCA4_U;
```

oppure per semplificare usiamo gli Array (notazioni decrementale e incrementale):
``` vhdl
A(3 downto 0)
A(0 to 3)
```
di solito usiamo la notazione decrementale, che è quella che usiamo abitualmente. <br>
L'accesso al bit avviene tramite indici: `A(0)` per accedere all'elemento in posizione 0 (cambia in base alla notazione). <br>
È possibile accedere a una porzione dell'array con `A(2 downto 1)`, ma bisogna farlo nello stesso modo in cui sono stati dichiarati. <br>

Per dichiarare gli array concateniamo "_vector" al tipo:
``` vhdl
entity RCA4_U is
    port(
        A, B : in bit_vector range (3 downto 0);
        C0   : in bit;
        C4   : out bit;
        S    : out bit_vector range (3 downto 0);
    );
end RCA4_U;
```

- #### Component:
Finora abbiamo descritto il circuito in modo "comportamentale" usando le equazioni logiche, ora passiamo a una descrizione strutturale. Riportiamo il componente FullAdder:
``` vhdl
entity FullAdder is
    port(
        A, B, Cin : in  bit;
        Cout, S   : out bit;
    );
end FullAdder;
```
per poi utilizzarlo nell'architettura come `component`:
```vhdl
architecture Es2 of RCA4_U is
    signal C1, C2, C3 : bit;
    component FullAdder
        port(
            A, B, Cin : in  bit;
            Cout, S   : out bit;
        );
    end component;
begin
    -- TODO
end Es2
```
NB: il richiamo dell'entity FullAdder come component di Es2 è posizionale, parametri da riportare in ordine.

- #### Istanziazione:
Creazione di moduli FullAdder con passaggio dei paramentri (sempre in ordine):
```vhdl
architecture Es2 of RCA4_U is
    signal C1, C2, C3 : bit;
    component FullAdder
        port(
            A, B, Cin : in  bit;
            Cout, S   : out bit;
        );
    end component;
begin
    FA0 : FullAdder port map ( A(0), B(0), C0, C1, S(0) );
    FA1 : FullAdder port map ( A(1), B(1), C1, C2, S(1) );
    FA2 : FullAdder port map ( A(2), B(2), C2, C3, S(2) );
    FA3 : FullAdder port map ( A(3), B(3), C3, C4, S(3) );
end Es2
```
<b>OSS</b>: la presenza dello stesso signal (ad esempio C1 in FA0 ed FA1) implica un collegamento tra i due component.

#### Sintassi source files
`src/FullAdder.vhd`:
``` vhdl
entity FullAdder is
    -- ...
end FullAdder;

architecture Es1 of FullAdder is
    -- ...
begin
    -- ...
end Es1
```
`src/RCA4U.vhd`:
``` vhdl
entity RCA4_U is
    -- ...
end RCA4_U;

architecture Es2 of RCA4_U is
    -- ...
begin
    -- ...
end Es2
```
NB: l'associazione entity architecture è 1:1, per definire funzioni diverse sulla stessa entity va duplicata e rinominata in un altro file.

#### Compattazione dei FullAdder
`FAi : FullAdder port map ( A(i), B(i), C(i), C(i+1), S(i) );` con for di tipo concurrent. 
```vhdl
entity RCA4_U2 is 
    port(
        A, B : in bit_vector (3 downto 0);
        Cin  : in bit;
        Cout : out bit;
        S : out bit_vector (3 downto 0);
    );
end RCA4_U2

architecture Es3 of RCA4_U2 is
    component FullAdder
        port(
            A, B, Cin : in  bit;
            Cout, S   : out bit;
        );
    end component;
    signal C : bit_vector (4 downto 0);
begin
    C(0) <= Cin; -- l'ingresso del circuito entra nell'array con Cin
    Cout <= C(4); -- il bit dell'array esce dal circuito tramite Cout

    -- label for var in min to max generate
    --     ...
    inst : for i in 0 to 3 generate
        FA : FullAdder port map ( A(i), B(i), C(i), C(i+1), S(i) );
        -- la differenziazione tra i FullAdder avviene a RunTime -> FA_i
    end generate
    
end Es3
```