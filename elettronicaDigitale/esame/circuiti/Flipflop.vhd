library IEEE;
use IEEE.std_logic_1164.all;

entity FF is
    port (
        clk, preset, D : in std_logic;
        Q              : out std_logic;
    );
end FF;

architecture Behavioural of FF is
begin
    process (clk, preset) begin
        if    preset = '1'     then Q <= '1';
        elsif rising_edge(clk) then Q <= D;
        end if;  
    end process;
end Behavioural;