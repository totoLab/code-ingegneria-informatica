library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;

entity TB0 is
    generic(n:integer:=16);
end TB0;

architecture Behavioural of TB0 is
    component AddReg is
        port (
            clk:   in std_logic;
            clear: in std_logic;
            A, B:  in std_logic_vector (n - 1 downto 0);
            Sum:   out std_logic_vector (n downto 0);
        );
    end component;
    
    signal A, B, std_logic_vector (n - 1 downto 0);
    signal Sum: std_logic_vector (n downto 0);
    signal clk, clear: std_logic;
    constant T time:= 10 ns; 

begin
    circ: AddReg port map (A,B, Sum, clk, clear);
    process begin
        -- t = 0
        clk <= '0';
        wait for T/2; -- duty cycle 50%
        clk <= '1';
        wait for T/2; -- t = T
    end process;

    process begin
        clear <= '0';
        wait for 3 ns;
        A <= (others => '1');
        B <= (others => '0');
        wait for T;
        B(0) <= '1';
        wait for T;
    end process;
end Behavioural;