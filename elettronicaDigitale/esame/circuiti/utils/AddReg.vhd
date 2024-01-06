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