select T.merce, max(T.prezzo)
from Fornitura T
where T.merce in (
    select M.codice
    from Merce M, Marca C
    where M.merce = C.codice and
        C.citta = 'CS'        
) and T.fornitore in (
    select F.PIVA
    from Fornitore F
    where F.citta = 'RC'
)
group by T.merce;