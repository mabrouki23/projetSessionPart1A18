/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetsessionpart1a18;

import calcule.Calcule;
import java.io.IOException;
import java.text.DecimalFormat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utilies.FileReader;
import utilies.FileWriter;

/**
 *
 * @author User
 */
public class ProjetSessionPart1A18 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String filePath = "json/TerrainEntree.json";
        String filePathSortie="json/terrainSortie.json";
        String encoding = "utf-8";
       String myJSON = FileReader.laodFileIntoString(filePath,encoding);
       JSONObject terrainEntree = JSONObject.fromObject(myJSON);
       int typeTerrain=terrainEntree.getInt("type_terrain");
       String prix_m2_min=terrainEntree.getString("prix_m2_min");
       double prixMin=Double.parseDouble(prix_m2_min.substring(0, prix_m2_min.length()-2));        
       String prix_m2_max=terrainEntree.getString("prix_m2_max");
       double prixMax=Double.parseDouble(prix_m2_max.substring(0, prix_m2_max.length()-2));
        
        JSONArray lotissements = terrainEntree.getJSONArray("lotissements");
       JSONArray lotissementsSortie = new JSONArray();
        JSONObject lot;
        JSONObject lotsortie = new JSONObject();
        JSONObject terrainsortie = new JSONObject();
        
        String descripttion;
        String vateurParLot;  
        /*pour formater le nombre en 2 chiffre apres vergule*/
        DecimalFormat df = new DecimalFormat("0.00$");
        for (int i = 0; i < lotissements.size(); i++) {
            lot = lotissements.getJSONObject(i);
            descripttion = lot.getString("description");
            int nbrDroitPassage=lot.getInt("nombre_droits_passage");
            int nbrServices=lot.getInt("nombre_services");
            double superficie=lot.getDouble("superficie");
            /* creation de l'objet sortie*/
            lotsortie.accumulate("description", descripttion);
            /*besoin de formatage*/
            lotsortie.accumulate("valeur_par_lot",
                   df.format(Calcule.calculeMontantValeurLot(typeTerrain,superficie,prixMin,prixMax)));
            lotissementsSortie.add(lotsortie);          
           
            lotsortie.clear();        
        }
        /*remplissage de l'objet terrain sortie*/       
        
        double valeurFonciereTerrain=Calcule.valeurFoncièreTerrain(lotissementsSortie);
       
        double valeurFonciereTotle=Calcule.valeurFoncièreTerrainTotale(valeurFonciereTerrain);
        /*besoin de formatage*/
        terrainsortie.accumulate("valeur_fonciere_totale", df.format(valeurFonciereTotle));        
        double taxScolaire= Calcule.taxeScolaire(valeurFonciereTotle);
        /*besoin de formatage*/
        terrainsortie.accumulate("taxe_scolaire", df.format(taxScolaire));
        double taxMunicipale=Calcule.taxeMunicipale(valeurFonciereTotle);
        /*besoin de formatage*/
        terrainsortie.accumulate("taxe_ municipale", df.format(taxMunicipale));
        terrainsortie.accumulate("lotissements",lotissementsSortie);
        FileWriter.saveStringIntoFile(filePathSortie, terrainsortie.toString());
        System.out.println("fichier terrainSortie est generé dans le dossier json");
        System.out.println(terrainsortie.toString());   
        terrainsortie.clear();
       

        
    }
    
}
