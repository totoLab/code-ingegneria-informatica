entity Adder is
    port (
        A, B: in std_logic_vector (3 downto 0);
        clk, clear: in std_logic;
        S: out std_logic_vector (4 downto 0);
    );
end Adder;

architecture Behavioural of Adder is
    component Registro is
        port (
            clk, clear : in std_logic;
            D: in std_logic_vector (n - 1 downto 0);
            Q: out std_logic_vector (n - 1 downto 0) );
    end component;

    signal Ra, IB, Rb, p, g: std_logic_vector (4 downto 0);
    signal Rout: std_logic_vector (4 downto 0);
    signal c: std_logic_vector(5 downto 0);

begin

    RegA: Registro generic map (4) port map(clk, clear, A, Ra);
    RegB: Registro generic map (4) port map(clk, clear, B, Rb);

    -- -B in complemento a 2
    IB <= not(Rb);
    c(0) <= '1';

    p <= Ra xor IB;
    g <= Ra and IB;
    c(5 downto 1) <= g or (p and c(4 downto 0)); 
    
    O <= p xor c(4 downto 0);

    RegO: Registro generic map (5) port map(clk, clear, O, S);

end architecture;
