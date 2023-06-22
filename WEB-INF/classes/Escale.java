package escale;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;
import escale.Prestation;
import prevision.Prevision;
import prevision.Proposition;
import connection.BddObject;
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
        setReference(reference);
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

    public void debuter() throws Exception{
        DebutEscale debutEscale = new DebutEscale(getReference(), getArrive());
        Connection connection = null;
        try {
            connection = BddObject.getPostgreSQL();
            debutEscale.setIdDebut(debutEscale.buildPrimaryKey(connection));
            debutEscale.insert(connection);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    // public Escale[] findAll(Connection connection, String order) throws Exception {

    // }

}
