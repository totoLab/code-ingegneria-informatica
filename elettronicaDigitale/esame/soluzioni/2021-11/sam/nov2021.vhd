--Scrivere un codice che svolga le seguenti funzioni in base al segnale di controllo C:
--Per C=00 A+1, C=01 -A-1, C=10 B+1, C=11 -B-1

Entity Appello is
    generic(n: integer := 4);
    port(A, B: in std_logic_vector(n-1 downto 0);
            C: in std_logic_vector(1 downto 0);
        Sum  : out std_logic_vector(n downto0));
end Appello;

Architecture behavioural of Appello is
    generic(n: integer := 4);

    signal IA, IB: std_logic_vector (n-1 downto 0);
    signal p,g: std_logic_vector (n downto 0);
    signal Carry: std_logic_vector(n+1 downto 0);
    
    IA <= A      when C = '00' else
          not(A) when C = '01' else 
          B      when C = '10' else
          not(B);
          
    IB <= '0000' when C(0) = 0 else
          '1111';
    
    p <= (IA xor IB)(7) & IA xor IB;
    g <= (IA and IB)(7) & IA and IB;

    carry(0) <= c(0)
    carry(n+1 downto 1) <= g or (p and carry(n downto 1));

    sum <= p xor carry(n downto 0);

end behavioural;
    
