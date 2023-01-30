package com.company;

public class Carte {
    private String numeroDeCarte;
    private String codeSecret;
    private String dateExpiration;
    private String solde;
    private String typeDeCarte;
    private String compte;

    public Carte(String numeroDeCarte, String codeSecret, String dateExpiration, String solde, String typeDeCarte, String compte) {
        this.numeroDeCarte = numeroDeCarte;
        this.codeSecret = codeSecret;
        this.dateExpiration = dateExpiration;
        this.typeDeCarte = typeDeCarte;
        this.solde = solde;
        this.compte = compte;
    }

    public String getNumeroDeCarte() {
        return numeroDeCarte;
    }

    public String getCodeSecret() {
        return codeSecret;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public String getTypeDeCarte() {
        return typeDeCarte;
    }

    public Long getNumeroDeCompte() {
        return Long.parseLong(compte);
    }
}
