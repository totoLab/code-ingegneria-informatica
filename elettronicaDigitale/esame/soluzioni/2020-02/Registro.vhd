entity Registro is
    generic(n: integer:=4)
    port (
        clk, clear : in std_logic;
        D: in std_logic_vector (n - 1 downto 0);
        Q: out std_logic_vector (n - 1 downto 0);
    );
end Registro;

architecture Behavioural of Registro is

    signal 

begin

    process (clk, clear) begin
        if    clear = ''       then Q <= (others => '0');
        elsif rising_edge(clk) then Q <= D;
        end if;
    end process;

end architecture;