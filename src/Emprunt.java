import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprunt {
    private int idEmprunt;
    private int membreId;
    private int livreId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    // Constructeur
    public Emprunt(int membreId, int livreId, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
        this.membreId = membreId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
    }

    public Emprunt(int idEmprunt, int membreId, int livreId, LocalDate dateEmprunt, LocalDate dateRetourPrevue, LocalDate dateRetourEffective) {
        this.idEmprunt = idEmprunt;
        this.membreId = membreId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
    }

    // Getters et setters
    public int getIdEmprunt() {
        return idEmprunt;
    }

    public int getMembreId() {
        return membreId;
    }

    public int getLivreId() {
        return livreId;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public LocalDate getDateRetourEffective() {
        return dateRetourEffective;
    }

    public void setDateRetourEffective(LocalDate dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    // Méthode pour calculer les pénalités
    public long calculerPenalites() {
        if (dateRetourEffective != null && dateRetourEffective.isAfter(dateRetourPrevue)) {
            return ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourEffective) * 100;
        }
        return 0;
    }

    // Méthode toString pour afficher les détails de l'emprunt
    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("ID Emprunt: ").append(idEmprunt).append("\n")
                .append("ID Membre: ").append(membreId).append("\n")
                .append("ID Livre: ").append(livreId).append("\n")
                .append("Date d'emprunt: ").append(dateEmprunt).append("\n")
                .append("Date de retour prévue: ").append(dateRetourPrevue).append("\n");
        if (dateRetourEffective != null) {
            details.append("Date de retour effective: ").append(dateRetourEffective).append("\n")
                    .append("Pénalités: ").append(calculerPenalites()).append(" F CFA");
        }
        return details.toString();
    }
}
