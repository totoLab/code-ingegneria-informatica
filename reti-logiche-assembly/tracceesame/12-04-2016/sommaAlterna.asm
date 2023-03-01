section .data
    a equ 16
    n equ 12
    res equ 8

section .text
    global _sommaAlterna
_sommaAlterna:
    push EBP
    mov EBP, ESP
    pushad

    xor ESI, ESI            ; i
    mov EDI, [EBP + n]      ; lenght
    SHR EDI, 1              ; half the lenght
    xor DX, DX              ; res

loop:
    cmp ESI, EDI
    jge end
    
    ; too many sums for the compiler (EBP + a + ESI * 2)
    ; placing addr of vector element in ECX
    xor ECX, ECX
    add ECX, [EBP + a]      ; [EBP + a] start of vector
    add ECX, ESI
    add ECX, ESI

    mov BX, [ECX]           ; V[i]
    mov AX, BX
    and AX, 1
    jnz second              ; odd
    add DX, BX              ; even

second:
    ; adding offset to addr
    mov BX, [ECX + EDI * 2] ; V[i+n/2] 
    mov AX, BX
    and AX, 1
    jz next                 ; even
    sub DX, BX              ; odd

next:
    inc ESI
    jmp loop

end:
    mov EAX, [EBP + res]    ; get result addr
    mov [EAX], DX           ; write result to memory

    popad
    mov ESP, EBP
    pop EBP
    ret 12