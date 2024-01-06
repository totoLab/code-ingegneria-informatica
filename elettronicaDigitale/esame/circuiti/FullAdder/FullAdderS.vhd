entity FullAdder is
    port(
        a, b, cin : in bit;
        cout, s   : out bit );
end FullAdder;

architecture Behavioural of FullAdder is
    component Mux
        port(
            a, b, control : in bit;
            o       : out bit );
    end component;
    signal p, g : bit;
begin
    p <= a xor b;
    g <= a and b;
    mux0: Mux port map( g, cin, p, cout );
    s <= p xor cin;
end Behavioural;