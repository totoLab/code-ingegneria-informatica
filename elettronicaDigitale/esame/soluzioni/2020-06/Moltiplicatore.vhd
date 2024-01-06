entity Moltiplicatore is
    port (
        clk : in std_logic;
        A: in std_logic_vector (3 downto 0);
        B: in std_logic_vector (1 downto 0);
        R: out std_logic_vector (6 downto 0) );
end Moltiplicatore;

architecture Behavioural of Moltiplicatore is

    signal p, g, IA, IB, Rout: std_logic_vector (6 downto 0);
    signal carry: std_logic_vector (7 downto 0);

begin

    IA <= (others => '0')        when B = '00' else
          A(3) & A(3) & A(3) & A when B = '01' else
          A(3) & A(3) & A & '0';
          
    IB <= A(3) & A(3) & A(3) & A when B = '11' else
          (others => '0');
          
    p <= IA xor IB;
    g <= IA and IB;
    carry(7 downto 1) <= g or (p and carry(6 downto 0));

    Rout <= p xor carry(6 downto 0);

    process (clk) begin
        if rising_edge(clk) then R <= Rout;
        end if;
    end process;

end architecture;