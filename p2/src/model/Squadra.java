package model;

public class Squadra {
    private int id,punteggio,golfatti,golsubiti;
    private String campionato;

    public String getCampionato() {
        return campionato;
    }

    public void setCampionato(String campionato) {
        this.campionato = campionato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public int getGolfatti() {
        return golfatti;
    }

    public void setGolfatti(int golfatti) {
        this.golfatti = golfatti;
    }

    public int getGolsubiti() {
        return golsubiti;
    }

    public void setGolsubiti(int golsubiti) {
        this.golsubiti = golsubiti;
    }
}
