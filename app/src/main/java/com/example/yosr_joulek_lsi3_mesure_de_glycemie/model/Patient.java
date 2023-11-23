package com.example.yosr_joulek_lsi3_mesure_de_glycemie.model;

public class Patient {
    private int age;
    private float value;
    private boolean isfasting;
    private String resultat;

    public Patient(int age, float value, boolean isfasting) {
        //appartient a la premier partie de update entre controller et view
        this.age = age;
        this.value = value;
        this.isfasting = isfasting;
        Calculer();
    }
    public String getResultat() {
        //appartient a la premier partie du notify entre model et controller (1/2)
        return resultat;
    }
    private void Calculer() {
        if (isfasting) {
            if (age >= 13) {
                if (value < 5.0) {
                    resultat = "Le niveau de glycemie est tres bas ";
                } else if (value >= 5.0 && value <= 7.2) {
                    resultat = "Le niveau de glycemie est normal ";
                } else {
                    resultat = "Le niveau de glycemie est elevé";
                }

                if (age >= 6 && age <= 12) {
                    if (value < 5.0) {
                        resultat = "Le niveau de glycemie est tres bas ";
                    } else if (value >= 5.0 && value <= 10.5) {
                        resultat = "Le niveau de glycemie est normal ";
                    } else {
                        resultat = "Le niveau de glycemie est elevé";
                    }
                } else if (value < 5.5) {
                    resultat = "Le niveau de glycemie est bas";
                } else if (value >= 5.0 && value <= 10.0) {
                    resultat = "Le niveau de glycemie est normal";
                } else {
                    resultat = "Le niveau de glycemie est elevé";
                }
            } else if (value < 10.5) {
                resultat = "Le niveau de glycemie est normal";
            } else {
                resultat = "Le niveau de glycemie est élevé";
            }

        }
    }

}
