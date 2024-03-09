select distinct M1.marca
from Merce M1, Fornitura T1
where M1.codice = T1.merce and
    M1.marca not in (
        select M.marca 
        from Merce M
        where M.codice not in (
            select T.merce
            from Fornitura T, Fornitore F
            where T.fornitore = F.PIVA and
                F.citta = 'CS'
        )
);

