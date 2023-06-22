package escale;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import escale.Prestation;
// import escale.Stationnement;
import prevision.Prevision;
import prevision.Proposition;
import bateau.Bateau;
import connection.BddObject;

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

    public void setCours(double value) throws Exception{
        if( value < 0 ){
            throw new Exception("The value of cours can't be null");
        }
        this.cours = value;
    }

    public double getCours(){
        return this.cours;
    }

    public Escale[] findAll(Connection connection, String order) throws Exception {
        String sql = "select * from v_escale";
        ArrayList<Escale> escales = new ArrayList<Escale>();
        try{
            java.sql.Statement st = connection.createStatement();
            java.sql.ResultSet set = st.executeQuery( sql );
            while( set.next() ){
                String reference = set.getString("reference");
                Timestamp debut = set.getTimestamp("debut");
                String idBateau = set.getString("idBateau");
                Timestamp fin = set.getTimestamp("fin");
                double cours = set.getDouble("cours");
                Escale escale = new Escale();
                escale.setBateau( idBateau );
                escale.setReference( reference );
                escale.setArrive( debut );
                escale.setDepart( fin );
                escale.setCours( cours );
                escales.add(escale);
            }
            st.close();
            return escales.toArray( new Escale[ escales.size() ] );
        }catch (Exception e) {
            throw e;
        }
    }


}