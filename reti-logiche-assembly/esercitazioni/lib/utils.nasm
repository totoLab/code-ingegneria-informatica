; ===============================================================================================
;
; utils.nasm
;
; ===============================================================================================
;
; Libreria di macro di utilità generale. Di seguito si riporta l'elenco dei comandi 
; disponibili, la sintassi e la relativa descrizione.
;
; -----------------------------------------------------------------------------------------------
;	printd <dword>		stampa a video una double word (registro, memoria, immediato)
;				Sintassi specifica:
;				printd dword <immediato_32_bit>
;				printd dword [<indirizzo>]
;				printd <registro_32_bit>
; -----------------------------------------------------------------------------------------------
;	printw <word>		stampa a video una word (reg, mem, imm)
;				Sintassi specifica:
;				printw word <immediato_16_bit>
;				printw word [<indirizzo>]
;				printw <registro_16_bit>
; -----------------------------------------------------------------------------------------------
;	printb <byte>		stampa a video un bye (reg, mem, imm)
;				Sintassi specifica:
;				printb byte <immediato_8_bit>
;				printb dword [<indirizzo>]
;				printb <registro_8_bit>
; -----------------------------------------------------------------------------------------------
;	prints <str>,<len>	stampa a video la stringa di lunghezza <len> 
;				posta in memoria a partire dall'indirizzo <str>
; -----------------------------------------------------------------------------------------------
;	printregs		mostra il contenuto dei registri generali a 32 bit
; -----------------------------------------------------------------------------------------------
;	print<reg>		mostra il contenuto del registro a 32 bit <reg>
;				dove <reg> in {eax, ebx, ecx, edx, esi, edi, ebp, esp}
; -----------------------------------------------------------------------------------------------
;	format_dec		imposta il formato di output a DECIMAL (default)
; -----------------------------------------------------------------------------------------------
;	format_udec		imposta il formato di output a UNSIGNE DECIMAL
; -----------------------------------------------------------------------------------------------
;	format_bin		imposta il formato di output a BINARY
; -----------------------------------------------------------------------------------------------
;	format_hex		imposta il formato di output a HEXADECIMAL
; -----------------------------------------------------------------------------------------------
;	cr_on			abilita a capo nelle stampe (default)
; -----------------------------------------------------------------------------------------------
;	cr_off			disabilita a capo nelle stampe 
; -----------------------------------------------------------------------------------------------
;	exit <code>		termina il programma con codice d'uscita <code>
; ===============================================================================================
;
; Per utilizzare i comandi nel proprio sorgente assembly:
;	1) Includere utils.nasm nel proprio file sorgente con la direttiva:
;		%sseutils "utils.nasm"
;	2) Includere nel link il file oggetto utils.o:
;		ld -m elf_i386 <file>.o utils.o -o <file>
;

section	.data

seax	db	'eax = ['
leax	equ	$-seax
sebx	db	'ebx = ['
lebx	equ	$-sebx
secx	db	'ecx = ['
lecx	equ	$-secx
sedx	db	'edx = ['
ledx	equ	$-sedx
sesi	db	'esi = ['
lesi	equ	$-sesi
sedi	db	'edi = ['
ledi	equ	$-sedi
sebp	db	'ebp = ['
lebp	equ	$-sebp
sesp	db	'esp = ['
lesp	equ	$-sesp
send	db	']'
scr 	db	10
lend	equ	2


oldcr	db	1


extern format
extern cr
extern sprintd
extern buf
extern buflen


%macro	exit	1
	mov	eax, 1
	mov	ebx, %1
	int	80h
%endmacro


%macro	prints	2
	pushfd
	pushad
	mov	eax, 4
	mov	ebx, 1
	mov	ecx, %1
	mov	edx, %2
	int	80h
	popad
	popfd
%endmacro


%macro	printcr	0
	prints		scr, 1
%endmacro


%macro	printbuf	0
	prints	buf, [buflen]
%endmacro


%macro	cr_on	0
	mov	[cr], byte 1
%endmacro


%macro	cr_off	0
	mov	[cr], byte 0
%endmacro


%macro	cr_save	0
	push	eax
	mov	al, [cr]
	mov	[oldcr], al
	pop	eax
%endmacro


%macro	cr_restore 0
	push	eax
	mov	al, [oldcr]
	mov	[cr], al
	pop	eax
%endmacro


%macro	format_dec	0
	mov	[format], byte 0
%endmacro


%macro	format_udec	0
	mov	[format], byte 1
%endmacro


%macro	format_bin	0
	mov	[format], byte 2
%endmacro


%macro	format_hex	0
	mov	[format], byte 3
%endmacro


%macro	printd	1
	pushfd
	pushad
	push	dword 32
	push	%1
	call	sprintd
	add	esp, 8
	printbuf
	popad
	popfd
%endmacro


%macro	printw	1
	pushfd
	pushad
	push	dword 16
	mov	ax, %1
	movsx	eax, ax
	push	eax
	call	sprintd
	add	esp, 8
	printbuf
	popad
	popfd
%endmacro


%macro	printb	1
	pushfd
	pushad
	push	dword 8
	mov	al, %1
	movsx	eax, al
	push	eax
	call	sprintd
	add	esp, 8
	printbuf
	popad
	popfd
%endmacro


%macro	printeax	0
	cr_save
	cr_off
	prints		seax, leax
	printd		eax
	prints		send, lend
	cr_restore
%endmacro


%macro	printebx	0
	cr_save
	cr_off
	prints		sebx, lebx
	printd		ebx
	prints		send, lend
	cr_restore
%endmacro


%macro	printecx	0
	cr_save
	cr_off
	prints		secx, lecx
	printd		ecx
	prints		send, lend
	cr_restore
%endmacro


%macro	printedx	0
	cr_save
	cr_off
	prints		sedx, ledx
	printd		edx
	prints		send, lend
	cr_restore
%endmacro


%macro	printesi	0
	cr_save
	cr_off
	prints		sesi, lesi
	printd		esi
	prints		send, lend
	cr_restore
%endmacro


%macro	printedi	0
	cr_save
	cr_off
	prints		sedi, ledi
	printd		edi
	prints		send, lend
	cr_restore
%endmacro


%macro	printebp	0
	cr_save
	cr_off
	prints		sebp, lebp
	printd		ebp
	prints		send, lend
	cr_restore
%endmacro


%macro	printesp	0
	cr_save
	cr_off
	prints		sesp, lesp
	printd		esp
	prints		send, lend
	cr_restore
%endmacro


%macro	printregs	0
	cr_save
	printcr
	printeax
	printebx
	printecx
	printedx
	printesi
	printedi
;	printebp
;	printesp
	cr_restore
%endmacro

