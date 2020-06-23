package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchedinaGiocataDAO {
    public void doSave(SchedinaGiocata schedina) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO schedinagiocata(testo,data,ora,importo,vincita,idutente) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, schedina.getTesto());
            ps.setString(2,schedina.getData());
            ps.setString(3,schedina.getOra());
            ps.setInt(4, schedina.getImporto());
            ps.setDouble(5, schedina.getVincita());
            ps.setInt(6, schedina.getIdutente());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            schedina.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<SchedinaGiocata> doRetrieveByIdutente(int idutente) {

        ArrayList<SchedinaGiocata> a=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idschedinagiocata,testo,data,ora,importo,vincita,idutente FROM schedinagiocata WHERE idutente=?");
            ps.setInt(1, idutente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SchedinaGiocata s = new SchedinaGiocata();
                s.setId(rs.getInt(1));
                s.setTesto(rs.getString(2));
                s.setData(rs.getString(3));
                s.setOra(rs.getString(4));
                s.setImporto(rs.getInt(5));
                s.setVincita(rs.getDouble(6));
                s.setIdutente(rs.getInt(7));
                a.add(s);
            }
            return a;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<SchedinaGiocata> doRetrieveAll() {

        ArrayList<SchedinaGiocata> a=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idschedinagiocata,testo,data,ora,importo,vincita,idutente FROM schedinagiocata");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SchedinaGiocata s = new SchedinaGiocata();
                s.setId(rs.getInt(1));
                s.setTesto(rs.getString(2));
                s.setData(rs.getString(3));
                s.setOra(rs.getString(4));
                s.setImporto(rs.getInt(5));
                s.setVincita(rs.getDouble(6));
                s.setIdutente(rs.getInt(7));
                a.add(s);
            }
            return a;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
