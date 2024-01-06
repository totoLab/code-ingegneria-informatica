-- A (4 bit, 2 compl) in ingresso a registro
-- C = 0 → A+1
--     1 → -A -1

entity Add1Inv is
    port (
        A: in std_logic_vector (3 downto 0);
        C: in std_logic;
        O: out std_logic_vector (4 downto 0);
    );
end Add1Inv;

architecture Behavioural of Add1Inv is
    component FullAdder is
        generic(n: integer:=4);
        port (
            A, B: in std_logic_vector (n - 1 downto 0);
            S: out std_logic_vector (n downto 0);
        );
    end component;

    signal A1: std_logic_vector (4 downto 0);
    signal A2: std_logic_vector (5 downto 0);

begin

    Sum:   generic map(4) FullAdder port map (A, '0001', A1);

    Compl: generic map(5) FullAdder port map (not(A1), '00001', A2)

    O <= A1 when C = '0' else A2; 
    
end architecture;