create view FornitureNo(fornitore, merce) as (
    select F.piva as fornitore, M.codice as merce
    from Fornitore F, Merce M, Marca, C
    where M.marca = C.nome and C.citta = 'CS'
    except
    select T.fornitore, T.merce
    from Fornitura T
)

select F.nome
from Fornitura T, Fornitore F
where T.fornitore = F.piva and
    T.merce not in (
        select M.codice
        from Merce M, Marca C
        where M.marca = C.nome and C.citta != 'CS'
    ) and T.fornitore not in (
        select FN.fornitore
        from FornitureNo FN); 