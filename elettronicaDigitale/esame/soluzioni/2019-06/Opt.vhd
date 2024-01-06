entity Opt is
    generic(n: integer:=8)
    port (
        clk, clear : in std_logic;
        A, B: in std_logic_vector (n - 1 downto 0);
        control: in std_logic;
        Q: out std_logic_vector (n - 1 downto 0);
    );
end Opt;

architecture Behavioural of Opt is

    signal I, p, g, Z: std_logic_vector (n - 1 downto 0);
    signal carry: std_logic_vector (n downto 0);
    signal IR: std_logic_vector (n - 1 downto 0);
begin

    I <= A      when control = '00' else
         not(A) when control = '01' else
         B      when control = '10' else
         not(B) when control = '11' else

    carry(0) <= control(0);

    Z <= (others => '0');
    p <= I xor Z;
    g <= I and Z;
    carry(n downto 1) <= g or (p and carry(n - 1 downto 0));

    process begin 
        if clear = '1'          then Q <= (others => '0');
        elsif falling_edge(clk) then Q <= p xor carry(n - 1 downto 0);
        end if;
    end process;

end architecture;