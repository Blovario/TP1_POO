package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUtils {

    public static int getPapiersRecusRestants(String filePath) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int papiersRestants = 0;

        try {
            br = new BufferedReader(new FileReader(filePath));
            // Skip the first line (header)
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] quantite = line.split(cvsSplitBy);
                System.out.println(quantite[0]);
                papiersRestants = Integer.parseInt(quantite[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return papiersRestants;
    }


    public static Map<String, Integer> getBilletsRestants(String filePath) {
        Map<String, Integer> billetsRestants = new HashMap<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] billets = line.split(",");
                System.out.println(billets[0] + " " + Integer.valueOf(billets[1]));
                billetsRestants.put(billets[0], Integer.valueOf(billets[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return billetsRestants;
    }

    public static List<Compte> getComptes(String filePath) {
        List<Compte> comptes = new ArrayList<Compte>();
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] compte = line.split(cvsSplitBy);
                // Format du fichier CSV: numero, titulaire, solde, estGerant
                System.out.println(compte[0] + " " + compte[1] + " " + compte[2] + " " + compte[3]);
                comptes.add(new Compte(compte[0], compte[1], compte[2], compte[3]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return comptes;
    }

    public static List<Carte> getCartes(String filePath) {
        List<Carte> cartes = new ArrayList<Carte>();
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();
            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] carte = line.split(cvsSplitBy);
                // Format du fichier CSV: Numero, Code, Date, Solde, Type, Compte
                cartes.add(new Carte(carte[0], carte[1], carte[2], carte[3], carte[4], carte[5]));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cartes;
    }
}
