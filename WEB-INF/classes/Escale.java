package escale;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;
import escale.Prestation;
import port.Quai;
import prevision.Prevision;
import prevision.Proposition;
import bateau.Bateau;

public class Escale extends Proposition {

    Prestation[] prestations;
    Prestation[] listePrestation;
    Double cours;

    public void setPrestations(Prestation[] prestations) {
        this.prestations = prestations;
    }

    public Prestation[] getPrestations() {
        return prestations;
    }

    public void setListePrestation(Prestation[] prestations) {
        this.listePrestation = prestations;
    }

    public Prestation[] getListePrestation() {
        return listePrestation;
    }

    public Escale() throws Exception {}

    public Escale(Bateau bateau, Timestamp arrive, Timestamp depart, String reference) throws Exception {
        this.setBateau(bateau);
        this.setArrive(arrive);
        this.setDepart(depart);
        this.setReference(reference);
    }

    public void ajouterPrestation(Prestation prestation) throws Exception {
        // if (contains(prestation)) throw new Exception("Prestation " + prestation.getNom() + " deja ajouter");
        // prestation.setEscale(this);
        // this.getListePrestation().add(prestation);
    }

    public boolean contains(Prestation prestation) {
        for (Prestation p : this.getListePrestation()) {
            if (p.getIdPrestation().equals(prestation.getIdPrestation())) return true;
        }
        return false;
    }

    public static Escale createEscale(String idQuai, String reference) throws Exception {
        try (Connection connection = BddObject.getPostgreSQL()) {
            Quai quai = new Quai();
            quai.setIdQuai(idQuai);
            escale.setQuai(quai.getById(connection));
        }
    }

    // public Escale[] findAll(Connection connection, String order) throws Exception {

    // }

}
