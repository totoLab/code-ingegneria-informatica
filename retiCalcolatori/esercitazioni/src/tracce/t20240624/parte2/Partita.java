package tracce.t20240624.parte2;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Partita implements Serializable {

    public static int ID_PROGRESSIVO = 0;

    private int id;
    private String squadraInCasa;
    private String squadraOspite;
    private LocalDate data;
    private LocalTime orario;
    private String stadio;
    private String luogo;

    public Partita(String squadraInCasa, String squadraOspite, LocalDate data, LocalTime orario, String stadio, String luogo) {
        this.id = ID_PROGRESSIVO++;
        this.squadraInCasa = squadraInCasa;
        this.squadraOspite = squadraOspite;
        this.data = data;
        this.orario = orario;
        this.stadio = stadio;
        this.luogo = luogo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean gioca(String squadra) {
        return this.squadraInCasa.equals(squadra) || this.squadraOspite.equals(squadra);
    }

    public String getSquadraInCasa() {
        return squadraInCasa;
    }

    public void setSquadraInCasa(String squadraInCasa) {
        this.squadraInCasa = squadraInCasa;
    }

    public String getSquadraOspite() {
        return squadraOspite;
    }

    public void setSquadraOspite(String squadraOspite) {
        this.squadraOspite = squadraOspite;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getOrario() {
        return orario;
    }

    public void setOrario(LocalTime orario) {
        this.orario = orario;
    }

    public String getStadio() {
        return stadio;
    }

    public void setStadio(String stadio) {
        this.stadio = stadio;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    @Override
    public String toString() {
        return String.format("%s vs %s il %s %s allo stadio %s (%s)", squadraInCasa, squadraOspite, data, orario, stadio, luogo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof Partita)) return false;

        Partita p = (Partita) obj;
        return p.squadraInCasa.equals(this.squadraInCasa) &&
                p.squadraOspite.equals(this.squadraOspite) &&
                p.data.equals(this.data);
    }
}
