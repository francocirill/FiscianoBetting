package model;

public class Squadra {
    private int campionato,punteggio,golfatti,golsubiti;
    private String id;

    public int getCampionato() {
        return campionato;
    }

    public void setCampionato(int campionato) {
        this.campionato = campionato;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
