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
		if		pr = '1'  then Q <= '1';
		else if clr = '1' then Q <= '0';
		else if clk = '1' then Q <= D;
		end if;
	end process;
end Behavioural;