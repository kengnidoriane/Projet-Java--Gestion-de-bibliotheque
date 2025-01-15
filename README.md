# Système de Gestion de Bibliothèque

Bienvenue dans le Système de Gestion de Bibliothèque ! Ce projet est une application Java conçue pour gérer efficacement les livres, les membres et les emprunts d'une bibliothèque. Le système offre des fonctionnalités telles que l'ajout et la recherche de livres, la gestion des informations des membres et la gestion des emprunts et des retours de livres.

## Table des Matières

- [Fonctionnalités](#fonctionnalités)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Utilisation](#utilisation)
- [Contribution](#contribution)
- [Contact](#contact)

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
  - afficher les emprunts en cours.

## Prérequis

Pour exécuter ce projet, vous aurez besoin de :

- Java Development Kit (JDK) 11 ou plus récent
- PostgreSQL
- Pilote JDBC pour PostgreSQL
- Un IDE comme IntelliJ IDEA, Eclipse ou NetBeans (facultatif mais recommandé)

## Installation

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/kengnidoriane/systeme-gestion-bibliotheque.git
   ```
2. Importez le projet dans votre IDE.
3. Importez la base de données à partir du fichier SQL contenu dans le dossier ressources du projet :
  - Placez le fichier SQL dans un répertoire accessible.
  - Exécutez la commande suivante dans le terminal :
    ```bash
    psql -U votre_utilisateur -d votre_base_de_donnees -f chemin/vers/fichier.sql
    ```
4. Configurez la connexion à la base de données dans le fichier `db.properties` :
   ```properties
   db.url=jdbc:postgresql://localhost:5432/votre_nom_de_base_de_donnees
   db.username=votre_nom_utilisateur
   db.password=votre_mot_de_passe
   ```
   **NB** : Remplissez ces champs sans ajouter d'espaces ou d'autres caractères.
5. Exécutez la classe `Main.java` pour démarrer l'application.

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

## Contact

Pour toute question ou retour, veuillez contacter :

- **GitHub** : [https://github.com/kengnidoriane](https://github.com/kengnidoriane)

---

Merci d'utiliser le Système de Gestion de Bibliothèque !

