# Circuiti

## Indice
### Combinatori
- Coder/Decoder
- Mux/Demux
- Adder (Half, Full, Ripple Carry, Carry Select, Carry Look-Ahead, Sottrattore)

### Sequenziali
- Latch (4 varianti)
- Flip Flop
- Registro

### utils
- registro doppio
- Somma tra registri
- Differenza tra registri

## Codice

### Combinatori generici

#### Coder/decoder 

#### Mux/Demux
- mux 
```vhdl
entity Mux is 
    port(
        a, b, control : in std_logic;
        o             : out std_logic );
end Mux;

architecture Behavioural of Mux is
begin
    process begin
        o <= a when control = '0' else b;
    end process;
end Behavioural;
```

- demux
```vhdl
entity Demux is 
    port(
        I, control : in std_logic;
        a, b       : out std_logic );
end Demux;

architecture Behavioural of Demux is
begin
    process begin
        if   control = '0' then a <= I;
        else b <= I;
        end if;
    end process;
end Behavioural;
```

### Sommatori
#### FullAdder
- behavioural
```vhdl
entity FullAdder is
    port(
        a, b, cin : in bit;
        cout, s   : out bit );
end FullAdder;

architecture Behavioural of FullAdder is
    signal p, g : bit;
begin
    p <= a xor b;
    g <= a and b;
    cout <= g or (p and cin)
    s <= p xor cin;
end Behavioural;
```
- structured
```vhdl
entity FullAdder is
    port(
        a, b, cin : in bit;
        cout, s   : out bit );
end FullAdder;

architecture Structured of FullAdder is
    component Mux
        port(
            a, b, control : in bit;
            o       : out bit );
    end component;
    signal p, g : bit;
begin
    p <= a xor b;
    g <= a and b;
    mux0: Mux port map( g, cin, p, cout );
    s <= p xor cin;
end Structured;
```

#### Ripple Carry Adder
- behavioural
``` vhdl
entity RCA_v1 is
generic (n: integer:=8);
    port(
        A, B: in std_logic_vector (n-1 downto 0);
        Cin : in std_logic;
        S   : out std_logic_vector (n downto 0) );
    end RCA_v1;

architecture Behavioural of RCA_v1 is
    signal ea, eb,  p, g: std_logic_vector (n downto 0);
    signal carry: std_logic_vector (n+1 downto 0);
begin
    ea <= a(n - 1) & a;
    eb <= b(n - 1) & b;
    p <= ea xor eb;
    g <= ea and eb;
    carry(0) <= cin;
    carry(n + 1 downto 1) <= g or (p and carry(n downto 0));
    s <= p xor carry(n downto 0);
end Behavioural;
```

- structured
``` vhdl
entity RCA4U_v2 is 
    generic (n: integer:=8);
    port(
        A, B: in bit_vector (n - 1 downto 0);
        Cin : in bit;
        Cout: out bit;
        S   : out bit_vector (n - 1 downto 0) );
end RCA4U_v2;

architecture Structured of RCA4U_v2 is
    component FullAdder
        port(
            A, B, Cin : in  std_logic;
            Cout, S   : out std_logic;
        );
    end component;
    signal C : std_logic_vector (n downto 0);
begin
    C(0) <= Cin;
    Cout <= C(n);

    inst : for i in 0 to n - 1 generate
        FA : FullAdder port map ( A(i), B(i), C(i), C(i+1), S(i) );
    end generate;
end Structured;
```

### Sequenziali

#### Latch
- latch P
```vhdl
library IEEE;
use IEEE.std_logic_1164.all;

entity LatchP is
	port(
		D, clk: in std_logic;
		Q	  : out std_logic );
end LatchP;

architecture Behavioural of LatchP is
begin
	process (clk, D)
	begin
		if (clk = '1') then Q <= D;
		end if;
	end process;
end Behavioural;
```
- latch PC
```vhdl 
library IEEE;
use IEEE.std_logic_1164.all;

entity LatchPc is
	port(
		D, clk, clr: in std_logic;
		Q		   : out std_logic );
end LatchPc;

architecture Behavioural of LatchPc is
begin
	process (clk, D, clr)
	begin
		if 		clr = '1' then Q <= '0'; -- prioritario sul clock (scelta progettuale)
		elsif clk = '1'	  then Q <= D;
		end if;
	end process;
end Behavioural;
```
- latch PCP
```vhdl
library IEEE;
use IEEE.std_logic_1164.all;

entity LatchPcp is
	port(
		D, clk, clr, pr: in std_logic;
		Q			   : out std_logic );
end LatchPcp;

architecture Behavioural of LatchPcp is
begin
	process (clk, D, clr, pr)
	begin
		if	  pr = '1'  then Q <= '1';
		elsif clr = '1' then Q <= '0';
		elsif clk = '1' then Q <= D;
		end if;
	end process;
end Behavioural;
```
- latch Psync
```vhdl
library IEEE;
use IEEE.std_logic_1164.all;

entity LatchRS is
	port(
		D, clk, reset, set: in std_logic;
		Q				  : out std_logic );
end LatchRS;

architecture Behavioural of LatchRS is
begin
	process (D, clk, reset, set)
	begin
		if clk = '1' then
			if    reset = '0' then Q <= '0';
			elsif set = '0'   then Q <= '1';
			else				   Q <= D;
			end if;
		end if;
	end process;
end Behavioural;
```

#### Flip Flop
```vhdl
library IEEE;
use IEEE.std_logic_1164.all;

entity FF is
    port (
        clk, preset, D : in std_logic;
        Q              : out std_logic );
end FF;

architecture Behavioural of FF is
begin
    process (clk, preset) begin
        if    preset = '1'     then Q <= '1';
        elsif rising_edge(clk) then Q <= D;
        end if;  
    end process;
end Behavioural;
```

#### Registro
```vhdl
library IEEE;
use IEEE.std_logic_1164.all;

entity Registro is
	generic (n: integer:=8);
	port(
		clk:    in std_logic;
        clear:  in std_logic;
		D:      in std_logic_vector (n - 1 downto 0);
		Q:      out std_logic_vector (n - 1 downto 0) );
end Registro;

architecture Behavioural of Registro is
begin
	process (clk, clear) begin
        if clear = '1'         then Q <= (others => '0');
        elsif rising_edge(clk) then
            Q <= D;
        end if;
    end process;
end Behavioural;
```

### utils

#### DueReg
```vhdl
library IEEE;
use IEEE.std_logic_1164.all;

entity DueRegistri is
    generic(n1:integer:=11;
            n2:integer:=32 )
    port(
        clk, clear: in std_logic;
        D1:     in std_logic_vector (n1 - 1 downto 0);
        Q1:     out std_logic_vector (n2 - 1 downto 0); 
        D2:     in std_logic_vector (n1 - 1 downto 0);
        Q2:     out std_logic_vector (n2 - 1 downto 0) 
    );
end DueRegistri;

architecture Behavioural of DueRegistri is
    component Registro
        generic(n:integer:=8)
        port(
            clk:    in std_logic;
            clear:  in std_logic;
            D:      in std_logic_vector (n - 1 downto 0);
            Q:      out std_logic_vector (n - 1 downto 0) );
    end component;
begin
    Reg1: Registro generic map(n1) port map (clk, clear, D1, Q1);
    Reg2: Registro generic map(n2) port map (clk, clear, D2, Q2);
end Behavioural;
```

#### AddReg
```vhdl
library IEEE;
use IEEE.std_logic_1164.all;

entity AddReg is
    generic(n:integer:=16)
    port (
        clk:   in std_logic;
        clear: in std_logic;
        A, B:  in std_logic_vector (n - 1 downto 0);
        Sum:   out std_logic_vector (n downto 0) );
end AddReg;

architecture Behavioural of AddReg is
    component Registro is
        generic (n: integer:=8);
        port(
            clk:    in std_logic;
            clear:  in std_logic;
            D:      in std_logic_vector (n - 1 downto 0);
            Q:      out std_logic_vector (n - 1 downto 0) );
    end component;
    signal Ra, Rb, P, G, ISum: std_logic_vector (n downto 0); -- predispongo l'estensione per il complemento a 2 aggiungendo un bit 
    signal c: std_logic_vector (n + 1 downto 0);
begin
    P <= Ra xor Rb;
    G <= Ra and Rb;
    
    -- estensione con segno
    Ra(n) <= Ra(n - 1)
    Rb(n) <= Rb(n - 1) 
    
    RegA: Registro generic map(n) port map ( clk, clear, A, Ra(n - 1 downto 0) );
    RegB: Registro generic map(n) port map ( clk, clear, B, Rb(n - 1 downto 0) );
    
    c(0) <= '0';
    c(n + 1 downto 1) <= G or (P and c(n downto 0)); 
    ISum <= P xor c(n downto 0); 
    RegS: Registro generic map(n + 1) port map (clk, clear, ISum, Sum);
end Behavioural;
```

#### SubReg
```vhdl
library IEEE;
use IEEE.std_logic_1164.all;

entity SubReg is
    generic(n: integer:= 16);
    port (
        clk, clear : in std_logic;
        a, b       : in std_logic_vector (n - 1 downto 0);
        Sum        : out std_logic_vector (n downto 0);
    );
end SubReg;

architecture Behavioural of SubReg is

    signal Ra, Rb, p, g, ISum : std_logic_vector (n downto 0);
    signal carry              : std_logic_vector (n + 1 downto 0);
    
begin
    -- alternativa
    -- p <= (a(n - 1) xor b(n - 1)) & (a xor b);
    -- g <= (a(n - 1) xor b(n - 1)) & (a and b);
    
    p <= Ra xor Rb;
    g <= Ra and Rb;

    carry(0) <= '0';
    carry(n + 1 downto 1) <= g or p and carry(n down 0);

    ISum <= p xor carry(n downto 0);

    process (clk, clear) begin
        if clear = '1' then
            Ra <= (others => '0');
            Rb <= (others => '0');
            Sum <= (others => '0');
        elsif rising_edge(clk) then
            Ra <= a(n - 1) & a;
            Rb <= b(n - 1) & b;
            Sum <= ISum;
        end if;
    end process;

end Behavioural;

```
