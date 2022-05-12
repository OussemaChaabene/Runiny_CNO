/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ACER EXTENSA 15
 */
public class User {

    public static User getInstance() {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        private int id;
    private String Email , Password,Role;

    public User(int id, String Email, String Password, String Role) {
        this.id = id;
        this.Email = Email;
        this.Password = Password;
        this.Role = Role;
    }

    public User() {
    }

    public User(String Email, String Password, String Role) {
        this.Email = Email;
        this.Password = Password;
        this.Role = Role;
    }

    public User(String string, String string0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", Email=" + Email + ", Password=" + Password + ", Role=" + Role + '}';
    }

   
}
