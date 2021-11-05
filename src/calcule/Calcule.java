/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calcule;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author User
 */
public class Calcule {

    public static double calculeMontantValeurLot(int typeTerrain, double superficie, double prixMin, double prixMax) {
        double prixMoy = 0;
        double calc=0;
        if (typeTerrain == 0) {
            calc=superficie * prixMin;
            
        } else if (typeTerrain == 1) {
            prixMoy = (prixMax + prixMin) / 2;
            calc=superficie * prixMoy;
            
        } else if (typeTerrain == 2) {
            calc= superficie * prixMax;
        }
        return  Math.round(calc * 100.0) / 100.0;
       
    }

    public static double calculeMontantDroitPassage(int typeTerrain,int nbrDroitPassages,double valeurLot) {

        if (typeTerrain == 0) {
            return 500-(nbrDroitPassages *0.05*valeurLot);
        } else if (typeTerrain == 1) {
            return 500-(nbrDroitPassages *0.1*valeurLot);            
        } else if(typeTerrain==2){
           return 500-(nbrDroitPassages *0.15*valeurLot); 
        }       
        return 0;       
      
    }

    public static double calculeMontanServices(int typeTerrain, double superficie, int nbrServices) {
        if (typeTerrain == 0) {
            return 0;
        } else if (typeTerrain == 1) {
            if (superficie <= 500) {
                return 0;
            } else if ((superficie > 500) && (superficie <= 10000)) {
                return 500 * nbrServices;
            } else if (superficie > 10000) {
                return 1000 * nbrServices;
            }
            
        } else if (superficie == 2) {
            if (superficie <= 500) {
                double calcul=500*nbrServices;
                if (calcul>5000) {
                     calcul=5000;   
                    }
                return calcul;
            } else if (superficie > 500) {
               double calcul=1500*nbrServices;
                if (calcul>5000) {
                     calcul=5000;   
                    }
                return calcul;
            } 
            
        }
        return 0;
    }
//    public static double  valeurFoncièreTerrain1(JSONObject terrainSortie){
//        double somme=0;
//        JSONArray lotissements= terrainSortie.getJSONArray("lotissements");
//        JSONObject lot=new JSONObject();
//        for (int i = 0; i < lotissements.size(); i++) {
//           lot = (JSONObject) lotissements.get(i);
//           
//           String str="0.0$";/*valeur initiale doit etre au format "0.0$"*/
//           str=lot.getString("valeur_par_lot").substring(0, str.length()-2);
//           double val=Double.parseDouble(str);
//         somme=somme+val;
//        }
//        
//        return somme;
//        
//    }
    public static double  valeurFoncièreTerrain(JSONArray lotissementsSorite){
        double somme=0;       
        JSONObject lot=new JSONObject();
        for (int i = 0; i < lotissementsSorite.size(); i++) {
           lot = (JSONObject) lotissementsSorite.get(i);
           
           String str="0.00$";/*valeur initiale doit etre au format "0.0$"*/
           str=lot.getString("valeur_par_lot").substring(0, str.length()-2);
           double val=Double.parseDouble(str);
         somme=somme+val;
        }
        
        return somme;
        
    }
    
    public static double  valeurFoncièreTerrainTotale(double valeurFoncTerrain){
        
        return Math.round((valeurFoncTerrain+733.77) * 100.0) / 100.0;
        
    }
    
    public static double taxeScolaire(double valeurFoncTerrainTotale){
    
        return Math.round((valeurFoncTerrainTotale*0.012) * 100.0) / 100.0;
    
    }
   public static double taxeMunicipale(double valeurFoncTerrainTotale){
   
        return Math.round((valeurFoncTerrainTotale*0.025) * 100.0) / 100.0;
   
   }
}
