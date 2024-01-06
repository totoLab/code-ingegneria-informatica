library IEEE;
use IEEE.std_logic_1164.all;

entity Registro is
	generic (n: integer:=8);
	port(
		clk:    in std_logic;
        clear:  in std_logic;
		D:      in std_logic_vector (n - 1 downto 0);
		Q:      out std_logic_vector (n - 1 downto 0) );
end Registro;

architecture Behavioural of Registro is
begin
	process (clk, clear) begin
        if clear = '1'         then Q <= (others => '0');
        elsif rising_edge(clk) then
            Q <= D;
        end if;
    end process;
end Behavioural;