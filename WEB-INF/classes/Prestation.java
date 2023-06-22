package escale;

import connection.annotation.ForeignKey;
import connection.annotation.PrimaryKey;
import escale.Escale;
import java.sql.Time;
import java.sql.Timestamp;
import connection.BddObject;

public class Prestation extends BddObject<Prestation> {

    @PrimaryKey
    String idPrestation;
    String nom;
    String reference;
    Timestamp debut;
    Timestamp fin;
    Double prix;
    Integer etat;
    Escale escale;
    
    public Escale getEscale() {
        return escale;
    }

    public void setEscale(Escale escale) throws Exception {
        if (escale == null) throw new Exception("Escale est null");
        this.escale = escale;
    }

    public String getReference() {
        return reference;
    }

    public String getIdPrestation() {
        return idPrestation;
    }

    public void setIdPrestation(String idPrestation) throws Exception {
        if (idPrestation  == null) throw new Exception("ID Prestation est null");
        if (idPrestation.isEmpty()) throw new Exception("ID Prestation est vide");
        this.idPrestation = idPrestation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception {
        if (nom == null) throw new Exception("Nom du prestation est null");
        if (nom.isEmpty()) throw new Exception("Nom du prestation est vide");
        this.nom = nom;
    }

    public Prestation() throws Exception {
        this.setTable("prestation");
        this.setConnection("PostgreSQL");
    }

    public Prestation(String idPrestation) throws Exception {
        this();
        this.setIdPrestation(idPrestation);
    }

    public void setPrestation(String value, Tarif tarif) throws Exception {
        String[] values = value.split("[.]");
        this.setIdPrestation(values[0]);
        this.setNom(values[1]);
    }

}
