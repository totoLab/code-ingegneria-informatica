section .data
    v equ 16
    n equ 12
    w equ 8

section .text
    global _proc
_proc:
    push EBP
    mov EBP, ESP
    pushad

    mov EAX, [EBP + v]
    mov EBX, [EBP + w]
    mov EDI, [EBP + n]
    sub EDI, 3
    xor ESI, ESI
    
loop:
    cmp ESI, EDI
    jge end

    mov ECX, [EAX + ESI*2]  ; v[i]
    mov [EBX + ECX*2], ESI  ; w[v[i]] = i

    inc ESI
    jmp loop

end:
    popad
    mov ESP, EBP
    pop EBP
    ret 12