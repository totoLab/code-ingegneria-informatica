section .data
    v equ 8
    w equ 12
    t equ 16
    n equ 20

section .text
    global proc
proc:
    push EBP
    mov ESP, EBP
    pushad
    mov EAX, [EBP + v]
    mov EBX, [EBP + w]
    mov ECX, [EBP + t]
    mov EDI, [EBP + n]
    xor ESI, ESI        ; i = 0
    
loop:
    cmp ESI, EDI
    jge end

    mov DX, [EAX + 2*ESI] ; V[i]
    and DX, 1             ; pari?
    jnz dispari
    mov DX, [EAX + 2*ESI] ; W[i]
    and DX, 1             ; pari?
    jnz dispari

    mov DX, [EAX + 2*ESI]
    mov [ECX + 4*ESI], DX     ; T[2*i] = V[i]
    mov DX, [EBX + 2*ESI]
    mov [ECX + 4*ESI + 2], DX ; T[2*i + 1] = W[i]
    
    inc ESI
    jmp loop

dispari:
    mov DX, [EAX + 2*ESI]
    neg DX                 
    mov [ECX + 4*ESI], DX     ; T[2*i] = -V[i]
    mov DX, [EBX + 2*ESI]
    neg DX                 
    mov [ECX + 4*ESI + 2], DX ; T[2*i + 1] = -W[i]

    inc ESI
    jmp loop

end:
    popad
    mov ESP, EBP
    pop EBP
    ret 16