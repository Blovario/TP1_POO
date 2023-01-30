package com.company;

public class ImprimanteRecu {
    private int papierRestant;

    public ImprimanteRecu(int papierRestant) {
        this.papierRestant = papierRestant;
    }

    public int getPapierRestant() {
        return papierRestant;
    }

    public void remplacerPapier(int nouveauPapier) {
        this.papierRestant = nouveauPapier;
    }

    public void imprimerRecu(String contenu) {
        if (papierRestant > 0) {
            System.out.println("Imprimant le reçu: " + contenu);
            papierRestant--;
        } else {
            System.out.println("Impossible d'imprimer, pas de papier disponible.");
        }
    }

    public void remplirPapiers(Integer nbPapiers, String filePath) {
        papierRestant += nbPapiers;
        CSVUtils.writePapiersRestants(papierRestant, filePath);

    }
}

