package port;

import connection.BddObject;
import connection.annotation.PrimaryKey;
import escale.Escale;

public class Quai extends BddObject<Quai> {

    @PrimaryKey
    String idQuai;
    String nom;
    Double profondeur;
    Escale escale;

    public void setIdQuai(String idQuai) throws Exception {
        if (idQuai == null) throw new Exception("ID du Quai est null");
        if (idQuai.isEmpty()) throw new Exception("IdQuai est vide");
        this.idQuai = idQuai;
    }

    public String getIdQuai() {
        return idQuai;
    }

    public void setNom(String nom) throws Exception {
        if (nom == null) throw new Exception("Nom est null");
        if (nom.isEmpty()) throw new Exception("Nom est vide");
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setProfondeur(Double profondeur) throws Exception {
        if (profondeur < 0) throw new Exception("Profondeur du quai doit etre positif");
        this.profondeur = profondeur;
    }

    public void setProfondeur(String profondeur) throws Exception {
        if (profondeur == null) throw new Exception("Profondeur est null");
        if (profondeur.isEmpty()) throw new Exception("Profondeur est vide");
        this.setProfondeur(Double.parseDouble(profondeur));
    }

    public Double getProfondeur() {
        return profondeur;
    }

    public void setEscale(Escale escale) {
        this.escale = escale;
    }

    public Escale getEscale() {
        return escale;
    }

    public Quai() throws Exception {
        this.setTable("quai");
        this.setConnection("PostgreSQL");
    }

    public Quai(String nom) throws Exception {
        this();
        this.setNom(nom);
    }

    public boolean isDisponible() {
        return this.getEscale() == null;
    }

}
