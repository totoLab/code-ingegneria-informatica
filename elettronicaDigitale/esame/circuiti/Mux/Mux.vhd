entity Mux is 
    port(
        a, b, control : in std_logic;
        o             : out std_logic );
end Mux;

architecture Behavioural of Mux is
begin
    process begin
        o <= a when control = '0' else b;
    end process;
end Behavioural;