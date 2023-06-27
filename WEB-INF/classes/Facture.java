package facture;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;
import connection.annotation.*;
import port.Quai;
import escale.Escale;
import escale.Prestation;
import utilisateur.Profile;
import connection.BddObject;
import validation.Validable;

public class Facture extends Validable {

    Prestation prestation;
    Timestamp date;
    List<Facture> factures = new ArrayList<>();
    Escale escale;

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Escale getEscale() {
        return escale;
    }

    public void setEscale(Escale escale) {
        this.escale = escale;
    }

    public void setFactures(ArrayList<Facture> factures) {
        this.factures = factures;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }

    public Prestation getPrestation() {
        return prestation;
    }

    public Facture() throws Exception {
        this.setTable("facture");
        this.setFunctionPK("nextval('seq_id_facture')");
        this.setPrimaryKey("id_facture");
        this.setValidation("validation_facture");
        this.setProfile(new Profile("PRO004"));
        this.setPrefix("FAC");
        this.setCountPK(6);
        this.setConnection("PostgreSQL");
    }

    public Facture(Prestation prestation) throws Exception {
        this();
        this.setPrestation(prestation);
    }

    public Facture(String idFacture, String reference, Timestamp date, int etat) throws Exception {
        this.setId(idFacture);
        Escale escale = new Escale();
        escale.setReference(reference);
        this.setEscale(escale);
        this.setDate(date);
        this.setEtat(etat);
    }

    public void insert(Connection connection) throws Exception {
        boolean open = false;
        Statement statement = null;
        try {
            if (connection == null) { connection = BddObject.getPostgreSQL(); open = true; }
            String sql = "INSERT INTO facture (id_facture, reference, etat) VALUES ('%s', '%s', %o)";
            statement = connection.createStatement();
            this.setId(this.buildPrimaryKey(connection));
            statement.executeUpdate(String.format(sql, this.getId(), this.getEscale().getReference(), 1));
            sql = "INSERT INTO detailFacture (id_detail_facture, id_facture, id_escale_prestation) VALUES ('%s', '%s', '%s')";
            for (Facture facture : this.getFactures()) {
                facture.setFunctionPK("nextval('seq_id_detail')");
                facture.setPrefix("DET");
                facture.setCountPK(7);
                statement.executeUpdate(String.format(sql, facture.buildPrimaryKey(connection), this.getId(), facture.getPrestation().getId()));   
            }
            if (open) connection.commit();
        } catch (Exception e) {
            if (open) connection.rollback();
            throw e;
        } finally {
            statement.close();
            if (open) connection.commit();
        }
    }

    public ArrayList<Facture> detail(Connection connection) throws Exception {
        String sql = "SELECT * FROM v_detail_facture WHERE id_facture='%s'";
        ArrayList<Facture> factures = new ArrayList<>();
        Statement st = connection.createStatement();
        ResultSet set = st.executeQuery( String.format(sql, this.getId()) );
        while( set.next() ) {
            Escale escale = new Escale();
            escale.setQuai(set.getString("quai"), connection);
            Prestation prestation = new Prestation("", set.getString("nom"), set.getTimestamp("debut"), set.getTimestamp("fin"), set.getDouble("prix"), 10, escale);
            factures.add(new Facture(prestation));
        }
        st.close();
        return factures;
    }

    public static Facture createFacture(String idFacture) throws Exception {
        Facture facture = new Facture();
        facture.setId(idFacture);
        try (Connection connection = BddObject.getPostgreSQL()) {
            facture.setFactures(facture.detail(connection));
        }
        return facture;
    }

}