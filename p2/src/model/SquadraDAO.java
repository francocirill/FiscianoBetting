package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SquadraDAO {
    public ArrayList<Squadra> doRetrieveByCampionato(String campionato) {

        ArrayList<Squadra> a=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,punteggio,golfatti,golsubiti,campionato FROM Squadra WHERE campionato=?");
            ps.setString(1, campionato);
            ResultSet rs = ps.executeQuery();
            Squadra p = new Squadra();
            while (rs.next()) {

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
