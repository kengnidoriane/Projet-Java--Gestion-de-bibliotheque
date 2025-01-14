import java.sql.SQLException;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LivreDAO livreDAO = new LivreDAO();
        MembreDAO membreDAO = new MembreDAO();
        EmpruntDAO empruntDAO = new EmpruntDAO();

        try (var connection = DB.connect()) {
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        while (true) {
            System.out.println("*************** Bienvenue dans votre systeme de gestion de Bibliotheque ***************");
            System.out.println();
            System.out.println("Veuillez choisir une option :");
            System.out.println("1. Gestion des livres");
            System.out.println("2. Gestion des membres");
            System.out.println("3. Gestion des emprunts");
            System.out.println("0. Quitter");

            int choixGestion = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choixGestion) {
                case 1:
                    menuGestionLivres(scanner, livreDAO);
                    break;
                case 2:
                    menuGestionMembres(scanner, membreDAO);
                    break;
                case 3:
                    menuGestionEmprunts(scanner, empruntDAO);
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

    public static void menuGestionLivres(Scanner scanner, LivreDAO livreDAO) {
        while (true) {
            System.out.println("Gestion des livres :");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Rechercher un livre");
            System.out.println("3. Afficher tous les livres");
            System.out.println("0. Retour");

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
                    menuRechercheLivre(scanner, livreDAO);
                    break;
                case 3:
                    List<Livre> livres = livreDAO.afficherTousLesLivres();
                    for (Livre livre : livres) {
                        System.out.println(livre);
                    };
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }


    public static void menuRechercheLivre(Scanner scanner, LivreDAO livreDAO) {
        System.out.println("Choisissez le critère de recherche : ");
        System.out.println("1. Par titre");
        System.out.println("2. Par auteur");
        System.out.println("3. Par ID");

        int choixCritere = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        String critere = null;
        String valeurRecherche = null;
        int idRecherche = 0;

        switch (choixCritere) {
            case 1:
                critere = "titre";
                System.out.println("Entrez le titre : ");
                valeurRecherche = scanner.nextLine();
                break;
            case 2:
                critere = "auteur";
                System.out.println("Entrez l'auteur : ");
                valeurRecherche = scanner.nextLine();
                break;
            case 3:
                System.out.println("Entrez l'ID : ");
                idRecherche = scanner.nextInt();
                scanner.nextLine(); // Consommer la nouvelle ligne
                Livre livre = livreDAO.rechercherLivreParId(idRecherche);
                if (livre != null) {
                    System.out.println(livre);
                } else {
                    System.out.println("Livre non trouvé.");
                }
                return;
            default:
                System.out.println("Choix invalide, retour au menu principal.");
                return;
        }

        List<Livre> livres = livreDAO.rechercherLivre(critere, valeurRecherche);
        if (!livres.isEmpty()) {
            livres.forEach(System.out::println);
        } else {
            System.out.println("Aucun livre trouvé pour le critère spécifié.");
        }
    }


    public static void menuGestionMembres(Scanner scanner, MembreDAO membreDAO) {
        while (true) {
            System.out.println("Gestion des membres :");
            System.out.println("1. Inscrire un membre");
            System.out.println("2. Rechercher un membre par nom");
            System.out.println("0. Retour");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
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
                case 2:
                    System.out.println("Nom : ");
                    String nomRecherche = scanner.nextLine();
                    membreDAO.rechercherMembreParNom(nomRecherche).forEach(System.out::println);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    public static void menuGestionEmprunts(Scanner scanner, EmpruntDAO empruntDAO) {
        while (true) {
            System.out.println("Gestion des emprunts :");
            System.out.println("1. Enregistrer un emprunt");
            System.out.println("2. Gérer le retour d'un livre");
            System.out.println("0. Retour");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
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
                case 2:
                    System.out.println("ID de l'emprunt : ");
                    int empruntId = scanner.nextInt();
                    System.out.println("Date de retour effective (YYYY-MM-DD) : ");
                    String dateRetourEffective = scanner.next();
                    empruntDAO.gererRetourLivre(empruntId, LocalDate.parse(dateRetourEffective));
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }
}
