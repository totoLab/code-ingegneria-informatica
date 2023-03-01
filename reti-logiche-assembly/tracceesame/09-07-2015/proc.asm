section .data
    v equ 16
    l equ 12
    w equ 8

section .text
    global _proc
_proc:
    push EBP
    mov EBP, ESP
    pushad

    mov EAX, [EBP + v]
    mov EBX, [EBP + w]
    mov EDI, [EBP + l]
    xor ESI, ESI

loop:
    cmp ESI, EDI
    jge end

    lea ECX, [EAX + ESI*2]  ; addr of v[i]
    mov DX, [ECX]           ; save value
    add DX, [ECX + EDI*2]   ; sum with v[i+n/2]
    
    mov [EBX + ESI*2], DX   ; set w[i]

    inc ESI
    jmp loop
end:
    popad
    mov ESP, EBP
    pop EBP
    ret 12