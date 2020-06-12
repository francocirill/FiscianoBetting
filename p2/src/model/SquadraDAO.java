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
                    con.prepareStatement("SELECT id,punteggio,golfatti,golsubiti,idcampionato FROM Squadra WHERE idcampionato=?");
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
}
