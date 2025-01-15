import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class MembreDAO {

    public void inscrireMembre(Membre membre) {
        String sql = "INSERT INTO membres (nom, prenom, email, adhesion_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, membre.getNom());
            pstmt.setString(2, membre.getPrenom());
            pstmt.setString(3, membre.getEmail());
            pstmt.setDate(4, Date.valueOf(membre.getAdhesionDate()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Membre> rechercherMembreParNom(String nom) {
        String sql = "SELECT * FROM membres WHERE LOWER(nom) LIKE LOWER(?)";
        List<Membre> membres = new ArrayList<>();
        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nom + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                membres.add(new Membre(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getDate("adhesion_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }

//    methode pour afficher tous les membres contenu dans le systeme
    public List<Membre> afficherTousLesMembres() {
        String sql = "SELECT * FROM membres";
        List<Membre> membres = new ArrayList<>();
        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Membre membre = new Membre(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getDate("adhesion_date").toLocalDate()
                );
                membres.add(membre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }

//    methode pour compter le nombre total de membre
    public int compterMembres() {
        String sql = "SELECT COUNT(*) FROM membres";
        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
