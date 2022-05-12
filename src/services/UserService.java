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
import com.codename1.l10n.ParseException;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import utils.UserSession;
import utils.statics;

/**
 *
 * @author ACER EXTENSA 15
 */
public class UserService {
         public ArrayList<User> users;

    public static UserService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private UserService() {
        req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
}
    boolean result;
    public boolean loginAction(String Email, String password,Resources theme) {

        // création d'une nouvelle demande de connexion
        String url = statics.BASE_URL + "/SignInJ?email="+Email+"&password="+password;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
System.out.println(url);
        req.addResponseListener((e) -> {
            result = req.getResponseCode() == 200;
            
            if (result) {
                try {
                    parseListUserJson(new String(req.getResponseData()));
                    String str = new String(req.getResponseData());//Récupération de la réponse du serveur
                    // System.out.println(str);//Affichage de la réponse serveur sur la console
                    
                } catch (ParseException ex) {

                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        return result;
    }
    public User parseListUserJson(String json) throws ParseException, IOException {

        User u = new User();
        try {
            JSONParser j = new JSONParser();
      
            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.print(obj);
            String em=obj.get("email").toString();
            String pwd = obj.get("password").toString();
            u.setEmail(em);
            u.setPassword(pwd);
            
            UserSession z = UserSession.getInstance(u);
            

        } catch (IOException ex) {
        }

        return u;
    }
   public boolean addUser(User u) {
        System.out.println(u.toString());
        System.out.println("********");
        String url = statics.BASE_URL + "/addUserJ";

        req.setUrl(url);
        req.addArgument("email", u.getEmail());
        req.addArgument("Password", u.getPassword());

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
   public boolean modifierUSer(User u) {
        String url = statics.BASE_URL + "&Email=" + u.getEmail()+ "&Password=" + u.getPassword();
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

    public void loginAction(TextField Email, TextField password, Resources theme) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}