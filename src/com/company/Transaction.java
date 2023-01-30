package com.company;

import java.util.Date;

public class Transaction {
    private Date date;
    private Compte compteBancaire;
    private TransactionType transactionType;
    private double montant;

    public Transaction(Compte compteBancaire, TransactionType typeTransaction, double montant) {
        this.date = new Date();
        this.compteBancaire = compteBancaire;
        this.transactionType = typeTransaction;
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public Compte getCompteBancaire() {
        return compteBancaire;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public double getMontant() {
        return montant;
    }
}
