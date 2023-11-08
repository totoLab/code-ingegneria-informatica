entity RCA_v1 is
generic (n: integer:=4);
port(a, b: in bit_vector(n-1 downto 0);
     cin:in bit;
     s: out bit_vector(n downto 0));
end RCA_v1;
architecture AddArr of RCA_v1 is
signal ea, eb,  p, g: bit_vector(n downto 0);
signal carry: bit_vector(n+1 downto 0);
begin
  ea<=a(n-1) & a;
  eb<=b(n-1) & b;
  p<=ea xor eb;
  g<=ea and eb;
  carry(0)<=cin;
  carry(n+1 downto 1)<=g or (p and carry(n downto 0));
  s<=p xor carry(n downto 0);
end AddArr;