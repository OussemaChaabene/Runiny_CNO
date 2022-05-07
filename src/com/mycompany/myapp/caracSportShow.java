/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.CaracSport;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;
import services.CaracService;

/**
 *
 * @author ASUS
 */
public class caracSportShow extends Form {
Form current;

    public caracSportShow(Resources res) {

        String data =CaracService.getInstance().getAllCaracs().toString();
        ArrayList<CaracSport>list = CaracService.getInstance().getAllCaracs();
        
        for(CaracSport c : list ) {
            
             Label taille= new Label();//taille,poids,prtoNeeds,calorieNeed,age,genre
             taille.setText("Taille : "+c.getTaille()+"\n");
             Label poids= new Label();
             poids.setText("Poids : "+c.getPoids()+"\n");
             Label besoinscal= new Label();
             besoinscal.setText("Besoins Calories : "+c.getCalorieNeed()+"\n");
             Label besoinsprot= new Label();
             besoinsprot.setText(c.getPrtoNeeds()+"");
             Label age= new Label();
             age.setText(c.getAge()+"");
             Label genre= new Label();
             genre.setText(c.getGenre()+"");
             
             addAll(taille,poids,besoinsprot,besoinscal,age,genre);
             
        }
        setTitle("List caracs");

        Label l= new Label();
        l.setText(data);
      
        Button btnAddCarac = new Button("Modifié Caracs");
        btnAddCarac.addActionListener(e-> new caracSportAdd(current,res).show());

        addAll(l,btnAddCarac);
    }

    
    


}
