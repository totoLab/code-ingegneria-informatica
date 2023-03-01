section .data
    a equ 16
    n equ 12
    s equ 8

section .text
    global proc
proc:
    push EBP
    mov EBP, ESP
    pushad

    mov EAX, [EBP + a]  ; access a address on stack
    mov ESI, [EBP + n]  ; access n address on stack
    xor EDI, EDI        ; init i
    xor CX, CX          ; init sum
loop:
    cmp EDI, ESI        ; stop condition
    jge end
    add CX, [EAX + EDI*2]   ; add to sum vector element with stack address
    inc EDI                 ; i++
    jmp loop
end:
    mov EAX, [EBP + s]  ; access address of reserved space in stack
    mov [EAX], CX       ; write sum to memory 

    popad
    mov ESP, EBP
    pop EBP
    RET 12              ; 3 parameters * 4