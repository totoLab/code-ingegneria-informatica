%include '../lib/utils.nasm'
section .data
	V dw 7,3,3,4,4,4,4,10
	N equ ($ - V) /2
section .bss
	ris resd 1
section .text
	extern proc
	global _start

_start:
	
	printw word N
	PUSH V
	PUSH word N
	PUSH ris
	CALL proc
	POP ECX
	MOV [ris], ECX
	printd dword [ris]
	exit 0