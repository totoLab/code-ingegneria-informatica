entity Opt is
    port (
        A: in std_logic_vector(7 downto 0);
        C: in std_logic;
        O: out std_logic_vector(9 downto 0);
    );
end Opt;

architecture Behavioural of Opt is

begin

    with C select
    O <= A(8) & A & '0' when '0';
         A & '00' when '1';

end Behavioural;