library IEEE;
use IEEE.std_logic_1164.all;

entity AddReg is
    generic(n: integer:= 16);
    port (
        clk, clear : in std_logic;
        a, b       : in std_logic_vector (n - 1 downto 0);
        Sum        : out std_logic_vector (n downto 0);
    );
end AddReg;

architecture Behavioural of AddReg is

    signal Ra, Rb, p, g, ISum : std_logic_vector (n downto 0);
    signal carry              : std_logic_vector (n + 1 downto 0);
    
begin
    -- alternativa
    -- p <= (a(n - 1) xor b(n - 1)) & (a xor b);
    -- g <= (a(n - 1) xor b(n - 1)) & (a and b);
    
    p <= Ra xor Rb;
    g <= Ra and Rb;

    carry(0) <= '0';
    carry(n + 1 downto 1) <= g or p and carry(n down 0);

    ISum <= p xor carry(n downto 0);

    process (clk, clear) begin
        if clear = '1' then
            Ra <= (others => '0');
            Rb <= (others => '0');
            Sum <= (others => '0');
        elsif rising_edge(clk) then
            Ra <= a(n - 1) & a;
            Rb <= b(n - 1) & b;
            Sum <= ISum;
        end if;
    end process;

end Behavioural;
