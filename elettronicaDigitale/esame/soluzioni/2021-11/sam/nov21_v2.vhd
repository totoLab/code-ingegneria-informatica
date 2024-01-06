-- Scrivere un codice che svolga le seguenti funzioni in base al segnale di controllo C:
-- Per C=00 A+1, C=01 -A-1, C=10 B+1, C=11 -B-1
-- Registri in entrata e in uscita, fronte di salita, clear e set attivi alti

Entity Appello is
    generic(n: integer := 4);
    port(A, B: in std_logic_vector(n-1 downto 0);
            C: in std_logic_vector(1 downto 0);
        clr, set : std_logic;
        Sum  : out std_logic_vector(n downto0));
end Appello;

Architecture behavioural of Appello is
    generic(n: integer := 4);

    signal IA, IB: std_logic_vector (n-1 downto 0);
    signal p,g, Isum: std_logic_vector (n downto 0);
    signal Carry: std_logic_vector(n+1 downto 0);
    
    IB <= '0000' when C(0) = 0 else
          '1111';
    
    p <= (IA xor IB)(7) & IA xor IB;
    g <= (IA and IB)(7) & IA and IB;

    carry(0) <= c(0)
    carry(n+1 downto 1) <= g or (p and carry(n downto 1));

    Isum <= p xor carry(n downto 0);


    process(clk, clear) begin
            if clear = '1' then 
                IA <=  (others => '0');
                Sum <= (others => '0');
                else;
            end if;
            if rising_edge(clk):
                if set = '1' then
                    IA  <= (others => '1');
                    Sum <= (others => '1');

                else
                    IA <= A        when C = '00' else
                          not(A)   when C = '01' else 
                          B        when C = '10' else
                          not(B);
                sum <= Isum;
                end if;
            end if;
    end process;
end behavioural;
    
