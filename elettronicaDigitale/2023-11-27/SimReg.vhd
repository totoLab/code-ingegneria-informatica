library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;

entity TB is 
    generic(n:integer:=8);
end TB;

architecture Behavioural of TB is
    component Registro
        port(
            clk:    in std_logic;
            clear:  in std_logic;
            D:      in std_logic_vector (n - 1 downto 0);
            Q:      out std_logic_vector (n - 1 downto 0) );
    end component;
    signal clk, clear: std_logic;
    signal D, Q: std_logic_vector (n - 1 downto 0)
    constant T: time:= 10 ns;
begin
    myReg: Registro port map (clk, clear, D, Q);
    process (clk, clear) begin
        -- t = 0
        clk <= '0';
        wait for T/2; -- duty cycle 50%
        clk <= '1';
        wait for T/2; -- t = T
    end process;

    process begin
        clear <= '1';
        wait until falling_edge(clk);
        clear <= '0';
        wait for 3 ns;
        for i in -2**(n - 1) to 2**(n - 1) - 1 loop
            D <= conv_std_logic_vector(i, n);
            wait for D; -- aggiornamento del valore
        end loop;
    end process;
end Behavioural;