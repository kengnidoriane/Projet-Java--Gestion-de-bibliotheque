
import java.time.LocalDate;


    public class Membre {
        private int id;
        private String nom;
        private String prenom;
        private String email;
        private LocalDate adhesionDate;

        // Constructeur
        public Membre(String nom, String prenom, String email, LocalDate adhesionDate) {
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.adhesionDate = adhesionDate;
        }

        public Membre(int id, String nom, String prenom, String email, LocalDate adhesionDate) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.adhesionDate = adhesionDate;
        }

        // Getters et setters
        public int getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public String getEmail() {
            return email;
        }

        public LocalDate getAdhesionDate() {
            return adhesionDate;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setAdhesionDate(LocalDate adhesionDate) {
            this.adhesionDate = adhesionDate;
        }

        @Override
        public String toString() {
            return "ID: " + id  +
                    ", Nom: " + nom  +
                    ", Prénom: " + prenom  +
                    ", Email: " + email  +
                    ", Date d'adhésion: " + adhesionDate + "\n";
        }
    }


