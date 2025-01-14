import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {

    // Méthode pour ajouter un livre
    public void ajouterLivre(Livre livre) {
        String sql = "INSERT INTO livres (titre, auteur, categorie, nombre_exemplaires) VALUES (?, ?, ?, ?)";
        try (Connection conn = DB.connect();
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

    // Méthode pour rechercher un livre par titre
    public List<Livre> rechercherLivreParTitre(String titre) {
        String sql = "SELECT * FROM livres WHERE titre LIKE ?";
        List<Livre> livres = new ArrayList<>();
        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + titre + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                livres.add(new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("categorie"),
                        rs.getInt("nombre_exemplaires")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    public List<Livre> rechercherLivre(String critere, String valeur) {
        String sql = "SELECT * FROM livres WHERE " + critere + " LIKE ?";
        List<Livre> livres = new ArrayList<>();
        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + valeur + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                livres.add(new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("categorie"),
                        rs.getInt("nombre_exemplaires")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    public Livre rechercherLivreParId(int id) {
        String sql = "SELECT * FROM livres WHERE id = ?";
        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("categorie"),
                        rs.getInt("nombre_exemplaires")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Méthode pour afficher tous les livres
    public List<Livre> afficherTousLesLivres() {
        String sql = "SELECT * FROM livres";
        List<Livre> livres = new ArrayList<>();
        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Livre livre = new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("categorie"),
                        rs.getInt("nombre_exemplaires")
                );
                livres.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    // Méthode pour supprimer un livre par ID
    public void supprimerLivre(int id) {
        String sql = "DELETE FROM livres WHERE id = ?";
        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour un livre
    public void mettreAJourLivre(Livre livre) {
        String sql = "UPDATE livres SET titre = ?, auteur = ?, categorie = ?, nombre_exemplaires = ? WHERE id = ?";
        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, livre.getTitre());
            pstmt.setString(2, livre.getAuteur());
            pstmt.setString(3, livre.getCategorie());
            pstmt.setInt(4, livre.getNombreExemplaires());
            pstmt.setInt(5, livre.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
