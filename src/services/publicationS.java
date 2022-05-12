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
import entities.publication;
import utils.statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author DELL
 */
public class publicationS {
    
     public ArrayList<publication> pub;

    public static publicationS instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private publicationS() {
        req = new ConnectionRequest();
    }

    public static publicationS getInstance() {
        if (instance == null) {
            instance = new publicationS();
        }
        return instance;
    }

    public ArrayList<publication> parsepub(String jsonText) {
        try {
            pub = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                publication pb = new publication();
                pb.setId((int) Float.parseFloat(obj.get("id").toString()));
                pb.setNom(obj.get("nom").toString());              
                pb.setDescription(obj.get("description").toString());
                pb.setImage(obj.get("image").toString());
                pb.setAdresse(obj.get("adresse").toString());
                
                

               

               pub.add(pb);
            }

        } catch (IOException ex) {

        }
        return pub;
    }

    public ArrayList<publication> getAllpub() {

        String url = statics.BASE_URL + "j/caracs";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                pub = parsepub(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return pub;
    }

    public boolean addpub(publication pb) {
        System.out.println(pb.toString());
        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = statics.BASE_URL + "j/addCarac";

        req.setUrl(url);

        req.addArgument("nom", pb.getNom() + "");
        req.addArgument("description", pb.getDescription() + "");
        req.addArgument("adresse", pb.getAdresse() + "");
        req.addArgument("Image", pb.getImage());

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

    public boolean deleteCarac(int id) {
        String url = statics.BASE_URL + "j/suppCarac/" + id;

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

    public boolean modifierpub(publication pb) {
        String url = statics.BASE_URL + "/j/modifCarac?&nom=" + pb.getNom() + "&description=" + pb.getDescription() + "&image=" + pb.getImage() + "&adresse=" + pb.getAdresse();
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

    }
    
    
    
    
}
