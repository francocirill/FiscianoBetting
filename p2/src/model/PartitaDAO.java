package model;

import java.sql.*;
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
                p.setIdsquadra1(rs.getString(4));
                p.setIdsquadra2(rs.getString(5));
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
    public Partita doRetrieveById(int id) {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,data,ora,idsquadra1,idsquadra2,quota1,quota2,quota3 FROM Partita WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Partita p = new Partita();
            if (rs.next()) {

                p.setId(rs.getInt(1));
                p.setId(rs.getInt(1));
                p.setData(rs.getString(2));
                p.setOra(rs.getString(3));
                p.setIdsquadra1(rs.getString(4));
                p.setIdsquadra2(rs.getString(5));
                p.setQuota1(rs.getDouble(6));
                p.setQuota2(rs.getDouble(7));
                p.setQuota3(rs.getDouble(8));

            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Partita> doRetrieveByCampionato(int campionato)
    {
        ArrayList<Partita> a=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT partita.id,data,ora,idsquadra1,idsquadra2,quota1,quota2,quota3 FROM partita,squadra " +
                            "WHERE partita.idsquadra1=squadra.id AND squadra.idcampionato=?");
            ps.setInt(1, campionato);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Partita p = new Partita();
                p.setId(rs.getInt(1));
                p.setData(rs.getString(2));
                p.setOra(rs.getString(3));
                p.setIdsquadra1(rs.getString(4));
                p.setIdsquadra2(rs.getString(5));
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
    /*
    public ArrayList<Integer> findProdotti(int id) {

        ArrayList<Integer> p=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idpartita FROM partitaPartita WHERE idPartita="+id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                p.add(rs.getInt(1));

            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

     */
    public List<Partita> doRetrieveBySquadre(String against, int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, data, ora, idsquadra1,idsquadra2,quota1,quota2,quota3 FROM partita WHERE MATCH(idsquadra1,idsquadra2) AGAINST(? IN BOOLEAN MODE) LIMIT ?, ?");
            ps.setString(1, against);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Partita> partite = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Partita p = new Partita();
                p.setId(rs.getInt(1));
                p.setData(rs.getString(2));
                p.setOra(rs.getString(3));
                p.setIdsquadra1(rs.getString(4));
                p.setIdsquadra2(rs.getString(5));
                p.setQuota1(rs.getDouble(6));
                p.setQuota2(rs.getDouble(7));
                p.setQuota3(rs.getDouble(8));
                partite.add(p);
            }
            return partite;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void doUpdate(Partita p) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("UPDATE partita SET data=?, ora=?, quota1=?, quota2=?, quota3=? WHERE id=?");
            ps.setString(1,p.getData());
            ps.setString(2,p.getOra());
            ps.setDouble(3,p.getQuota1());
            ps.setDouble(4,p.getQuota2());
            ps.setDouble(5, p.getQuota3());
            ps.setInt(6, p.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM partita WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void doSave(Partita partita) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO partita(data, ora, idsquadra1, idsquadra2, quota1,quota2,quota3) VALUES(?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, partita.getData());
            ps.setString(2, partita.getOra());
            ps.setString(3, partita.getIdsquadra1());
            ps.setString(4, partita.getIdsquadra2());
            ps.setDouble(5, partita.getQuota1());
            ps.setDouble(6, partita.getQuota2());
            ps.setDouble(7, partita.getQuota3());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            partita.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
