package escale;

import connection.annotation.ForeignKey;
import connection.annotation.PrimaryKey;
import escale.Escale;
import port.Quai;
import prevision.Prevision;
import utilisateur.Profile;
import java.sql.Time;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import connection.BddObject;
import validation.Validable;

public class Prestation extends Validable {

    String idPrestation;
    String nom;
    Timestamp debut;
    Timestamp fin;
    Double prix;
    Escale escale;
    Tarif[] tarifs;

    public void setTarifs(Tarif[] tarifs) {
        this.tarifs = tarifs;
    }

    public Tarif[] getTarifs() throws Exception {
        return tarifs;
    }

    public void setReference( String reference ) throws Exception{
        this.reference = reference;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Double getPrix() {
        return prix;
    }

    public String getPrixDevise() {
        return this.getPrix() + " " + this.getEscale().getBateau().getPavillon().getDevise().getValeur();
    }

    public Timestamp getFin() {
        return fin;
    }

    public Timestamp getDebut() {
        return debut;
    }

    public Escale getEscale() {
        return escale;
    }

    public void setEscale(Escale escale) throws Exception {
        if (escale == null) throw new Exception("Escale est null");
        this.escale = escale;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception {
        if (nom == null) throw new Exception("Nom du prestation est null");
        if (nom.isEmpty()) throw new Exception("Nom du prestation est vide");
        this.nom = nom;
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }

    public Prestation() throws Exception {
        this.setTable("escale_prestation");
        this.setPrimaryKey("id_escale_prestation");
        this.setConnection("PostgreSQL");
        this.setProfile(new Profile("PRO002"));
    }

    public Prestation(String idPrestation) throws Exception {
        this();
        this.setId(idPrestation);
    }

    public Prestation(String idPrestation, String nom) throws Exception {
        this(idPrestation);
        this.setNom(nom);
    }

    public Prestation(String idPrestation, String nom, Timestamp debut, Timestamp fin, Double prix, Integer etat, Escale escale) throws Exception {
        this(idPrestation, nom);
        this.setDebut(debut);
        this.setFin(fin);
        this.setPrix(prix);
        this.setEtat(etat);
        this.setEscale(escale);
    }

    public void insert(Connection connection) throws Exception {
        boolean open = false;
        if (connection == null) { connection = BddObject.getPostgreSQL(); open = true; }
        this.setCountPK(7);
        this.setFunctionPK("nextval('seq_id_escale_prestation')");
        this.setPrefix("ESP");
        String sql = "insert into escale_prestation (id_escale_prestation, id_prestation, reference, id_quai, debut, fin, prix, etat) values ('%s', '%s', '%s', '%s', TO_TIMESTAMP('%s', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('%s', 'YYYY-MM-DD HH24:MI:SS.FF'), %15.8f, %o)";
        sql = String.format(sql, this.buildPrimaryKey(connection), this.getIdPrestation(), this.getEscale().getReference(), this.getEscale().getQuai().getIdQuai(), this.getDebut(), this.getFin(), this.getPrix(), this.getEtat());
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        if (open) { connection.commit(); connection.close(); }
    }

    public void update(Connection connection , String idEscalePrestsation) throws Exception{
        boolean open = false;
        if ( connection == null ) {
            connection = BddObject.getPostgreSQL();
            open = true;
        }
        String sql = "UPDATE escale_prestation SET ";
        sql += "debut = TO_TIMESTAMP('"+this.getDebut()+"', 'YYYY-MM-DD HH24:MI:SS.FF'),";
        sql += "fin = TO_TIMESTAMP('" + this.getFin() + "', 'YYYY-MM-DD HH24:MI:SS.FF'), ";
        sql += " prix = " + this.getPrix();
        sql += " WHERE id_escale_prestation LIKE '"+idEscalePrestsation+"'";
        System.out.println(sql);    
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        connection.commit();
        if ( open ) {
            connection.close();
            open = false;
        }
    }

    public Tarif[] getTarifs(Connection connection) throws Exception {
        Tarif tarif = new Tarif();
        tarif.setPavillon(this.getEscale().getBateau().getPavillon());
        tarif.setIdPrestation(this.getIdPrestation());
        tarif.setType(this.getEscale().getBateau().getType());
        tarif.setQuai(this.getEscale().getQuai());
        return tarif.findAll(connection, null);
    }

    public static boolean isBetweenTimestamps(String target, String start, String end) throws Exception {
        LocalTime timestamp = LocalTime.parse(target);
        LocalTime startTime = LocalTime.parse(start);
        LocalTime endTime = LocalTime.parse(end);
        if (startTime.isBefore(endTime)) {
            return ((timestamp.isAfter(startTime) || timestamp.compareTo(startTime) == 0) && (timestamp.isBefore(endTime) || timestamp.compareTo(endTime) == 0));
        } else {
            return ((timestamp.isAfter(startTime) || timestamp.compareTo(startTime) == 0) && timestamp.isBefore(LocalTime.parse("23:59:59"))) || ((timestamp.isAfter(LocalTime.parse("00:00:00")) || timestamp.compareTo(LocalTime.parse("00:00:00")) == 0) && (timestamp.isBefore(endTime) || timestamp.compareTo(endTime) == 0));
        }
    }

    public Tarif getTarif(Timestamp time) throws Exception {
        for (Tarif tarif : this.getTarifs()) {
            // Dans l'intervalle ferme du temps pour les majorations
            double duree = Prevision.convertToMinute(time.getTime() - this.getDebut().getTime());
            if ((tarif.getDebut() <= duree && duree < tarif.getFin()) && isBetweenTimestamps(new Time(time.getTime()).toString(), tarif.getHeureDebut().toString(), tarif.getHeureFin().toString()))
                return tarif;
        }
        throw new Exception("Il n'y pas de temps pour " + time.toString());
    }

    public double getDuree() {
        return Prevision.convertToMinute(this.getFin().getTime() - this.getDebut().getTime());
    }

    public Double getPrix(Connection connection) throws Exception {
        this.setTarifs(this.getTarifs(connection));
        Timestamp arrive = new Timestamp(this.getDebut().getTime());
        double somme = 0;
        int index = -1;
        while (arrive.compareTo(this.getFin()) <= 0) {
            Tarif tarif = this.getTarif(arrive);
            arrive = new Timestamp((long) (arrive.getTime() + tarif.getTrancheMillis()));
            // Strictement superieur a la tranche limite
            if (tarif.getTranche() == 1 && index < 0) index = 1;
            else somme += tarif.getPrixTotal();
        }
        return somme;
    }

}

}