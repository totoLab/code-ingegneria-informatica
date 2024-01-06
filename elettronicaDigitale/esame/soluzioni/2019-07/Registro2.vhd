entity Registro1 is
    port (
        clk, clear: in std_logic;
        D: in std_logic_vector (3 downto 0);
        Q: out std_logic_vector (3 downto 0) );
end Registro1;

architecture Behavioural of Registro1 is

begin

    process (clk, clear) begin
        if    clear = '1' then Q <= (others => '0');
        elsif clk = '1'   then Q <= D;
        end if;
    end process;

end architecture;