entity Adder_CLA is 
    generic(n: integer:= 4);
    port(
        A, B: in std_logic_vector(n - 1 downto 0);
        Cin : in std_logic;
        S   : out std_logic_vector(n downto 0);
    );
end Adder_CLA;

architecture Behavioural of Adder_CLA is

    signal P : std_logic_vector (n-1 downto 0);
    signal G : std_logic_vector (n-1 downto 0);
    signal C : std_logic_vector (n downto 0);

begin

    P <= A xor B;
    G <= A and B;
    C(0) <= '0';

    for i in 1 to n-1 generate
        C(i) <= G(i-1) or (P(i-1) and C(i-1));
    end generate;

    S <= P xor C;
    
end architecture;