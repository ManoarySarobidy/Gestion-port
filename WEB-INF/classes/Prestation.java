package escale;

import connection.annotation.ForeignKey;
import connection.annotation.PrimaryKey;
import escale.Escale;
import port.Quai;
import prevision.Prevision;

import java.sql.Time;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;
import connection.BddObject;

public class Prestation extends BddObject<Prestation> {

    @PrimaryKey
    String idPrestation;
    String nom;
    String reference;
    @ForeignKey
    Quai quai;
    Timestamp debut;
    Timestamp fin;
    Double prix;
    Integer etat;
    Escale escale;
    Tarif[] tarifs;

    public void setTarifs(Tarif[] tarifs) {
        this.tarifs = tarifs;
    }

    public Tarif[] getTarifs() {
        return tarifs;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Double getPrix() {
        return prix;
    }

    public Timestamp getFin() {
        return fin;
    }

    public Quai getQuai() {
        return quai;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public void setQuai(Quai quai) {
        this.quai = quai;
    }

    public void setQuai(String idQuai) throws Exception {
        Quai quai = new Quai();
        quai.setIdQuai(idQuai);
        this.setQuai(quai.getById());
    }

    public Integer getEtat() {
        return etat;
    }

    public Timestamp getDebut() {
        return debut;
    }

    public Escale getEscale() {
        return escale;
    }

    public String getReference() {
        return reference;
    }

    public void setEscale(Escale escale) throws Exception {
        if (escale == null) throw new Exception("Escale est null");
        this.escale = escale;
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

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }

    public Prestation() throws Exception {
        this.setTable("prestation");
        this.setConnection("PostgreSQL");
    }

    public Prestation(String idPrestation) throws Exception {
        this();
        this.setIdPrestation(idPrestation);
    }

    public void setPrestation(String value) throws Exception {
        String[] values = value.split("[.]");
        this.setIdPrestation(values[0]);
        this.setNom(values[1]);
    }


    public void insert(Connection connection) throws Exception {
        boolean open = false;
        if (open) {
            connection = BddObject.getPostgreSQL();
            open = true;
        }
        this.setCountPK(7);
        this.setFunctionPK("nextval('seq_id_escale_prestation')");
        this.setPrefix("ESP");
        String sql = "insert into escale_prestation (id_escale_prestation, id_prestation, reference, id_quai, debut, fin, prix, etat) values (";
        sql += "'" + this.buildPrimaryKey(connection) + "', ";
        sql += "'" + this.getIdPrestation() + "', ";
        sql += "'" + this.getEscale().getReference() + "', ";
        sql += "'" + this.getEscale().getQuai().getIdQuai() + "', ";
        sql += "TO_TIMESTAMP('" + this.getDebut() + "', 'YYYY-MM-DD HH24:MI:SS.FF'),";
        sql += "TO_TIMESTAMP('" + this.getFin() + "', 'YYYY-MM-DD HH24:MI:SS.FF'), ";
        sql += this.getPrix() + ", ";
        sql += this.getEtat() + ")";
        System.out.println(sql);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        connection.commit();
        if (open) {
            connection.close();
            open = false;
        }
    }

    public void update(Connection connection , String idEscalePrestsation){
        boolean open = false;
        if (open) {
            connection = BddObject.getPostgreSQL();
            open = true;
        }
        String sql = "UPDATE escale_prestation SET";
        sql += "debut = TO_TIMESTAMP('"+this.getDebut()+", 'YYYY-MM-DD HH24:MI:SS.FF'),";
        sql += "fin = TO_TIMESTAMP('" + this.getFin() + "', 'YYYY-MM-DD HH24:MI:SS.FF'), ";
        sql += this.getPrix();
        sql += " WHERE id_escale_prestation LIKE '"+idEscalePrestsation+"'";
        System.out.println(sql);    
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        connection.commit();
        if (open) {
            connection.close();
            open = false;
        }
    }

    public Tarif[] getTarifs(Connection connection) throws Exception {
        Tarif tarif = new Tarif();
        tarif.setPavillon(this.getEscale().getBateau().getPavillon());
        tarif.setPrestation(this);
        tarif.setType(this.getEscale().getBateau().getType());
        tarif.setQuai(this.getEscale().getQuai());
        return tarif.findAll(connection, null);
    }

    public static boolean isBetweenTimestamps(Time timestamp, Time startTimestamp, Time endTimestamp) {
        return timestamp.toInstant().isAfter(startTimestamp.toInstant()) && timestamp.toInstant().isBefore(endTimestamp.toInstant());
    }

    public Tarif getTarif(Timestamp time) {
        for (Tarif tarif : this.getTarifs()) {
            double duree = Prevision.convertToMinute(time.getTime() - this.getDebut().getTime());
            if ((tarif.getDebut() <= duree && duree <= tarif.getFin()) && isBetweenTimestamps(new Time(time.getTime()), tarif.getHeureDebut(), tarif.getHeureFin()))
                return tarif;
        }
        return null;
    }

    public double toMillis(double minute) {
        return minute * 60.0 * 1000.0;
    }

    public double getDuree() {
        return Prevision.convertToMinute(this.getFin().getTime() - this.getDebut().getTime());
    }

    public Double getPrix(Connection connection) throws Exception {
        this.setTarifs(this.getTarifs(connection));
        Timestamp arrive = new Timestamp(this.getDebut().getTime());
        double somme = 0;
        while (arrive.compareTo(this.getFin()) >= 0) {
            Tarif tarif = this.getTarif(arrive);
            arrive = new Timestamp((long) (arrive.getTime() + toMillis(tarif.getTranche())));
            somme += tarif.getPrix();
        }
        return somme;
    }

}
