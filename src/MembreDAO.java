import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class MembreDAO {

    public void inscrireMembre(Membre membre) {
        String sql = "INSERT INTO membres (nom, prenom, email, adhesionDate) VALUES (?, ?, ?, ?)";
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
        String sql = "SELECT * FROM membres WHERE nom LIKE ?";
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
                        rs.getDate("adhesionDate").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }

    // Autres méthodes pour supprimer et modifier un membre
}
