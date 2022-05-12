/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.publication;
import java.util.ArrayList;
import java.util.Date;
import services.publicationS;

/**
 *
 * @author DELL
 */
public class publicationform extends SideMenuBaseForm{
    Form current;

    public publicationform(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ProfileForm(res).show());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        setTitle("Liste des Publication");

        ArrayList<publication> list = publicationS.getInstance().getAllpub();
            
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (publication p : list) {
            
            System.out.println(p.toString());
            
            MultiButton fourLinesIcon = new MultiButton();
            fourLinesIcon.setTextLine1("Nom : " + p.getNom()+ "\n");
            fourLinesIcon.setTextLine2("description : " + p.getDescription()+ "\n");
            fourLinesIcon.setTextLine2("image : " + p.getImage()+ "\n");
            fourLinesIcon.setTextLine2("adresse : " + p.getAdresse()+ "\n");
            
            
            
            
            
            container.add(fourLinesIcon);
            
            
            //supprimer button
            Button btnSupPlat = new Button("Supprimer");
            btnSupPlat.setUIID("LoginButton");
            container.add(btnSupPlat);
            
         
            
            //click sup button
            btnSupPlat.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppression");

                if (dig.show("Suppression", "Vous voulez supprimer cet Seance ?", "Annuler", "Oui")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                }
                if (publicationS.getInstance().deleteCarac(p.getId())) {
                    System.out.println("Supprimé");
                    dig.dispose();        

                }
                //current.show();

            });
            
        }
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.addActionListener(e -> new publicationAdd(current, res).show());
        //fab.bindFabToContainer(current.getContentPane());

       /* Button btnAddSnc = new Button("Ajouter seance");
        btnAddSnc.setUIID("LoginButton");
        btnAddSnc.addActionListener(e -> new SncAdd(current, res).show());*/

        addAll(container,fab);
    }

    @Override
    protected void showOtherForm(Resources res) {
    }

    private String toString(Date date) {
        return date.toString();
    }

    
    
}
