package prevision;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import bateau.Bateau;
import connection.BddObject;
import connection.annotation.ForeignKey;
import connection.annotation.PrimaryKey;
import prevision.Historique;
import utilisateur.Utilisateur;

public class Prevision extends BddObject<Prevision> {
    
    @PrimaryKey
    String idPrevision;
    @ForeignKey
    Bateau bateau;
    Timestamp arrive;
    Timestamp depart;
    String reference;

    public void setIdPrevision(String idPrevision) throws Exception {
        if (idPrevision == null) throw new Exception("ID Prevision est null");
        if (idPrevision.isEmpty()) throw new Exception("ID pour " + this.getClass().getSimpleName() + " est vide");
        this.idPrevision = idPrevision;
    }

    public void setReference(String reference) throws Exception {
        if (reference == null) throw new Exception("Reference est null");
        if (reference.isEmpty()) throw new Exception("Reference est vide");
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    public String getIdPrevision() {
        return idPrevision;
    }

    public void setBateau(Bateau bateau) throws Exception {
        if (bateau == null) throw new Exception("Bateau ne doit pas etre null");
        this.bateau = bateau;
    }

    public void setBateau(String idBateau) throws Exception {
        if (idBateau == null) throw new Exception("Champ Bateau est null");
        if (idBateau.isEmpty()) throw new Exception("Selectionner un bateau");
        this.setBateau(new Bateau(idBateau).getById());
    }
    
    public void setBateau(String idBateau, Connection connection) throws Exception {
        if (idBateau == null) throw new Exception("Champ Bateau est null");
        if (idBateau.isEmpty()) throw new Exception("Selectionner un bateau");
        this.setBateau(new Bateau(idBateau).getById(connection));
    }

    public Bateau getBateau() {
        return bateau;
    }

    public void setArrive(Timestamp arrive) throws Exception {
        // if (arrive == null) throw new Exception("Date d'arrive est null"); 
        if (new Timestamp(System.currentTimeMillis()).compareTo(arrive) >= 0) throw new Exception("Date doit etre un evenement a avenir");
        this.arrive = arrive;
    }

    public void setArrive(String arrive) throws Exception {
        // if (arrive == null) throw new Exception("Le champ arrive est null");
        if ( arrive != null &&  arrive.isEmpty()) throw new Exception("Le champ arrive est vide");
        this.setArrive(toDate(arrive));
    }

    public Timestamp getArrive() {
        return arrive;
    }

    public static Timestamp toDate(String date) throws Exception {
        // Format date in client-side is YYYY-MM-ddThh:mm
        date = date.replace("T", ",");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
        Date date2 = formatter.parse(date);
        return new Timestamp(date2.getTime());
    }

    public Prevision(Bateau bateau, Timestamp arrive) throws Exception {
        this();
        this.setBateau(bateau);
        this.setArrive(arrive);
    }

    public Prevision(String idPrevision) throws Exception {
        this();
        this.setIdPrevision(idPrevision);
    }

    public void setDepart(Timestamp depart) throws Exception {
        // if (this.getArrive() == null) throw new Exception("Il n'y pas encore de date d'arrive");
        if ( depart != null && this.getArrive().compareTo(depart) >= 0) throw new Exception("Depart doit etre derriere la date d'arrive");
        this.depart = depart;
    }

    public void setDepart(String depart) throws Exception {
        // if (depart == null) throw new Exception("Le champ depart est null");
        if (depart.isEmpty()) throw new Exception("Le champ depart est vide");
        this.setDepart(toDate(depart));
    }

    public Timestamp getDepart() {
        return this.depart;
    }
    
    public double getDuree() throws Exception {
        if (this.getArrive() == null) throw new Exception("Il n'y pas encore de date d'arrive");
        if (this.getDepart() == null) throw new Exception("Il n'y pas encore de date de depart");
        long time = this.getDepart().getTime() - this.getArrive().getTime();
        return convertToMinute(time);
    }

    public static double convertToMinute(long time) {
        return (time / (1000 * 60));
    }

    public static String toFormatString(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        String day = Integer.toString(calendar.get(Calendar.DATE));
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        if (month.length() < 2) month = "0" + month;
        if (day.length() < 2) day = "0" + day;
        return month + "/" + day + "/" + year;
    }

    public Prevision() throws Exception {
        this.setTable("v_liste_prevision_a_venir");
        this.setFunctionPK("nextval('seq_id_prevision')");
        this.setCountPK(3);
        this.setConnection("PostgreSQL");
    }

    public Prevision(String idBateau, String arrive, String depart) throws Exception {
        this();
        this.setBateau(idBateau);
        this.setArrive(arrive);
        this.setDepart(depart);
    }

    public Prevision(String idPrevision, Bateau bateau, Timestamp arrive, Timestamp depart, String reference) throws Exception {
        this();
        this.setIdPrevision(idPrevision);
        this.setArrive(arrive);
        this.setDepart(depart);
        this.setBateau(bateau);
        this.setReference(reference);
    }

    public String generateID(Connection connection) throws Exception {
        return "PRE" + toFormatString(new Date()).replace("/", "") + this.buildPrimaryKey(connection);
    }

    public String generateReference(Connection connection)throws Exception {
        String primary = this.getFunctionPK();
        int count = this.getCountPK();
        this.setFunctionPK("nextval('seq_escale')");
        this.setCountPK(3);
        String escale = "ESC" + toFormatString(this.getArrive()).replace("/", "") + this.getBateau().getPavillon().getNom().substring(0,1) + this.buildPrimaryKey(connection);
        this.setFunctionPK(primary);
        this.setCountPK(count);
        return escale;
    }

    public void insert(Utilisateur user) throws Exception {
        Connection connection = null;
        try {
            if (user == null) throw new Exception("Vous devait etre authentifier pour pouvoir faire des previsions");
            connection = this.getConnection();
            this.setIdPrevision(this.generateID(connection));
            this.setReference(this.generateReference(connection));
            this.insert(connection);
            Historique historique = new Historique(this, user, "insertion", new Date());
            historique.setIdHistorique(historique.buildPrimaryKey(connection));
            historique.insert(connection);
            connection.commit();
        } catch (Exception e) {
            if (connection != null) connection.rollback();
            throw e;
        } finally {
            if (connection != null) connection.close();
        }
    }

}