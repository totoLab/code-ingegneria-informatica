create view AcqCitta (acquisto, automobile, guidatore, citta) as (
    select A.codAcquisto as acquisto, A.automobile as automobile, A.guidatore as guidatore, G.citta as citta
    from Acquisto A, Guidatore G
    where A.guidatore = G.CodiceFiscale
)

select C.nome
from Concessionaria C
where C.codice not in (
    select A.concessionaria
    from Automobile A
    where A.CodAutomobile in (
        select Q1.automobile
        from AcqCitta Q1, AcqCitta Q2
        where Q1.acquisto != Q2.acquisto AND
            Q1.automobile = Q2.automobile and
            Q1.citta != Q2.citta
    )
);

