package bateau;

import java.sql.Connection;
import java.sql.Timestamp;

import bateau.Pavillon;
import bateau.TypeBateau;
import connection.BddObject;
import connection.annotation.ForeignKey;
import connection.annotation.PrimaryKey;
import connection.annotation.ColumnName;
import formulaire.Formulaire;

public class Bateau extends BddObject<Bateau> {

    @PrimaryKey
    String idBateau;
    String nom;
    Double profondeur;
    Double remorquage; // en minute
    @ForeignKey
    Pavillon pavillon;
    @ForeignKey
    TypeBateau type;

    public void setType(TypeBateau type) throws Exception {
        if (type == null) throw new Exception("Type de bateau est null");
        this.type = type;
    }

    public void setType(String idType) throws Exception {
        TypeBateau type = new TypeBateau();
        type.setIdType(idType);
        this.setType(type);
    }

    public TypeBateau getType() {
        return type;
    }

    public void setIdBateau(String idBateau) throws Exception {
        if (idBateau == null) throw new Exception("IdBateau est null");
        if (idBateau.isEmpty()) throw new Exception("ID pour " + this.getClass().getSimpleName() + " est vide");
        this.idBateau = idBateau;
    }

    public String getIdBateau() {
        return idBateau;
    }

    public void setNom(String nom) throws Exception {
        if (nom == null) throw new Exception("Nom est null");
        if (nom.isEmpty()) throw new Exception("Nom est vide");
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setRemorquage(Double remorquage) throws Exception {
        if (remorquage < 0) throw new Exception("remorquage doit etre positif");
        this.remorquage = remorquage;
    }

    public void setRemorquage(String remorquage) throws Exception {
        if (remorquage == null) throw new Exception("Remorquage est null");
        if (remorquage.isEmpty()) throw new Exception("Champ remorquage est vide");
        if (!isNumeric(remorquage)) throw new Exception("Champ remorquage doit etre un nombre");
        this.setRemorquage(Double.parseDouble(remorquage));
    }

    public Double getRemorquage() {
        return remorquage;
    }

    public void setProfondeur(Double profondeur) throws Exception {
        if (profondeur < 0) throw new Exception("Profondeur doit etre positive");
        this.profondeur = profondeur;
    }

    public void setProfondeur(String profondeur) throws Exception {
        if (profondeur == null) throw new Exception("Profondeur est null");
        if (profondeur.isEmpty()) throw new Exception("Champ profondeur est vide");
        if (!isNumeric(profondeur)) throw new Exception("Champ profondeur doit etre un nombre");
        this.setProfondeur(Double.parseDouble(profondeur));
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public Double getProfondeur() {
        return profondeur;
    }

    public Pavillon getPavillon() {
        return pavillon;
    }

    public void setPavillon(Pavillon Pavillon) throws Exception {
        if (Pavillon == null) throw new Exception("Pavillon est null");
        this.pavillon = Pavillon;
    }

    public void setPavillon(String idPavillon) throws Exception {
        Pavillon pavilon = new Pavillon();
        pavilon.setIdPavillon(idPavillon);
        this.setPavillon(pavilon);
    }

    public Bateau(String idBateau, String nom) throws Exception {
        this(idBateau);
        this.setNom(nom);
    }

    public Bateau(String idBateau, String nom, double profondeur) throws Exception {
        this(idBateau, nom);
        this.setProfondeur(profondeur);
    }

    public Bateau(String idBateau, String nom, String profondeur) throws Exception {
        this(idBateau, nom);
        this.setProfondeur(profondeur);
    }

    public Bateau(String idBateau) throws Exception {
        this();
        this.setIdBateau(idBateau);
    }

    public Bateau(String idBateau, String nom, double profondeur, double remorquage) throws Exception {
        this(idBateau, nom, profondeur);
        this.setRemorquage(remorquage);
    }

    public Bateau(String idBateau, String nom, String profondeur, String remorquage) throws Exception {
        this(idBateau, nom, profondeur);
        this.setRemorquage(remorquage);
    }

    public Bateau() throws Exception {
        this.setTable("bateau");
        this.setCountPK(7);
        this.setFunctionPK("nextval('seq_id_bateau')");
        this.setPrefix("BAT");
        this.setConnection("PostgreSQL");
    }

    public String selected(Bateau bateau) {
        return (this.equals(bateau)) ? "selected" : "";
    }

    public boolean equals(Bateau bateau) {
        return this.getIdBateau().equals(bateau.getIdBateau());
    }

    public void insert(Connection connection) throws Exception {
        boolean connect = false;
        try {
            if (connection == null) {connection = this.getConnection(); connect = true;}
            this.setIdBateau(this.buildPrimaryKey(connection));
            super.insert(connection);
            if (connect) connection.commit();
        } catch (Exception e) {
            if (connect && connection != null) connection.rollback();
            throw e;
        } finally {
            if (connect && connection != null) connection.close();
        }
    }

    public static Formulaire createFormulaire(String error) throws Exception {
        Bateau bateau = new Bateau();
        Formulaire form = Formulaire.createFormulaire(bateau);
        form.getListeChamp()[0].setVisible(false, "");
        form.setError(error);
        form.setAction("/gestion-port/insert");
        form.setTitle("Saisie de Bateau");
        try (Connection connection = bateau.getConnection()) {
            form.getListeChamp()[4].changeToDrop(new Pavillon().findAll(connection, null), "getNom", "getIdPavillon");
            form.getListeChamp()[5].changeToDrop(new TypeBateau().findAll(connection, null), "getNom", "getIdType");
        }
        return form;
    }

}