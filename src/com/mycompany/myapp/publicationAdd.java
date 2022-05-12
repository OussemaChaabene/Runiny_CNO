/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import entities.publication;

import services.publicationS;
/**
 *
 * @author DELL
 */
public class publicationAdd extends SideMenuBaseForm{
   
    public publicationAdd(Form previous, Resources res){
        
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new publicationform(res).show());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        setTitle("Ajouter Publication");

       
        
        TextField nom = new TextField("", "Nom");
        TextField description = new TextField("", "description");
        TextField adresse = new TextField("", "adresse");
        

        

        Button btnValider = new Button("Ajouter Seance");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if ((nom.getText().length() == 0)   || (description.getText().length() == 0)  || (adresse.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                         
                                publication p = new publication( adresse.getText(), description.getText() , nom.getText());
 
                        if (publicationS.getInstance().addpub(p)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Erreur !!", new Command("OK"));
                    }

                }

            }
        });
 

        addAll(nom,description,adresse, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ProfileForm(res).show());

    }


   @Override
    protected void showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    
    
    
    

