package model;

public class Scommessa {
    public String getEsito() {
        return esito;
    }

    public void setEsito(String esito) {
        this.esito = esito;
    }

    public Partita getP() {
        return p;
    }

    public void setP(Partita p) {
        this.p = p;
    }

    private String esito;
    private Partita p;

}
