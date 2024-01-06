-- sommatore che fa A+1 o A + 2 in base a C da un bit
-- e salva il risultato in un registro a fronte di discesa

entity Adder12 is
    generic(n: integer:=8);
    port (
        clk, C : in std_logic;
        A: in std_logic_vector (n - 1 downto 0);
        O: out std_logic_vector (n downto 0) );
end Adder12;

architecture Behavioural of Adder12 is

    signal B: std_logic_vector (n - 1 downto 0);
    signal IA, IB, p, g: std_logic_vector (n downto 0);
    signal carry: std_logic_vector (n + 1 downto 0);

begin

    B <= (0 => '1', others => '0');
    IA <= A(n - 1) & A;
    IA <= B(n - 1) & B;

    p <= IA xor IB;
    g <= IA and IB;

    carry(0) <= C; -- if 0 A+1, if 1 (A+1)+1
    carry(n + 1 downto 1) <= g or (p and carry(n downto 0));

    process (clk) begin
        if falling_edge(clk) then 
            O <= p xor carry(n downto 0);
        end if;
    end process;


end architecture;