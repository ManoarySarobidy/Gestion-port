package escale;

import java.sql.Time;
import java.sql.Connection;
import bateau.Pavillon;
import bateau.TypeBateau;
import connection.BddObject;
import connection.annotation.ColumnName;
import connection.annotation.ForeignKey;
import connection.annotation.PrimaryKey;
import escale.Prestation;
import port.Quai;
import connection.BddObject;
import formulaire.Formulaire;

public class Tarif extends BddObject<Tarif> {

    @PrimaryKey
    String idTarif;
    @ForeignKey
    Quai quai;
    @ForeignKey
    Prestation prestation;
    @ForeignKey
    TypeBateau type;
    @ColumnName("heure_debut")
    Time heureDebut;
    @ColumnName("heure_fin")
    Time heureFin;
    Double majoration;
    Double debut;
    Double fin;
    @ForeignKey
    Pavillon pavillon;
    Double prix;

    public void setIdTarif(String idTarif) {
        this.idTarif = idTarif;
    }

    public String getIdTarif() {
        return idTarif;
    }

    public void setQuai(String idQuai) throws Exception {
        Quai quai = new Quai();
        quai.setIdQuai(idQuai);
        this.setQuai(quai);
    }

    public Pavillon getPavillon() {
        return pavillon;
    }

    public void setPavillon(Pavillon pavillon) {
        this.pavillon = pavillon;
    }

    public void setQuai(Quai quai) throws Exception {
        if (quai == null) throw new Exception("Quai du tarif est null");
        this.quai = quai;
    }

    public Quai getQuai() {
        return quai;
    }

    public void setPrestation(Prestation prestation) throws Exception {
        if (prestation == null) throw new Exception("Prestation est null");
        this.prestation = prestation;
    }

    public void setPrestation(String idPrestation) throws Exception {
        Prestation prestation = new Prestation(idPrestation);
        this.setPrestation(prestation);
    }

    public Prestation getPrestation() {
        return prestation;
    }

    public void setType(TypeBateau type) {
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

    public void setDebut(Double debut) throws Exception {
        if (debut < 0) throw new Exception("Debut en minute doit etre positif");
        this.debut = debut;
    }

    public Double getDebut() {
        return debut;
    }

    public void setFin(Double fin) throws Exception {
        if (fin < 0) throw new Exception("Fin en minute doit etre positif");
        this.fin = fin;
    }

    public Double getFin() {
        return fin;
    }

    public void setPrix(Double prix) throws Exception {
        if (prix < 0) throw new Exception("Prix doit etre positif");
        this.prix = prix;
    }

    public Double getPrix() {
        return prix;
    }

    public Tarif() throws Exception {
        this.setTable("v_tarif_quai");
        this.setFunctionPK("nextval('seq_id_tarif')");
        this.setCountPK(7);
        this.setPrefix("TAR");
        this.setConnection("PostgreSQL");
    }

    public Tarif(Double prix, Prestation prestation) throws Exception {
        this.setPrix(prix);
        this.setPrestation(prestation);
        this.setPavillon(prestation.getEscale().getBateau().getPavillon());
    }

    public String toString() {
        return Double.toString(this.getPrix()) + " " + this.getPavillon().getDevise().getValeur();
    }

    public static Formulaire createFormulaire() throws Exception {
        Formulaire form = null;
        try (Connection connection = BddObject.getPostgreSQL()) {
            form = Formulaire.createFormulaire(new Tarif());
            form.getListeChamp()[0].setVisible(false, "");
            form.setAction("/gestion-port/insert");
            form.getListeChamp()[1].changeToDrop(new Quai().findAll(connection, null), "getNom", "getIdQuai");
            // form.getListeChamp()[2].changeToDrop(new Prestation().findAll(connection, null), "getNom", "getIdPrestation");
            form.getListeChamp()[3].changeToDrop(new TypeBateau().findAll(connection, null), "getNom", "getIdType");
            form.getListeChamp()[4].setLabel("Heure de debut");
            form.getListeChamp()[5].setLabel("Heure de fin");
            form.getListeChamp()[7].setType("datetime-local");
            form.getListeChamp()[8].setType("datetime-local");
            form.getListeChamp()[9].changeToDrop(new Pavillon().findAll(connection, null), "getNom", "getIdPavillon");
        }
        return form;
    }

}
