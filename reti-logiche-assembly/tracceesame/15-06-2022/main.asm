%include "../lib/utils.nasm"

section .data
	v dw 6,4,7,1,9,10,7
	n equ ($-v)/2
	h equ 3

section .bss
	w resw h

section .text
	extern _proc
	global _start
_start:
	xor ESI, ESI
init:
	cmp ESI, h
	jge populate_w
	mov [w + ESI*2], word 0
	inc ESI	
	jmp init
populate_w:
	push v
	push dword n
	push dword h
	push w
	call _proc
	
	xor ESI, ESI
print_w:
	cmp ESI, h
	jge end
	printw word [w + ESI*2]
	inc ESI
	jmp print_w
end:
	exit 0
