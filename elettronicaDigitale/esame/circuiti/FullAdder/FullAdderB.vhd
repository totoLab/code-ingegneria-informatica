entity FullAdder is
    port(
        a, b, cin : in bit;
        cout, s   : out bit );
end FullAdder;

architecture Behavioural of FullAdder is
    signal p, g : bit;
begin
    p <= a xor b;
    g <= a and b;
    cout <= g or (p and cin)
    s <= p xor cin;
end Behavioural;