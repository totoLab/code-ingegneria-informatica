select S.nome
from Stazione S
where S.codice in (
    select T.stazionePartenza
    from Tratta T
    where T.codice in (
        select A.trattaCoperta
        from Autobus A
        group by (A.trattaCoperta, A.tipologia)
        having count(*) >= 2
    )
    group by T.stazionePartenza
    having count(*) >= 2
);