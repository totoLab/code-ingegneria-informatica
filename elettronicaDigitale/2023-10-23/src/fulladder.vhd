entity fulladder is
    port(
        a, b, cin : in bit;
        cout, s   : out bit;
    );
end fulladder;

architecture Behavioural of fulladder is
    signal p, g : bit;
begin
    
    p <= a xor b;
    g <= a and b;
    -- cout <= g or (p and cin); implementabile con un multiplexer
    cout <= g when p = "0" else cin;
    s <= p xor cin;

end Behavioural;