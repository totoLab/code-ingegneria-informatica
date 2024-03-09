SELECT C.codice
FROM Concessionaria C
WHERE (C.citta = 'TO' OR C.citta = 'NA') AND
    C.codConcessionaria IN (
        SELECT A.concessionaria
        FROM Automobile A
        WHERE A.codAutomobile IN (
            SELECT Q.automobile
            FROM Acquisto Q
            WHERE Q.guidatore IN (
                SELECT G.CodFiscale
                FROM Guidatore G
                WHERE YEAR(G.dateNascita) BETWEEN 1985 AND 1988
            )
            GROUP BY Q.automobile
            HAVING COUNT(Q.guidatore) >= 2
        )
        GROUP BY A.concessionaria
        HAVING COUNT(*) >= 2
    );
