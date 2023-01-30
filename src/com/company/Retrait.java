package com.company;

public class Retrait {
    private int billetsRestant;

    public Retrait(int billetsRestant) {
        this.billetsRestant = billetsRestant;
    }

    public int getBilletsRestant() {
        return billetsRestant;
    }

    public void remplirBillets(int nouveauxBillets) {
        this.billetsRestant += nouveauxBillets;
    }

    public boolean retirerArgent(int montant) {
        if (billetsRestant >= montant) {
            billetsRestant -= montant;
            System.out.println(montant + "€ ont été retirés. Il reste " + billetsRestant + " billets dans le distributeur.");
            return true;
        } else {
            System.out.println("Impossible de retirer ce montant, pas assez de billets disponibles.");
            return false;
        }
    }
}
