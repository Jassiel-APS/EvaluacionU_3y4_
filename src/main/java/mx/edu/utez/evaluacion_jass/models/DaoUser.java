package mx.edu.utez.evaluacion_jass.models;
import mx.edu.utez.evaluacion_jass.utils.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoUser {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    public User loadUserByUsernameAndPassword(String username, String password) {
        System.out.println(username + password);
        try {
            conn = new MySQLConnection().connect();
            String query = "select users.id_user, users.username, r.role from users  join roles r on users.id_role = r.id_role where username=? and password=?;";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, username);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId_user(rs.getLong("id_user"));
                user.setUsername(rs.getString("username"));

                Role role = new Role();
                role.setRole(rs.getString("role"));
                user.setRole(role);
                return user;
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoUser.class.getName())
                    .log(Level.SEVERE,
                            "Credentials mismatch: " + e.getMessage());
        } finally {
            close();
        }
        return null;
    }
    public List<Incidents> findAll() {
        List<Incidents> incidents = new ArrayList<>();
        try {
            conn = new MySQLConnection().connect();
            String query = "SELECT * FROM incidents;";
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Incidents incident = new Incidents();
                incident.setId(rs.getLong("id"));
                incident.setName(rs.getString("name"));
                incident.setTitle(rs.getString("title"));
                incident.setDescription(rs.getString("description"));
                incident.setStatus(rs.getString("status"));
                incidents.add(incident);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoUser.class.getName())
                    .log(Level.SEVERE,
                            "Credentials mismatch: " + e.getMessage());
        } finally {
            close();
        }
        return incidents;
    }
    public List<Incidents> ListaIncidentes_Activos() {
        List<Incidents> incidents = new ArrayList<>();
        try {
            conn = new MySQLConnection().connect();
            String query = "SELECT * FROM incidents where status = 'ACTIVO';";
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Incidents incident = new Incidents();
                incident.setId(rs.getLong("id"));
                incident.setName(rs.getString("name"));
                incident.setTitle(rs.getString("title"));
                incident.setDescription(rs.getString("description"));
                incident.setStatus(rs.getString("status"));
                incidents.add(incident);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoUser.class.getName())
                    .log(Level.SEVERE,
                            "Credentials mismatch: " + e.getMessage());
        } finally {
            close();
        }
        return incidents;
    }

    public void close() {
        try {
            if (conn != null) conn.close();
            if (pstm != null) pstm.close();
            if (rs != null) rs.close();
        } catch (SQLException e) {

        }

    }
}
