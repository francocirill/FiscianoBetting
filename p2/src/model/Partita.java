package model;

public class Partita {
    private int id;
    private String data,ora,idsquadra1,idsquadra2;
    private double quota1;
    private double quota2;
    private double quota3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdsquadra1() {
        return idsquadra1;
    }

    public void setIdsquadra1(String idsquadra1) {
        this.idsquadra1 = idsquadra1;
    }

    public String getIdsquadra2() {
        return idsquadra2;
    }

    public void setIdsquadra2(String idsquadra2) {
        this.idsquadra2 = idsquadra2;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public double getQuota1() {
        return quota1;
    }

    public void setQuota1(double quota1) {
        this.quota1 = quota1;
    }

    public double getQuota2() {
        return quota2;
    }

    public void setQuota2(double quota2) {
        this.quota2 = quota2;
    }

    public double getQuota3() {
        return quota3;
    }

    public void setQuota3(double quota3) {
        this.quota3 = quota3;
    }


}
