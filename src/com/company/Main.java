package com.company;
import java.util.HashMap;
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
            while (true) {
                System.out.println("1. Remplir les billets");
                System.out.println("2. Remplir les papiers pour reçus");
                System.out.println("3. Quitter");
                int choix = scanner.nextInt();
                switch (choix) {
                    case 1:
                        System.out.println("Combien de billets voulez-vous ajouter ?");
                        int nbBillets = scanner.nextInt();
                        System.out.println("Quel billet voulez-vous ajouter ?");
                        System.out.println("1. 5$");
                        System.out.println("2. 10$");
                        System.out.println("3. 20$");
                        System.out.println("4. 50$");
                        System.out.println("5. 100$");
                        switch(scanner.nextInt()) {
                            case 1:
                                remplirBillets(5, nbBillets);
                                break;
                            case 2:
                                remplirBillets(10, nbBillets);
                                break;
                            case 3:
                                remplirBillets(20, nbBillets);
                                break;
                            case 4:
                                remplirBillets(50, nbBillets);
                                break;
                            case 5:
                                remplirBillets(100, nbBillets);
                                break;
                            default:
                                System.out.println("Choix invalide, veuillez réessayer.");
                        }
                        break;
                    case 2:
                        System.out.println("Combien de papiers voulez-vous ajouter ?");
                        int nbPapiers = scanner.nextInt();
                        remplirPapiersRecus(nbPapiers, "src/papiersRecus.csv");
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");

                }
            }
        } else {
            Boolean aChoisi = false;
            while(true) {
                System.out.println("Bonjour, que voulez-vous faire ?");
                System.out.println("1. Retirer de l'argent");
                System.out.println("2. Déposer de l'argent");
                System.out.println("3. Payer une facture");
                System.out.println("4. Transférer de l'argent entre comptes");
                System.out.println("5. Changer votre code secret");
                System.out.println("6. Imprimer votre livret de banque");
                System.out.println("7. Quitter");
                int choix = scanner.nextInt();
                aChoisi = gererChoixUtilisateur(choix, scanner, utilisateur, papiersRecusRestants);
                if(aChoisi){
                    System.out.println("Voulez-vous un reçu? (O/N)");
                    String reponse = scanner.next();
                    if (reponse.equals("O")) {
                        imprimanteRecu.imprimerRecu("Reçu de " + utilisateur.getPrenom() + " " + utilisateur.getNom());
                    }
                }
            }


        }
    }

    private static void remplirPapiersRecus(Integer nbPapiers, String filePath) {
        int ancienPapierRestant = CSVUtils.getPapiersRecusRestants("src/papiersRecus.csv");
        ImprimanteRecu imprimanteRecu = new ImprimanteRecu(ancienPapierRestant);
        imprimanteRecu.remplirPapiers(nbPapiers, filePath);

    }

    private static void remplirBillets(Integer typeBillet, Integer nbBillets) {
        Billet billet = new Billet((HashMap<String, Integer>) CSVUtils.getBilletsRestants("src/billets.csv"));
        int ancienBilletsRestant = billet.getBilletsRestants(String.valueOf(typeBillet));
        billet.remplirBillets(String.valueOf(typeBillet), nbBillets, "src/billets.csv");

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


    private static Boolean gererChoixUtilisateur(int choix, Scanner scanner, Utilisateur utilisateur, int papiersReçusRestants) {
        switch (choix){
            case 1:
                System.out.println("Combien voulez-vous retirer?");
                System.out.println("1. 5$");
                System.out.println("2. 10$");
                System.out.println("3. 20$");
                System.out.println("4. 50$");
                System.out.println("5. 100$");
                int montant = scanner.nextInt();
                while(montant < 1 || montant > 5){
                    System.out.println("Choix invalide, veuillez réessayer.");
                    montant = scanner.nextInt();
                }
                switch (montant) {
                    case 1:
                        montant = 5;
                        break;
                    case 2:
                        montant = 10;
                        break;
                    case 3:
                        montant = 20;
                        break;
                    case 4:
                        montant = 50;
                        break;
                    case 5:
                        montant = 100;
                        break;
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                }
                if(utilisateur.getCompte().retirer(montant)){
                    System.out.println("Retrait effectué avec succès.");
                    return true;
                } else {
                    System.out.println("Retrait échoué.");
                }
                break;
            case 2:
                System.out.println("Combien voulez-vous déposer?");
                int montantDepot = scanner.nextInt();
                if(utilisateur.getCompte().deposer(montantDepot)){
                    System.out.println("Dépôt effectué avec succès.");
                    return true;
                } else {
                    System.out.println("Dépôt échoué.");
                }
                break;
            case 3:
                System.out.println("Quel est la référence de la facture?");
                Long referenceFacture = scanner.nextLong();
                System.out.println("Quel est le montant de la facture?");
                Double montantFacture = scanner.nextDouble();
                if(utilisateur.getCompte().payerFacture(String.valueOf(referenceFacture), montantFacture)){
                    System.out.println("Paiement effectué avec succès.");
                    return true;
                } else {
                    System.out.println("Paiement échoué.");
                }
                break;
            case 4:
                System.out.println("Quel est le numéro de compte du destinataire?");
                Integer numeroDeCompteDestinataire = scanner.nextInt();
                System.out.println("Quel est le montant à transférer?");
                int montantTransfert = scanner.nextInt();
                if(utilisateur.getCompte().transfertArgent(String.valueOf(numeroDeCompteDestinataire), montantTransfert,"src/comptes.csv")){
                    System.out.println("Transfert effectué avec succès.");
                    return true;
                } else {
                    System.out.println("Transfert échoué.");
                }

                break;
            case 5:
                System.out.println("Veuillez entrer votre nouveau code secret : ");
                Integer nouveauCodeSecret = scanner.nextInt();
                if(utilisateur.getCarte().setCodeSecret(String.valueOf(nouveauCodeSecret))){
                    System.out.println("Code secret modifié avec succès.");
                    return true;
                } else {
                    System.out.println("Code secret non modifié.");
                }

                break;
            case 6:
                utilisateur.getCompte().imprimerLivretBanque();
                break;
            case 7:
                System.exit(0);
            default:
                System.out.println("Choix invalide, veuillez réessayer.");
        }
        return false;
    }
}


