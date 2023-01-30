package com.company;

public class Compte {


    private double solde;
    private String numeroDeCompte;
    private String titulaireDuCompte;
    private boolean estGerant;

    public Compte(String numeroDeCompte, String titulaireDuCompte) {
        this.solde = 0.0;
        this.numeroDeCompte = numeroDeCompte;
        this.titulaireDuCompte = titulaireDuCompte;
        this.estGerant = false;
    }


    public Compte(String numeroDeCompte, String titulaireDuCompte, String solde, String estGerant) {
        this.solde = Double.parseDouble(solde);
        this.numeroDeCompte = numeroDeCompte;
        this.titulaireDuCompte = titulaireDuCompte;
        this.estGerant = Boolean.parseBoolean(estGerant);
    }


    public String getTitulaireDuCompte() {
        return titulaireDuCompte;
    }

    public boolean deposer(double montant) {
        solde += montant;
        writeNewCompteFile("src/comptes.csv");
        return true;
    }

    public boolean retirer(double montant) {
        if (montant > solde) {
            return false;
        } else {
            solde -= montant;
            writeNewCompteFile("src/comptes.csv");
            return true;
        }
    }

    public boolean getEstGerant() {

        return estGerant;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Long getNumeroDeCompte() {
        return Long.parseLong(numeroDeCompte);
    }

    private void writeNewCompteFile(String filePath) {
        CSVUtils.writeCompte(this, filePath);
    }

    public Boolean transfertArgent(String numeroDeCompteDestinataire, int montantTransfert, String filePath) {
        Compte compteDestinataire = CSVUtils.getCompteByNumeroDeCompte(numeroDeCompteDestinataire, filePath);
        if (compteDestinataire != null) {
            if (this.retirer(montantTransfert)) {
                compteDestinataire.deposer(montantTransfert);
                return true;
            }
        }
        return false;
    }

    public void imprimerLivretBanque() {
        System.out.println("Titulaire du compte: " + titulaireDuCompte);
        System.out.println("Numéro de compte: " + numeroDeCompte);
        System.out.println("Solde: " + solde);
    }

    public boolean payerFacture(String referenceFacture, Double montantFacture) {
        if (this.retirer(montantFacture)) {
            CSVUtils.writeFacture(referenceFacture, montantFacture, "src/factures.csv");
            return true;
        } else {
            return false;
        }
    }
}

