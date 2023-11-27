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