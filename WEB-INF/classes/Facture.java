package facture;

import java.sql.Connection;
import java.util.Vector;

import connection.annotation.*;
import port.Quai;
import validation.Validable;
public class Facture extends Validable {

    String designation;
    Double prix;
    Facture parent;
    Vector<Facture> factures;
    Quai quai;

    public Facture() throws Exception {
        this.setTable("facture");
    }

    public void setQuai(Quai quai) {
        this.quai = quai;
    }

    public Quai getQuai() {
        return quai;
    }

    public void setFactures(Vector<Facture> factures) {
        this.factures = factures;
    }

    public Vector<Facture> getFactures() {
        return factures;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    
    public String getDesignation() {
        return designation;
    }
    
    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Facture(String designation, Double prix, Quai quai) {
        setDesignation(designation);
        setPrix(prix);
        setQuai(quai);
    }

}