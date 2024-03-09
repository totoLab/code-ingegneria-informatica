SELECT C.nome
FROM Concessionaria C
WHERE C.nazione = 'DE'
AND C.codice NOT IN (
    SELECT A.codConcessionaria
    FROM Automobile A, Acquisto Q, Guidatore G
    WHERE Q.guidatore = G.CF
    AND Q.automobile = A.CodAutomobile
    AND YEAR(G.dataNascita) = 1998
);
