library IEEE;
use IEEE.std_logic_1164.all;

entity Latch is
    port (
        clk, preset, D : in std_logic;
        Q              : out std_logic;
    );
end Latch;

architecture MyLatch of Latch is
begin
    process (clk, preset, D) begin
        if    preset = '1' then Q <= '1';
        elsif clk = '1'    then Q <= D;
        end if;        
    end process;
end MyLatch;