/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDateTime;

/**
 *
 * @author mayro
 */
public class Seance {
    private int id_seance;
    private LocalDateTime date;
    private String typeseance;

    public Seance(int id_seance, LocalDateTime date, String typeseance) {
        this.id_seance = id_seance;
        this.date = date;
        this.typeseance = typeseance;
    }

    public Seance(LocalDateTime date, String typeseance) {
        this.date = date;
        this.typeseance = typeseance;
    }

    public Seance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTypeseance() {
        return typeseance;
    }

    public void setTypeseance(String typeseance) {
        this.typeseance = typeseance;
    }

    @Override
    public String toString() {
        return "Seance{" + "id_seance=" + id_seance + ", date=" + date + ", typeseance=" + typeseance + '}';
    }
    
    
}
