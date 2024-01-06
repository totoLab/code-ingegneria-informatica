entity FullAdder is
    generic(n: integer:=4);
    port (
        A, B: in std_logic_vector (n - 1 downto 0);
        S: out std_logic_vector (n downto 0);
    );
end FullAdder;

architecture Behavioural of FullAdder is

    signal carry: std_logic_vector (n + 1 downto 0);
    signal p, g: std_logic_vector (n downto 0);

begin

    p <= A xor B;
    g <= A and B;
    carry(n + 1 downto 1) <= g or (p and carry(n downto 0))

    S <= p or carry(n downto 0);
    
end architecture;