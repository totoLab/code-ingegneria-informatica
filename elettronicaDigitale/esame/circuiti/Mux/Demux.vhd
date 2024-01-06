entity Demux is 
    port(
        I, control : in std_logic;
        a, b       : out std_logic );
end Demux;

architecture Behavioural of Demux is
begin
    process begin
        if   control = '0' then a <= I;
        else b <= I;
        end if;
    end process;
end Behavioural;