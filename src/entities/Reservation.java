/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDateTime;

public class Reservation {
    private int id_reser;
    private int id_user;
    private int id_even;
    private int id_coach;
    private int id_salle;
    private LocalDateTime date;
    public static Reservation user;

    public Reservation(int id_reser, int id_user, int id_even,int id_coach, int id_salle, LocalDateTime date) {
        this.id_reser = id_reser;
        this.id_user = id_user;
        this.id_even = id_even;
        this.id_coach = id_coach;
        this.id_salle = id_salle;
        this.date = date;
    }

    public Reservation(int id_user, int id_even, int id_salle, LocalDateTime date) {
        this.id_user = id_user;
        this.id_even = id_even;
        this.id_coach = id_coach;
        this.id_salle = id_salle;
        this.date = date;
    }

    public Reservation() {
    }

    public Reservation(int aInt, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_coach() {
        return id_coach;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public int getId_reser() {
        return id_reser;
    }

    public void setId_reser(int id_reser) {
        this.id_reser = id_reser;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_even() {
        return id_even;
    }

    public void setId_even(int id_even) {
        this.id_even = id_even;
    }

    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id_reser=" + id_reser +
                ", id_user=" + id_user +
                ", id_even=" + id_even +
                ", id_salle=" + id_salle +
                ", date='" + date + '\'' +
                '}';
    }
}
