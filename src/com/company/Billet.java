package com.company;

import java.util.HashMap;

public class Billet {

    // Dictionnaire pour stocker le type de billet et son nombre
    private HashMap<String, Integer> billetsStock;

    public Billet() {
        // Initialisation du dictionnaire
        this.billetsStock = new HashMap<String, Integer>();
    }

    public Billet(HashMap<String, Integer> billetsStock) {
        // Initialisation du dictionnaire
        this.billetsStock = billetsStock;
    }

    // Méthode pour ajouter des billets de type donné
    public void ajouterBillets(String type, int nombre) {
        if (billetsStock.containsKey(type)) {
            // Si le type de billet existe déjà, on ajoute le nombre de billets donné
            billetsStock.put(type, billetsStock.get(type) + nombre);
        } else {
            // Sinon, on ajoute le type de billet avec le nombre donné
            billetsStock.put(type, nombre);
        }
    }

    // Méthode pour retirer des billets de type donné
    public void retirerBillets(String type, int nombre) {
        if (billetsStock.containsKey(type)) {
            // Si le type de billet existe, on retire le nombre de billets donné
            int billetsRestants = billetsStock.get(type) - nombre;
            if (billetsRestants < 0) {
                // Si on retire plus de billets qu'il n'y en a, on met le nombre de billets à 0
                billetsStock.put(type, 0);
            } else {
                billetsStock.put(type, billetsRestants);
            }
        }
    }

    // Méthode pour vérifier la disponibilité des billets de type donné
    public boolean verifierDisponibiliteBillets(String type, int nombre) {
        if (billetsStock.containsKey(type)) {
            // Si le type de billet existe, on vérifie si le nombre de billets demandé est disponible
            return billetsStock.get(type) >= nombre;
        } else {
            // Si le type de billet n'existe pas, il n'y a pas de billets disponibles
            return false;
        }
    }

    // Méthode pour obtenir le nombre de billets de type donné
    public int getBilletsRestants(String type) {
        if (billetsStock.containsKey(type)) {
            // Si le type de billet existe, on retourne le nombre de billets
            return billetsStock.get(type);
        } else {
            // Si le type de billet n'existe pas, on retourne 0
            return 0;
        }
    }

    // Methode pour remplir le stock de billets d'un type donné
    public void remplirBillets(String type, int nombre, String filePath) {
        // On ajoute le nombre de billets donné
        ajouterBillets(type, nombre);
        // On écrit le nouveau nombre de billets dans le fichier CSV
        CSVUtils.writeBilletsRestants(billetsStock, filePath);
    }
}
