/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.CaracSport;
import java.util.ArrayList;
import services.CaracService;

/**
 *
 * @author ASUS
 */
public class caracSportShow extends Form {

    Form current;

    public caracSportShow(Resources res) {

        ArrayList<CaracSport> list = CaracService.getInstance().getAllCaracs();

        for (CaracSport c : list) {

            Label taille = new Label();//taille,poids,prtoNeeds,calorieNeed,age,genre
            taille.setText("-Taille : " + c.getTaille() + "\n");
            Label poids = new Label();
            poids.setText("-Poids : " + c.getPoids() + "\n");
            Label besoinscal = new Label();
            besoinscal.setText("-Besoins Calories : " + c.getCalorieNeed() + "\n");
            Label besoinsprot = new Label();
            besoinsprot.setText("-Besoins Proteins : " + c.getPrtoNeeds() + "\n");
            Label age = new Label();
            age.setText("-Age : " + c.getAge() + "\n");
            Label genre = new Label();
            genre.setText("-Sexe : " + c.getGenre() + "\n");
            Button btnModifCarac = new Button("Modifié Carac");
            btnModifCarac.setUIID("LoginButton");

            //supprimer button
            Label lSupprimer = new Label(" ");
            lSupprimer.setUIID("NewsTopLine");
            Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
            supprmierStyle.setFgColor(0xf21f1f);

            FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
            lSupprimer.setIcon(suprrimerImage);
            lSupprimer.setTextPosition(RIGHT);

            //click delete icon
            lSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppression");

                if (dig.show("Suppression", "Vous voulez supprimer ce reclamation ?", "Annuler", "Oui")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                }
                if (CaracService.getInstance().deleteCarac(c.getId())) {
                    System.out.println("supprimé");
                    current.repaint();
                    current.refreshTheme();
                }

            });
            Label sep = new Label("\n------------------------------------");
            addAll(btnModifCarac, taille, poids, besoinsprot, besoinscal, age, genre, sep,lSupprimer);
        }
        setTitle("Liste caracs");

        Button btnAddCarac = new Button("Ajouter Carac");
        btnAddCarac.setUIID("LoginButton");
        btnAddCarac.addActionListener(e -> new caracSportAdd(current, res).show());

        addAll(btnAddCarac);
    }

}
