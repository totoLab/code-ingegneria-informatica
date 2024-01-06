entity FullAdder is 
    port(
        A, B, C: in std_logic;
        Cout, S: out std_logic );
end FullAdder;

architecture Behavioural of FullAdder is 
    signal p, g: std_logic;
begin
    p <= A xor B;
    g <= A and B;

    Cout <= g or (p and C);
    S <= p xor C;

end Behavioural;