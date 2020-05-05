package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PartitaDAO {
    public List<Partita> doRetrieveAll()
    {
        ArrayList<Partita> a=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,data,ora,idsquadra1,idsquadra2,quota1,quota2,quota3 FROM Partita");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Partita p = new Partita();
                p.setId(rs.getInt(1));
                p.setData(rs.getString(2));
                p.setOra(rs.getString(3));
                p.setIdsquadra1(rs.getInt(4));
                p.setIdsquadra2(rs.getInt(5));
                p.setQuota1(rs.getDouble(6));
                p.setQuota2(rs.getDouble(7));
                p.setQuota3(rs.getDouble(8));
                a.add(p);
            }
            return a;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }/*
    public Partita doRetrieveById(int id) {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,nome,descrizione FROM Partita WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Partita p = new Partita();
            if (rs.next()) {

                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));

            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
    public List<Partita> doRetrieveByCampionato(String campionato)
    {
        ArrayList<Partita> a=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,data,ora,idsquadra1,idsquadra2,quota1,quota2,quota3 FROM partita,squadra " +
                            "WHERE partita.idsquadra1=squadra.id AND squadra.campionato=?");
            ps.setString(1, campionato);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Partita p = new Partita();
                p.setId(rs.getInt(1));
                p.setData(rs.getString(2));
                p.setOra(rs.getString(3));
                p.setIdsquadra1(rs.getInt(4));
                p.setIdsquadra2(rs.getInt(5));
                p.setQuota1(rs.getDouble(6));
                p.setQuota2(rs.getDouble(7));
                p.setQuota3(rs.getDouble(8));
                a.add(p);
            }
            return a;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Integer> findProdotti(int id) {

        ArrayList<Integer> p=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idprodotto FROM prodottoPartita WHERE idPartita="+id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                p.add(rs.getInt(1));

            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
