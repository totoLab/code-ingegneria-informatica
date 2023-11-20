library IEEE;
use IEEE.std_logic_1164.all;

entity Reg8 is
	generic (n: integer:=8);
	port(
		clk, set: in std_logic;
		a: 		  in std_logic_vector (n - 1 downto 0);
		o: 		  out std_logic_vector (n - 1 downto 0) );
end Reg8;

architecture Behavioural of Reg8 is
begin
	process (clk)
	begin
		if rising_edge(clk) then
			if set = '0' then o <= (others => '1');
			else 			  o <= a;
			end if;
		end if;
	end process;
end Behavioural;