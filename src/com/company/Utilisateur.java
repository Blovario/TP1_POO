package com.company;

public class Utilisateur {
    private String nom;
    private String prenom;
    private Carte carte;
    private Compte compte;

    public Utilisateur(String nom, String prenom, Carte carte, Compte compte) {
        this.nom = nom;
        this.prenom = prenom;
        this.carte = carte;
        this.compte = compte;
    }

    public Utilisateur(Compte compte, Carte carte) {
        this.compte = compte;
        this.carte = carte;
        // Get the name of the user from compte.getTitulaireDuCompte()
        String[] nomPrenom = compte.getTitulaireDuCompte().split(" ");
        this.nom = nomPrenom[0];
        this.prenom = nomPrenom[1];
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Carte getCarte() {
        return carte;
    }

    public Compte getCompte() {
        return compte;
    }

    public String toString() {
        return "Utilisateur: " + nom + " " + prenom + " " + carte + " " + compte;
    }

    public boolean equals(Object o) {
        if (o instanceof Utilisateur) {
            Utilisateur u = (Utilisateur) o;
            return nom.equals(u.nom) && prenom.equals(u.prenom) && carte.equals(u.carte) && compte.equals(u.compte);
        } else {
            return false;
        }
    }

    public boolean getEstGerant() {
        return compte.getEstGerant();
    }
}
