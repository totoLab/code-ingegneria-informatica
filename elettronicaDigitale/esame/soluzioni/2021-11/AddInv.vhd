-- dato C il circuito esegue
-- C = 00 → A+1
--     01 → -A -1 = - (A + 1)
--     10 → B + 1
--     11 → -B -1 = - (B + 1)

entity AddInv is 
    generic(n: integer:=8);
    port(
        A, B: in std_logic_vector (n - 1 downto 0);
        C   : in std_logic_vector (1 downto 0);
        O   : out std_logic_vector (n downto 0) );
end AddInv;

architecture Behavioural of AddInv is
    component FullAdder is 
        port(
            A, B, C: in std_logic;
            Cout, S: out std_logic );
    end component;
    signal inA, inB : std_logic_vector(n - 1 downto 0);
    signal carry : std_logic_vector(n downto 0);
begin
    carry(0) <= '0';
    myFor: for i in 0 to n - 1 generate
        Adder: FullAdder port map (inA(i), inB(i), carry(i), carry(i + 1), O(i));
    end generate;

    inA <= A when C = "00" else
           not(A) when C = "01" else
           B when C = "10" else
           not(B);

    inB <= '00000001' when C = "00" else
           '00000001' when C = "10" else
           '11111111';

    
end architecture;