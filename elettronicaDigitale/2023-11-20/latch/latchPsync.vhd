library IEEE;
use IEEE.std_logic_1164.all;

entity LatchRS is
	port(
		D, clk, reset, set: in std_logic;
		Q				  : out std_logic );
end LatchRS;

architecture Behavioural of LatchRS is
begin
	process (D, clk, reset, set)
	begin
		if clk = '1' then
			if 		reset = '0' then Q <= '0';
			else if set = '0'   then Q <= '1';
			else					 Q <= D;
			end if;
		end if;
	end process;
end Behavioural;