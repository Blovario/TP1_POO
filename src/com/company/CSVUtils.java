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

    public static void writePapiersRestants(int newPapierRestant, String filePath) {
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

    public static void writeCompte(Compte compte, String filePath){
        // Finds the line with the same account number and replace it with the new one (with the new balance)
        // Skip the first line (header)
        // Format du fichier CSV: numero, titulaire, solde, estGerant

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line = br.readLine();
            // Write the new line
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
                bw.write(line);
                bw.newLine();
                while((line = br.readLine()) != null){
                    String[] compteLigne = line.split(",");
                    if(Long.valueOf(compteLigne[0]).equals(compte.getNumeroDeCompte())) {
                        bw.write(compte.getNumeroDeCompte() + "," + compte.getTitulaireDuCompte() + "," + compte.getSolde() + "," + compte.getEstGerant());
                        bw.newLine();
                    } else {
                        bw.write(line);
                        bw.newLine();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Compte getCompteByNumeroDeCompte(String numeroDeCompteDestinataire, String filePath) {
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] compte = line.split(cvsSplitBy);
                // Format du fichier CSV: numero, titulaire, solde, estGerant
                if(compte[0].equals(numeroDeCompteDestinataire)) {
                    return new Compte(compte[0], compte[1], compte[2], compte[3]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void writeFacture(String referenceFacture, Double montantFacture, String filePath) {

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            // Write the new line
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                bw.write(line);
                bw.newLine();
                while ((line = br.readLine()) != null) {
                    String[] factureLigne = line.split(",");
                    if (factureLigne[0].equals(referenceFacture)) {
                        bw.write(referenceFacture + "," + montantFacture +","+ factureLigne[2] + ",true");
                        bw.newLine();
                    } else {
                        bw.write(line);
                        bw.newLine();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeNewCarteFile(String codeSecret, String numeroDeCarte, String filePath) {
        // Change le code secret de la carte dans le fichier CSV
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line = br.readLine();
            // Write the new line
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
                bw.write(line);
                bw.newLine();
                while((line = br.readLine()) != null){
                    String[] carteLigne = line.split(",");
                    if(carteLigne[0].equals(numeroDeCarte)) {
                        bw.write(numeroDeCarte + "," + codeSecret + "," + carteLigne[2] + "," + carteLigne[3] + "," + carteLigne[4] + "," + carteLigne[5]);
                        bw.newLine();
                    } else {
                        bw.write(line);
                        bw.newLine();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
