-- tracce in AR del 2023-11-10

-- 1. Le case madri nate tra il 1980 e il 2015 a cui afferiscono almeno due parrucchieri della città di Roma o Firenze, ciascuno dei quali ha almeno due clienti distinti di età compresa tra 18 e 25 anni.

create view ParrucchieriOk (Parrucchiere) as (
    select S1.Parrucchiere Parrucchiere
    from Servizio S1, Servizio S2, Cliente C1, Cliente C2
    where S1.Parrucchiere = S2.Parrucchiere
        and S1.Cliente != S2.Cliente
        and S1.Cliente = C1.CodiceFiscale
        and S2.Cliente = C2.CodiceFiscale
        and C1.eta >= 18 and C1.eta <= 25
        and C2.eta >= 18 and C2.eta <= 25
)

select C.CodCasaMadre
from CasaMadre C, Parrucchiere P1, Parrucchiere P2, ParrucchieriOk Ok
where C.CodCasaMadre = P1.CodCasaMadre and C.CodCasaMadre = P2.CodCasaMadre
    and P1.CodParrucchiere != P2.CodParrucchiere
    and C.AnnoNascita >= 1970 and C.AnnoNascita <= 2015
    and P1.CodParrucchiere in (
        select Parrucchiere as CodParrucchiere from Ok)
    and P2.CodParrucchiere in (
        select Parrucchiere as CodParrucchiere from Ok) 
    and (P1.citta = "Roma" or P1.citta = "Firenze")
    and (P2.citta = "Roma" or P2.citta = "Firenze");


-- 2. Il nome dei parrucchieri situati nella città di Roma che nell’anno 2018 non hanno mai effettuato un servizio di tipologia “colore” a un cliente di genere maschile.

-- traduzione 1:1 AR

create view ParrucchieriOk (Parrucchiere) as (
    select P.Parrucchiere Parrucchiere
    from Parrucchiere P
    where P.citta = "Roma"
    except
    select S.Parrucchiere
    from Servizio S, Cliente C
    where S.cliente = C.CodiceFiscale
        and S.tipologia = "colore"
        and C.genere = "m"
        and year S.data = 2018
)

select P.nome
from Parrucchiere P, ParrucchieriOk Ok
where P.CodParrucchiere = Ok.Parrucchiere;

-- versione not in

select P.nome
from Parrucchiere P
where P.CodParrucchiere not in (
    select S.Parrucchiere CodParrucchiere
    from Servizio S, Cliente C
    where S.Cliente = C.CodiceFiscale
        and S.tipologia = "colore"
        and year S.data = 2018
        and C.genere = "m"
    )
    and P.citta = "Roma";

-- versione not exists

select P.nome
from Parrucchiere P
where not exists (
    select S.Parrucchiere CodParrucchiere
    from Servizio S, Cliente C
    where S.Cliente = C.CodiceFiscale
        and S.tipologia = "colore"
        and year S.data = 2018
        and C.genere = "m"
        and S.Parrucchiere = P.CodParrucchiere
    )
    and P.citta = "Roma";

-- 5. Le coppie di clienti <C1, C2> di genere diverso tali che l’insieme delle tipologie di servizi richiesti da C1 nell’anno 2017 è lo stesso dell'insieme delle tipologie di servizi richiesti da C2 nello stesso anno

select C1.CodiceFiscale, C2.CodiceFiscale
from Cliente C1, Cliente C2
where C1.genere != C2.genere
    and not exists (
        select S1.Cliente
        from Servizio S1
        where S1.Cliente = C1.CodiceFiscale
            and year S1.data = 2017
            and S1.tipologia not in (
                select S2.tipologia
                from Servizio S2
                where S2.Cliente = C2.CodiceFiscale
                    and year S2.data = 2017
            )
    ) and not exists (
        select S1.Cliente
        from Servizio S1
        where S1.Cliente = C1.CodiceFiscale
            and year S1.data = 2017
            and S1.tipologia not in (
                select S2.tipologia
                from Servizio S2
                where S2.Cliente = C2.CodiceFiscale
                    and year S2.data = 2017
            )
    );