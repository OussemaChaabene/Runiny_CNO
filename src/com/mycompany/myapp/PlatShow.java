/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.*;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Plat;
import java.util.ArrayList;
import services.PlatService;

/**
 *
 * @author ASUS
 */
public class PlatShow extends Form{
    
    Form current;

    public PlatShow(Resources res) {

        ArrayList<Plat> list = PlatService.getInstance().getAllPlats();

        for (Plat p : list) {
            //id,poids,sodium,cholesterol,carbohydrate,protein,calories,nom
            Label nom = new Label();
            nom.setText("-Taille : " + p.getNom()+ "\n");
            Label poids = new Label();
            poids.setText("-Poids : " + p.getPoids()+ "\n");
            Label calories = new Label();
            calories.setText("-Calories : " + p.getCalories()+ "\n");
            Label protein = new Label();
            protein.setText("-Proteins : " + p.getProtein()+ "\n");
            Label sodium = new Label();
            sodium.setText("-sodium : " + p.getSodium()+ "\n");
            Label cholesterol = new Label();
            cholesterol.setText("-cholesterol : " + p.getCholesterol()+ "\n");
            Label carbohydrate = new Label();
            carbohydrate.setText("-carbohydrate : " + p.getCarbohydrate()+ "\n");
            Button btnModifPlat = new Button("Modifier Plat");
            btnModifPlat.setUIID("LoginButton");

            //supprimer button
            Label lSupprimer = new Label(" ");
            lSupprimer.setUIID("NewsTopLine");
            Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
            supprmierStyle.setFgColor(0xf21f1f);

            FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
            lSupprimer.setIcon(suprrimerImage);

            //click delete icon
            lSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppression");

                if (dig.show("Suppression", "Vous voulez supprimer ce reclamation ?", "Annuler", "Oui")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                }
                if (PlatService.getInstance().deletePlat(p.getId())) {
                    System.out.println("supprimé");
                    current.repaint();
                    current.refreshTheme();
                }

            });
            Label sep = new Label("\n------------------------------------");
            
            addAll(btnModifPlat, poids,sodium,cholesterol,carbohydrate,protein,calories,nom, sep,lSupprimer);
        }

        Button btnAddPlat = new Button("Ajouter Plat");
        btnAddPlat.setUIID("LoginButton");
        btnAddPlat.addActionListener(e -> new PlatAdd(current, res).show());

        addAll(btnAddPlat);
    }

    
}
