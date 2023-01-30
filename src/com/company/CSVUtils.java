package com.company;

import java.io.*;
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
                System.out.println(carte[0] + " " + carte[1] + " " + carte[2] + " " + carte[3] + " " + carte[4] + " " + carte[5]);
                cartes.add(new Carte(carte[0], carte[1], carte[2], carte[3], carte[4], carte[5]));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cartes;
    }

    public static void writePapiersRestants(int newPapierRestant, String filePath) {
        System.out.println("Writing new papiers restants to file: " + newPapierRestant);
        // Réécris le fichier avec le nouveau nombre de papiers restants tout en ne changeant pas la premiere ligne
        // Skip the first line (header)
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            // Write the new line
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                bw.write(line);
                bw.newLine();
                bw.write(String.valueOf(newPapierRestant));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void writeBilletsRestants(HashMap<String, Integer> billetsStock, String filePath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            // Write the new line
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                bw.write(line);
                bw.newLine();
                for (Map.Entry<String, Integer> entry : billetsStock.entrySet()) {
                    System.out.println(entry.getKey() + " $CAD " + entry.getValue());
                    bw.write(entry.getKey() + "," + entry.getValue());
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
