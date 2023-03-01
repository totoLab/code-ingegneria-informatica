section .data
    v equ 16
    n equ 12
    ris equ 8

section .text
    global _suffisso
_suffisso:
    push EBP
    mov EBP, ESP
    pushad

    mov EAX, [EBP + 16] ; v
    mov EDI, [EBP + 12] ; n
    sub EDI, 2          ; n - 2
    xor ESI, ESI        ; i
    mov CX, 1           ; cont_max
    mov DX, 1           ; cont

loop:
    cmp ESI, EDI
    jge end

    mov EBX, [EAX + ESI*2]  ; v[i]
    inc ESI                 ; i + 1
    cmp EBX, [EAX + ESI*2]  ; v[i + 1]
    jg max                  ; v[i] > v[i+1] -> check if this is the longest sequence
    inc DX                  ; longest sequence += 1
    jmp next

max:
    cmp CX, DX              
    jge reset               ; cont_max >= cont
    mov CX, DX              ; cont > cont_max

reset:
    mov DX, 1

next:
    jmp loop

end:
    mov EAX, [EBP + ris] 
    mov [EAX], CX

    popad
    mov ESP, EBP
    pop EBP
    ret 12