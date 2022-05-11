/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.CaracSport;
import entities.Seance;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.statics;

/**
 *
 * @author mayro
 */
public class SncService {
    public ArrayList<Seance> snc;

    public static SncService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private SncService() {
        req = new ConnectionRequest();
    }

    public static SncService getInstance() {
        if (instance == null) {
            instance = new SncService();
        }
        return instance;
    }

    public ArrayList<Seance> parsesnc(String jsonText) {
        try {
            snc = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                Seance cs = new Seance();
                cs.setDate((LocalDateTime)(obj.get("date")));
                cs.setTypeseance(((String)(obj.get("typeseance").toString())));       
                snc.add(cs);
            }

        } catch (IOException ex) {

        }
        return snc;
    }

    public ArrayList<Seance> getAllSeance() {

        String url = statics.BASE_URL + "reservation/allSeance";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                snc = parsesnc(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return snc;
    }

    public boolean addSnc(Seance cs) {
        System.out.println(cs.toString());
        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = statics.BASE_URL + "reservation/addSeanceJSON/new";

        req.setUrl(url);

        req.addArgument("date", cs.getDate() + "");
        req.addArgument("typeSeance", cs.getTypeseance() + "");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean deleteSnc(int id) {
        String url = statics.BASE_URL + "seance/deleteSeanceJSON/" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    /*public boolean modifierCarac(CaracSport c) {
        String url = statics.BASE_URL + "/j/modifCarac?id=" + c.getId() + "&age=" + c.getAge() + "&taille=" + c.getTaille() + "&poids=" + c.getPoids() + "&genre=" + c.getGenre();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOK;

    }*/
}

