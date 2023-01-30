package com.company;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Récupération des données à partir des fichiers CSV

        int papiersRecusRestants = CSVUtils.getPapiersRecusRestants("src/papiersRecus.csv");
        Map<String, Integer> billetsRestants = CSVUtils.getBilletsRestants("src/billets.csv");
        List<Compte> comptes = CSVUtils.getComptes("src/comptes.csv");
        List<Carte> cartes = CSVUtils.getCartes("src/cartes.csv");

        //Print les données récupérées
        System.out.println("Papiers recus restants: " + papiersRecusRestants);
        System.out.println("Billets restants: " + billetsRestants);
        System.out.println("Comptes: " + comptes);
        System.out.println("Cartes: " + cartes);


        // Création de l'imprimante de reçus
        ImprimanteRecu imprimanteRecu = new ImprimanteRecu(papiersRecusRestants);


        // Demande de connexion à l'utilisateur
        System.out.println("Veuillez entrer votre numéro de carte bancaire : ");
        String numCarte = scanner.nextLine();
        System.out.println("Veuillez entrer votre code secret : ");
        String codeSecret = scanner.nextLine();

        // Vérification de l'utilisateur
        Utilisateur utilisateur = verifierUtilisateur(numCarte, codeSecret, comptes, cartes);
        if (utilisateur == null) {
            System.out.println("Numéro de carte ou code secret incorrect. Veuillez réessayer.");
            return;
        }

        System.out.println("Bonjour " + utilisateur.getPrenom() + " " + utilisateur.getNom() + ", que voulez-vous faire ?");
        // Si c'est un gérant
        if (utilisateur.getEstGerant()) {
            System.out.println("Bonjour gérant, que voulez-vous faire ?");
            System.out.println("1. Remplir les billets");
            System.out.println("2. Remplir les papiers pour reçus");
            int choix = scanner.nextInt();
            if (choix == 1) {
                System.out.println("Combien de billets voulez-vous ajouter ?");
                int nbBillets = scanner.nextInt();
                System.out.println("Quel billet voulez-vous ajouter ?");
                System.out.println("1. 5$");
                System.out.println("2. 10$");
                System.out.println("3. 20$");
                System.out.println("4. 50$");
                System.out.println("5. 100$");
                int typeBillet = scanner.nextInt();

                remplirBillets(typeBillet, nbBillets);
            } else if (choix == 2) {
                System.out.println("Combien de papiers voulez-vous ajouter ?");
                int nbPapiers = scanner.nextInt();
                remplirPapiersReçus(nbPapiers, "src/papiersRecus.csv");
            }
        } else {
            System.out.println("Bonjour, que voulez-vous faire ?");
            System.out.println("1. Retirer de l'argent");
            System.out.println("2. Déposer de l'argent");
            System.out.println("3. Payer une facture");
            System.out.println("4. Transférer de l'argent entre comptes");
            System.out.println("5. Changer votre code secret");
            System.out.println("6. Imprimer votre livret de banque");
            int choix = scanner.nextInt();
            gererChoixUtilisateur(choix, scanner, utilisateur, papiersRecusRestants);
        }
    }

    private static void remplirPapiersReçus(Integer nbPapiers, String filePath) {
        ImprimanteRecu imprimanteRecu = new ImprimanteRecu(nbPapiers);
        imprimanteRecu.remplirPapiers(nbPapiers, filePath);

    }

    private static void remplirBillets(Integer typeBillet, Integer nbBillets) {
    }

    private static Utilisateur verifierUtilisateur(String numCarte, String codeSecret, List<Compte> comptes, List<Carte> cartes) {
        // Associer la carte entrée avec un compte si le code est correct
        // Pour chaque objet de la liste de carte, comparer le code et le numéro de carte entrée afin de vérifier si la carte est bonne
        for (Carte carte : cartes) {
            if (carte.getNumeroDeCarte().equals(numCarte) && carte.getCodeSecret().equals(codeSecret)) {
                // Si la carte est bonne, on associe la carte avec un compte
                for (Compte compte : comptes) {
                    if (compte.getNumeroDeCompte().equals(carte.getNumeroDeCompte())) {
                        // Si le compte est bon, on retourne l'utilisateur
                        return new Utilisateur(compte, carte);
                    }
                }
            }
        }



        return null;
    }


    private static void gererChoixUtilisateur(int choix, Scanner scanner, Utilisateur utilisateur, int papiersReçusRestants) {
        System.out.println("Choix: " + choix);
    }
}


