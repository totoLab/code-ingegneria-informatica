entity RCA4U_v2 is 
    port(
        A, B : in bit_vector (3 downto 0);
        Cin  : in bit;
        Cout : out bit;
        S : out bit_vector (3 downto 0) );
end RCA4U_v2;

architecture rtl of RCA4U_v2 is
    component FullAdder
        port(
            A, B, Cin : in  bit;
            Cout, S   : out bit;
        );
    end component;
    signal C : bit_vector (4 downto 0);
begin
    C(0) <= Cin;
    Cout <= C(4);

    inst : for i in 0 to 3 generate
        FA : FullAdder port map ( A(i), B(i), C(i), C(i+1), S(i) );
    end generate;
    
end rtl;