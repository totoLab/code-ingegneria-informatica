create view MarcheNo (marca, guidatore) as (
    select A.marca as marca, G.CF as guidatore
    from Automobile A, Guidatore G
    except
    select Q.automobile, Q.guidatore
    from Acquisto Q
)

create view CoppieNo (g1, g2) as (
select Q.guidatore as g1, M.guidatore as g2
from Automobile A, Acquisto Q, MarcheNo M
where A.CodAutomobile = Q.automobile and
    A.marca = MarcheNo.marca and
    Q.guidatore != M.guidatore
)

select G1.CF, G2.CF
from Guidatore G1, Guidatore G2
where G1.CF != G2.CF and G1.citta != G2.citta 
    and (G1.CF, G2.CF) not in (
        select C.g1, C.g2
        from CoppieNo C
) and (G1.CF, G2.CF) not in (
        select C.g2, C.g1
        from CoppieNo C
);