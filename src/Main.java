import java.util.Scanner;
import java.time.LocalDate;

public class BibliothequeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LivreDAO livreDAO = new LivreDAO();
        MembreDAO membreDAO = new MembreDAO();
        EmpruntDAO empruntDAO = new EmpruntDAO();

        while (true) {
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Rechercher un livre par titre");
            System.out.println("3. Inscrire un membre");
            System.out.println("4. Rechercher un membre par nom");
            System.out.println("5. Enregistrer un emprunt");
            System.out.println("6. Gérer le retour d'un livre");
            System.out.println("0. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    System.out.println("Titre : ");
                    String titre = scanner.nextLine();
                    System.out.println("Auteur : ");
                    String auteur = scanner.nextLine();
                    System.out.println("Catégorie : ");
                    String categorie = scanner.nextLine();
                    System.out.println("Nombre d'exemplaires : ");
                    int nombreExemplaires = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne
                    livreDAO.ajouterLivre(new Livre(titre, auteur, categorie, nombreExemplaires));
                    break;
                case 2:
                    System.out.println("Titre : ");
                    String titreRecherche = scanner.nextLine();
                    livreDAO.rechercherLivreParTitre(titreRecherche).forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.println("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.println("Email : ");
                    String email = scanner.nextLine();
                    System.out.println("Date d'adhésion (YYYY-MM-DD) : ");
                    String adhesionDate = scanner.nextLine();
                    membreDAO.inscrireMembre(new Membre(nom, prenom, email, LocalDate.parse(adhesionDate)));
                    break;
                case 4:
                    System.out.println("Nom : ");
                    String nomRecherche = scanner.nextLine();
                    membreDAO.rechercherMembreParNom(nomRecherche).forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("ID du membre : ");
                    int membreId = scanner.nextInt();
                    System.out.println("ID du livre : ");
                    int livreId = scanner.nextInt();
                    System.out.println("Date d'emprunt (YYYY-MM-DD) : ");
                    String dateEmprunt = scanner.next();
                    System.out.println("Date de retour prévue (YYYY-MM-DD) : ");
                    String dateRetourPrevue = scanner.next();
                    empruntDAO.enregistrerEmprunt(new Emprunt(membreId, livreId, LocalDate.parse(dateEmprunt), LocalDate.parse(dateRetourPrevue)));
                    break;
                case 6:
                    System.out.println("ID de l'emprunt : ");
                    int empruntId = scanner.nextInt();
                    System.out.println("Date de retour effective (YYYY-MM-DD) : ");
                    String dateRetourEffective = scanner.next();
                    empruntDAO.gererRetourLivre(empruntId, LocalDate.parse(dateRetourEffective));
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }
}
