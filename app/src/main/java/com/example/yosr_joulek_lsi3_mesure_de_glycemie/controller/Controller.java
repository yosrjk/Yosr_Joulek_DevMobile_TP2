package com.example.yosr_joulek_lsi3_mesure_de_glycemie.controller;
import com.example.yosr_joulek_lsi3_mesure_de_glycemie.model.Patient;
public final class Controller {
    private static Controller instance=null;
   private static Patient patient ;
    // fait une partie de user action entre view et controller (1/2)
    //les parametres font passer les valeurs
   public void CreatePatient(int age,float value,boolean isfasting){
       //fait la 2 eme partie du update entre controller et model
       patient =new Patient(age,value,isfasting);
   }
    public Controller(){
       // appartient au user action
       super();
    }
    public static  final Controller getInstance(){
       if(Controller.instance==null)
           Controller.instance=new Controller();
       return Controller.instance;
    }
     public String getResultat(){
         return patient.getResultat();
         //fait la 1 ere partie du notify entre controller et view (1/2)
         //fait la 2 eme partie du notify entre model et controller(2/2)
     }

}
