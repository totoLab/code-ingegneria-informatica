entity rca4_u is
    port(
        a, b : in bit_vector(3 downto 0);
        cin  : in bit;
        cout : out bit;
        s    : out bit_vector(3 downto 0);
    );
end rca4_u;

architecture Behavioural of rca4_u is
    component fulladder
        port(
            a, b, cin : in bit;
            cout, s   : out bit;
        );
    end component;
    signal c : bit_vector (4 down to 0);
begin
    c(0) <= cin;
    cout <= c(4);
    myFor: for i in 0 to 3 generate
        FA: fulladder port map( a(i), b(i), c(i), c(i+1), s(i) );
    end generate;
end Behavioural;