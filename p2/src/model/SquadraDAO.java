package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SquadraDAO {
    public ArrayList<Squadra> doRetrieveByCampionato(int campionato) {

        ArrayList<Squadra> a=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,punteggio,golfatti,golsubiti,idcampionato FROM Squadra WHERE idcampionato=? ORDER BY punteggio DESC ,golfatti DESC ,golsubiti DESC");
            ps.setInt(1, campionato);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Squadra p = new Squadra();
                p.setId(rs.getString(1));
                p.setPunteggio(rs.getInt(2));
                p.setGolfatti(rs.getInt(3));
                p.setGolsubiti(rs.getInt(4));
                p.setCampionato(rs.getInt(5));
                a.add(p);

            }
            return a;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Squadra doRetrieveById(String id) {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,punteggio,golfatti,golsubiti,idcampionato FROM Squadra WHERE id=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Squadra p = new Squadra();
                p.setId(rs.getString(1));
                p.setPunteggio(rs.getInt(2));
                p.setGolfatti(rs.getInt(3));
                p.setGolsubiti(rs.getInt(4));
                p.setCampionato(rs.getInt(5));
                return p;

            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void doUpdate(Squadra s) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("UPDATE squadra SET punteggio=?, golfatti=?, golsubiti=? WHERE id=?");
            ps.setInt(1,s.getPunteggio());
            ps.setInt(2,s.getGolfatti());
            ps.setInt(3,s.getGolsubiti());
            ps.setString(4,s.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
