entity AdderPM is
    generic(n: integer:=8);
    port (
        A: in std_logic_vector (n - 1 downto 0);
        C: in std_logic;
        O: out std_logic_vector (n downto 0);
    );
end AdderPM;

architecture Behavioural of AdderPM is

    signal p, g, B, IA: std_logic_vector (n downto 0);
    signal carry: std_logic_vector (n + 1 downto 0);

begin

    carry(0) <= C;
    IA <= A when C = '0' else
          not(A);

    B <= (0 => 1, others => '0')
    p <= IA xor B;
    g <= IA and B;

    carry(n + 1 downto 1) <= g or (p and carry(n downto 0));

    O <= p xor carry(n downto 0);

end architecture;