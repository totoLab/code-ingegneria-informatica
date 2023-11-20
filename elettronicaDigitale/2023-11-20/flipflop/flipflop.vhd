library IEEE;
use IEEE.std_logic_1164.all;

entity FF is
	port(
		D, clk, clr, pr: in std_logic;
		Q			   : out std_logic );
end FF;

architecture Behavioural of FF is
begin
	process (clk, clr, pr)
	begin
		if		clr = '0' 		  then Q <= '0'; -- attivo basso
		else if pr = '0'  		  then Q <= '1';
		else if falling_edge(clk) then Q <= D;
		end if;
	end process;
end Behavioural;