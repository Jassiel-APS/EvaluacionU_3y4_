package mx.edu.utez.evaluacion_jass.models.Incident;

import mx.edu.utez.evaluacion_jass.models.crud.DaoRepository;
import mx.edu.utez.evaluacion_jass.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoIncidents implements DaoRepository<Incident> {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    @Override
    public List<Incident> findAll() {
        List<Incident> incidents = new ArrayList<>();
        try {
            conn = new MySQLConnection().connect();
            String query = "SELECT * FROM incidents;";
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Incident incident = new Incident();
                incident.setId(rs.getLong("id"));
                incident.setName(rs.getString("name"));
                incident.setTitle(rs.getString("title"));
                incident.setDescription(rs.getString("description"));
                incident.setStatus(rs.getString("status"));
                incidents.add(incident);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoIncidents.class.getName())
                    .log(Level.SEVERE, "Error findAll "
                            + e.getMessage());
        } finally {
            close();
        }
        return incidents;
    }

    @Override
    public Incident findOne(Long id) {
        try {
            conn = new MySQLConnection().connect();
            String query = "SELECT * FROM incidents WHERE id = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            Incident incident = new Incident();
            if (rs.next()) {
                incident.setId(rs.getLong("id"));
                incident.setName(rs.getString("name"));
                incident.setTitle(rs.getString("title"));
                incident.setDescription(rs.getString("description"));
                incident.setStatus(rs.getString("status"));
            }
            return incident;
        } catch (SQLException e) {
            Logger.getLogger(DaoIncidents.class.getName()).log(Level.SEVERE, "Error findOne " + e.getMessage());
        } finally {
            close();
        }
        return null;
    }

    @Override
    public boolean save(Incident object) {
        try {
            conn = new MySQLConnection().connect();
            String query = "INSERT INTO incidents (name, title, description)" +
                    " VALUES (?, ?, ?);";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, object.getName());
            pstm.setString(2, object.getTitle());
            pstm.setString(3, object.getDescription());
            return pstm.executeUpdate() > 0; // == 1
        } catch (SQLException e) {
            Logger.getLogger(DaoIncidents.class.getName())
                    .log(Level.SEVERE, "Error save " + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    public boolean charge_active(Long id) {
        try {
            conn = new MySQLConnection().connect();
            String query = "UPDATE incidents SET status = 'ACTIVO' WHERE id = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, Long.parseLong(String.valueOf(id)));
            System.out.println("id: " + id + " estado: Activo ");
            return pstm.executeUpdate() > 0; // ==1
        } catch (SQLException e) {
            Logger.getLogger(DaoIncidents.class.getName())
                    .log(Level.SEVERE, "Error active_charge"
                            + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    public boolean charge_inactive(Long id) {
        try {
            conn = new MySQLConnection().connect();
            String query = "UPDATE incidents SET status = 'RECHAZADO' WHERE id = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, Long.parseLong(String.valueOf(id)));
            System.out.println("id: " + id + " estado: " + 0);
            return pstm.executeUpdate() > 0; // ==1
        } catch (SQLException e) {
            Logger.getLogger(DaoIncidents.class.getName())
                    .log(Level.SEVERE, "Error findAll"
                            + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    public boolean _admin_active (Long id) {
        try {
            conn = new MySQLConnection().connect();
            String query = "UPDATE incidents SET status = 'APROBADO' WHERE id = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, Long.parseLong(String.valueOf(id)));
            System.out.println("id: " + id + " estado: Aprobado");
            return pstm.executeUpdate() > 0; // ==1
        } catch (SQLException e) {
            Logger.getLogger(DaoIncidents.class.getName())
                    .log(Level.SEVERE, "Error active_admin"
                            + e.getMessage());
        } finally {
            close();
        }
        return false;
    }
    public boolean admin_inactive(Long id) {
        try {
            conn = new MySQLConnection().connect();
            String query = "UPDATE incidents SET status = 'RECHAZADO' WHERE id = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, Long.parseLong(String.valueOf(id)));
            System.out.println("id: " + id + " estado: " + 0);
            return pstm.executeUpdate() > 0; // ==1
        } catch (SQLException e) {
            Logger.getLogger(DaoIncidents.class.getName())
                    .log(Level.SEVERE, "Error findAll"
                            + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            conn = new MySQLConnection().connect();
            String query = "DELETE FROM incidents WHERE id = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, id);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(DaoIncidents.class.getName())
                    .log(Level.SEVERE, "Error delete " + e.getMessage());
        } finally {
            close();
        }
        return false;
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
