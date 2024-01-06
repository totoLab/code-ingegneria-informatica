entity Opt is
    port (
        A: in std_logic_vector(7 downto 0);
        C: in std_logic;
        O: out std_logic_vector(9 downto 0);
    );
end Opt;

architecture Behavioural of Opt is

    signal D: std_logic_vector(8 downto 0);
    signal Q: std_logic_vector(9 downto 0);

begin

    D(8 downto 1) <= A;
    D(0) <= '0';

    Q(9 downto 2) <= A;
    Q(1 downto 0) <= (others => '0');

    with C select
    O <= D when '0';
         Q when '1';

end Behavioural;