package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CampionatoDAO {
    public List<Campionato> doRetrieveAll()
    {
        ArrayList<Campionato> a=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,nome,descrizione FROM campionato");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Campionato p = new Campionato();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                a.add(p);
            }
            return a;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Campionato doRetrieveById(String nome)
    {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,nome,descrizione FROM campionato WHERE nome=?");
            ps.setString(1,nome);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Campionato p = new Campionato();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
