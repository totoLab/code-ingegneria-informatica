library IEEE;
use IEEE.NUMERIC_bit.ALL;

entity TB2 is 
end TB2;

architecture Behavioural of TB2 is
    component rca4_u
        port(
            a, b : in bit_vector (3 downto 0);
            cin  : in bit;
            cout : out bit;
            s    : out bit_vector (3 downto 0);
        );
    end component;
    signal a, b, s   : bit_vector (3 down to 0);
    signal cin, cout : bit;
begin
    mycirc : rca4_u port map ( a, b, cin, cout, s ); 
    process begin __t=0
        for i in 0 to 15 loop
            a <= bit_vector(to_unsigned(i, 4));
            for j in 0 to 15 loop
                b <= bit_vector(to_unsigned(j, 4));
                wait for 5ns;
            end loop;
        end loop;
    end process;
end Behavioural;