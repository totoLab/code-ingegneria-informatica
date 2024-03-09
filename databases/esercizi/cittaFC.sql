select F.nome
from Fornitore F
where F.PIVA not in (
    select T.fornitore as PIVA
    from Fornitura T, Merce M, Marca C
    where T.merce = M.codice and
        M.marca = C.nome and 
        F.citta = C.citta
);