library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;

entity TB0 is
    generic (
        n: integer:=8,  -- lenght of I/O
        t: integer:=5); -- periodo??? di clock
end TB0;

architecture Sim_Reg8 of TB0 is
    component Reg8
        port(
            clk: in std_logic;
            D:   in std_logic_vector (n - 1 downto 0);
            Q:   out std_logic_vector (n - 1 downto 0) );
    end component;
    signal D, Q: std_logic_vector (n - 1 downto 0);
    signal clk:  std_logic;
begin
    circuit: Reg8 port map ( clk, D, Q );
    clock: process begin
        clk <= '0';
        wait for t ns;
        clk <= '1';
        wait for t ns;
    end process;

    dato: process begin
        wait for 3 ns;
        for i in 0 to 2**n - 1 loop
            D <= conv_std_logic_vector(i, n);
            wait for t * 2 ns;
        end loop;
    end process;
end Sim_Reg8;