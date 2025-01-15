# Système de Gestion de Bibliothèque

Bienvenue dans le Système de Gestion de Bibliothèque ! Ce projet est une application Java conçue pour gérer efficacement les livres, les membres et les emprunts d'une bibliothèque. Le système offre des fonctionnalités telles que l'ajout et la recherche de livres, la gestion des informations des membres et la gestion des emprunts et des retours de livres.

## Fonctionnalités

- **Gestion des livres** :
    - Ajouter de nouveaux livres à la bibliothèque.
    - Rechercher des livres par titre, auteur ou ID (insensible à la casse).
    - Afficher tous les livres.
    - Mettre à jour ou supprimer des livres existants.

- **Gestion des membres** :
    - Inscrire de nouveaux membres.
    - Rechercher des membres par nom.
    - Afficher tous les membres.

- **Gestion des emprunts** :
    - Enregistrer les emprunts de livres par les membres.
    - Gérer le retour des livres empruntés.

## Prérequis

Pour exécuter ce projet, vous aurez besoin de :

- Java Development Kit (JDK) 11 ou plus récent
- PostgreSQL
- Pilote JDBC pour PostgreSQL
- Un IDE comme IntelliJ IDEA, Eclipse ou NetBeans (facultatif mais recommandé)

## Configuration de la base de données

1. Créez une nouvelle base de données pour le système de bibliothèque.
2. Exécutez les commandes SQL suivantes pour créer les tables nécessaires :

```sql
CREATE TABLE livres (
    id SERIAL PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    auteur VARCHAR(255) NOT NULL,
    categorie VARCHAR(255) NOT NULL,
    nombre_exemplaires INT NOT NULL
);

CREATE TABLE membres (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    date_adhesion DATE NOT NULL
);

CREATE TABLE emprunts (
    id SERIAL PRIMARY KEY,
    membre_id INT NOT NULL REFERENCES membres(id),
    livre_id INT NOT NULL REFERENCES livres(id),
    date_emprunt DATE NOT NULL,
    date_retour_prevue DATE NOT NULL,
    date_retour_effective DATE
);
```

## Installation

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/votreutilisateur/systeme-gestion-bibliotheque.git
   ```
2. Importez le projet dans votre IDE.
3. Configurez la connexion à la base de données dans la classe `DB.java` :
   ```java
   public class DB {
       public static Connection connect() throws SQLException {
           String url = "jdbc:postgresql://localhost:5432/votre_nom_de_base_de_donnees";
           String user = "votre_nom_d_utilisateur";
           String password = "votre_mot_de_passe";
           return DriverManager.getConnection(url, user, password);
       }
   }
   ```

4. Exécutez la classe `Main.java` pour démarrer l'application.

## Utilisation

- **Lancer l'application** : Exécutez `Main.java`.
- **Naviguer dans le menu** : Utilisez les options numériques pour naviguer dans les différentes fonctionnalités (Gestion des livres, Gestion des membres, Gestion des emprunts).
- **Quitter l'application** : Sélectionnez l'option `0` dans le menu principal.

## Contribution

1. Faites un fork du dépôt.
2. Créez une nouvelle branche pour votre fonctionnalité ou correction de bug.
3. Validez vos modifications avec un message descriptif.
4. Poussez vos modifications sur votre fork.
5. Ouvrez une pull request dans le dépôt original.

[//]: # (## Licence)

[//]: # ()
[//]: # (Ce projet est sous licence MIT. Consultez le fichier `LICENSE` pour plus de détails.)

## Contact

Pour toute question ou retour, veuillez contacter :


- **GitHub** : [https://github.com/kengnidoriane](https://github.com/kengnidoriane)

---

Merci d'utiliser le Système de Gestion de Bibliothèque !
