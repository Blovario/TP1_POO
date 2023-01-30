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

    public double obtenirSolde() {
        return solde;
    }



    public String getTitulaireDuCompte() {
        return titulaireDuCompte;
    }

    public void deposer(double montant) {
        solde += montant;
    }

    public boolean retirer(double montant) {
        if (montant > solde) {
            return false;
        } else {
            solde -= montant;
            return true;
        }
    }

    public boolean getEstGerant() {

        return estGerant;
    }

    public Long getNumeroDeCompte() {
        return Long.parseLong(numeroDeCompte);
    }
}

