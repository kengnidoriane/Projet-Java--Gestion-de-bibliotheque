import java.time.LocalDate;
public class Emprunt {
    private int idEmprunt;
    private int membreId;
    private int livreId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    // Constructeur
    public Emprunt(int idEmprunt, int membreId, int livreId, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
        this.idEmprunt = idEmprunt;
        this.membreId = membreId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
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

    // Méthode pour afficher les détails de l'emprunt
    public void afficherDetails() {
        System.out.println("ID Emprunt: " + idEmprunt);
        System.out.println("ID Membre: " + membreId);
        System.out.println("ID Livre: " + livreId);
        System.out.println("Date d'emprunt: " + dateEmprunt);
        System.out.println("Date de retour prévue: " + dateRetourPrevue);
        if (dateRetourEffective != null) {
            System.out.println("Date de retour effective: " + dateRetourEffective);
            System.out.println("Pénalités: " + calculerPenalites() + " F CFA");
        }
    }
}
