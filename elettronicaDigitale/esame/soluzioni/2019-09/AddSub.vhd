entity AddSub is
    port (
        A, B: in std_logic_vector (3 downto 0);
        control: in std_logic;
        S   : out std_logic_vector (4 downto 0) );
end AddSub;

architecture Behavioural of AddSub is

    signal carry: std_logic_vector (5 downto 0);
    signal p, g: std_logic_vector (4 downto 0);
    signal IB: std_logic_vector (3 downto 0);

begin

    -- determines if second operand of A + IB is B or -B
    carry(0) <= control;
    process begin
        if control = '1' then IB <= not(B);
        else IB <= B;
        end if;
    end process;

    -- sum
    p <= A xor IB;
    g <= A and IB;
    carry(5 downto 1) <= g or (p and carry(4 downto 0));

    S <= p xor carry(4 downto 0);

end architecture;