%include "../lib/utils.nasm"

section .bss
	cont resw 1

section .text
	global _start
_start:
	xor ESI, ESI
	xor DI, DI
loop:
	ESI, n
	jge end
	
	mov AX, [w + ESI*2]
	cwd
	idiv word [v + ESI*2]
	cmp DX, 0
	jne next
	inc DI
next:	
	inc ESI
	jmp loop
end:
	mov [cont], DI
	printw word DI
	exit 0
