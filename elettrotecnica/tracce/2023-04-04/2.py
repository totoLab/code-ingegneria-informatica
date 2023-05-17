def parallelo(r1, r2):
    return (r1 * r2) / (r1 + r2)

def serie(r1, r2):
    return r1 + r2


r1, r2, r3, r4, r5, r6, r7 = 7, 4, 4, 4, 8, 4, 6
Req = parallelo(
    r7,
    parallelo(
        r5,
        serie(  
            r4 + r6,
            parallelo(
                serie(r2, r3),
                r1
            )
        )
    )
)

print(f"{Req=}")