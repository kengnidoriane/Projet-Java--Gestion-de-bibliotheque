import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmpruntDAO {

    public void enregistrerEmprunt(Emprunt emprunt) {
        String sql = "INSERT INTO emprunts (membreId, livreId, dateEmprunt, dateRetourPrevue) VALUES (?, ?, ?, ?)";
        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, emprunt.getMembreId());
            pstmt.setInt(2, emprunt.getLivreId());
            pstmt.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            pstmt.setDate(4, Date.valueOf(emprunt.getDateRetourPrevue()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gererRetourLivre(int empruntId, LocalDate dateRetourEffective) {
        String sql = "UPDATE emprunts SET dateRetourEffective = ? WHERE idEmprunt = ?";
        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(dateRetourEffective));
            pstmt.setInt(2, empruntId);
            pstmt.executeUpdate();

            // Calcul des pénalités
            String query = "SELECT dateRetourPrevue FROM emprunts WHERE idEmprunt = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, empruntId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    LocalDate dateRetourPrevue = rs.getDate("dateRetourPrevue").toLocalDate();
                    long joursDeRetard = ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourEffective);
                    if (joursDeRetard > 0) {
                        System.out.println("Pénalité appliquée : " + (joursDeRetard * 100) + " F CFA");
                    } else {
                        System.out.println("Retour à temps.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // methode pour afficher tous les emprunts

    public List<String> afficherEmpruntsEnCours() {
        String sql = "SELECT e.id_emprunt AS emprunt_id, e.date_emprunt, e.date_retour_prevue, " +
                "m.nom AS membre_nom, m.prenom AS membre_prenom, l.titre AS livre_titre " +
                "FROM emprunts e " +
                "JOIN membres m ON e.membre_id = m.id " +
                "JOIN livres l ON e.livre_id = l.id " +
                "WHERE e.date_retour_effective IS NULL";
        List<String> empruntsEnCours = new ArrayList<>();
        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String empruntDetails = "Emprunt ID: " + rs.getInt("emprunt_id") +
                        ", Date d'emprunt: " + rs.getDate("date_emprunt") +
                        ", Date de retour prévue: " + rs.getDate("date_retour_prevue") +
                        ", Membre: " + rs.getString("membre_nom") + " " + rs.getString("membre_prenom") +
                        ", Livre: " + rs.getString("livre_titre");
                empruntsEnCours.add(empruntDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empruntsEnCours;
    }

}
