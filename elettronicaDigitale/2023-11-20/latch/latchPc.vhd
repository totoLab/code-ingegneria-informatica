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
		if 		clr = '1' then Q <= '0'; -- prioritario sul clock
		elsif clk = '1'	  then Q <= D;
		end if;
	end process;
end Behavioural;