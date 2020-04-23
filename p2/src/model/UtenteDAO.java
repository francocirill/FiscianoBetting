package model;

import java.sql.*;

public class UtenteDAO {
    public void doSave(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente (username,password,email,nome,cognome) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, utente.getUsername());
            ps.setString(2, utente.getPassword());
            ps.setString(3, utente.getEmail());
            ps.setString(4, utente.getNome());
            ps.setString(5, utente.getCognome());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            utente.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Utente doRetrieveByUsernamePassword(String username, String passwordhash) {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, username, password, nome,cognome, email, admin" +
                            " FROM utente WHERE username=? AND passwordhash=SHA1(?)");
            ps.setString(1, username);
            ps.setString(2, passwordhash);
            ResultSet rs = ps.executeQuery();
            Utente p=null;
            if (rs.next()) {
                p=new Utente();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setNome(rs.getString(4));
                p.setCognome(rs.getString(5));
                p.setEmail(rs.getString(6));
                p.setAdmin(rs.getInt(7));

            }
            else
            {
                throw new RuntimeException();
            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void doUpdate(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE utente SET username=?,password=?,nome=?,cognome=?,email=?) WHERE id=?");
            ps.setString(1, utente.getUsername());
            ps.setString(2, utente.getPassword());
            ps.setString(3, utente.getNome());
            ps.setString(4, utente.getCognome());
            ps.setString(5, utente.getEmail());
            ps.setInt(6, utente.getId());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Update error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
