import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {

    public void ajouterLivre(Livre livre) {
        String sql = "INSERT INTO livres (titre, auteur, categorie, nombreExemplaires) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, livre.getTitre());
            pstmt.setString(2, livre.getAuteur());
            pstmt.setString(3, livre.getCategorie());
            pstmt.setInt(4, livre.getNombreExemplaires());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livre> rechercherLivreParTitre(String titre) {
        String sql = "SELECT * FROM livres WHERE titre LIKE ?";
        List<Livre> livres = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + titre + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                livres.add(new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("categorie"),
                        rs.getInt("nombreExemplaires")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    // Autres méthodes pour supprimer et modifier un livre
}
