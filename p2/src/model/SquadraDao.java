package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SquadraDao {
    public Squadra doRetrieveByCampionato(String campionato) {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,punteggio,golfatti,golsubiti,campionato FROM Squadra WHERE campionato=?");
            ps.setString(1, campionato);
            ResultSet rs = ps.executeQuery();
            Squadra p = new Squadra();
            if (rs.next()) {

                p.setId(rs.getInt(1));
                p.setPunteggio(rs.getInt(2));
                p.setGolfatti(rs.getInt(3));
                p.setGolsubiti(rs.getInt(4));
                p.setCampionato(rs.getString(5));


            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
