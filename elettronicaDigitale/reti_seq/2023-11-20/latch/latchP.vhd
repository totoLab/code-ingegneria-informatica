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