import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmpruntDAO {

    public void enregistrerEmprunt(Emprunt emprunt) {
        String sql = "INSERT INTO emprunts (membreId, livreId, dateEmprunt, dateRetourPrevue) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
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
        try (Connection conn = DBConnection.getConnection();
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

    // Autres méthodes pour gérer les emprunts
}
