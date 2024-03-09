select P.nome
from Parrucchiere P
where P.citta = 'CS' and
    P.codParrucchiere in (
        select S.Parrucchiere
        from Servizio S, Cliente C
        where S.Cliente = C.CF and
            year(S.data) = 2018 and
            C.CF in (
                select S1.Cliente
                from Servizio S1
                where S1.tipologia = 'taglio'
                group by S1.Cliente
                having count(*) = 2
            )
        group by S.Parrucchiere
        having count(S.Cliente) >= 3
    );