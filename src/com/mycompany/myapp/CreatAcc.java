/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.User;
import static java.util.concurrent.ThreadLocalRandom.current;
import services.UserService;

/**
 *
 * @author ACER EXTENSA 15
 */
public class CreatAcc extends SideMenuBaseForm {
        public CreatAcc(Form previous, Resources res) {
        

        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new LoginForm(res).show());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        setTitle("Ajouter User");

        //poids,sodium,cholesterol,carbohydrate,protein,calories,nom
        TextField tfemail = new TextField("", "Email");
        TextField tfpass = new TextField("", "Password");
      
          
        

        Button btnValider = new Button("create account");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if ((tfemail.getText().length() == 0) || (tfpass.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        //Integer.parseInt(tfTaille.getText())
                         
                        User u = new User((tfemail.getText()),(tfpass.getText()));

 
                        if (UserService.getInstance().addUser(u)) {
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
 

        
        addAll(tfemail, tfpass, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ProfileForm(res).show());

    }

    CreatAcc(Resources theme) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


   @Override
    protected void showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
