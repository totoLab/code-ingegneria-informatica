package esercitazione1.esercizio1_2;

import java.io.*;
public class Studente implements Serializable {
    private int matricola;
    private String nome, cognome, corsoDiLaurea;
    public Studente (int matricola, String nome, String cognome,
                     String corsoDiLaurea) {
        this.matricola = matricola; this.nome = nome;
        this.cognome = cognome; this.corsoDiLaurea = corsoDiLaurea;
    }
    public int getMatricola () { return matricola; }
    public String getNome () { return nome; }
    public String getCognome () { return cognome; }
    public String getCorsoDiLaurea () { return corsoDiLaurea; }
}