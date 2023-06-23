package escale;

import java.sql.Time;
import bateau.Pavillon;
import bateau.TypeBateau;
import connection.BddObject;
import connection.annotation.ColumnName;
import connection.annotation.ForeignKey;
import connection.annotation.PrimaryKey;
import escale.Prestation;
import port.Quai;

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
    Double debut;
    Double fin;
    Double majoration;
    @ForeignKey
    Pavillon pavillon;
    Double prix;

    public String getIdTarif() {
        return idTarif;
    }

    public void setIdTarif(String idTarif) {
        this.idTarif = idTarif;
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

    public void setQuai(String idQuai) throws Exception {
        Quai quai = new Quai();
        quai.setIdQuai(idQuai);
        this.setQuai(quai);
    }

    public Quai getQuai() {
        return quai;
    }

    public void setPrestation(Prestation prestation) throws Exception {
        if (prestation == null) throw new Exception("Prestation est null");
        this.prestation = prestation;
    }

    public void setPrestation(String idPrestation)  throws Exception {
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
        this.setTable("tarif");
        this.setConnection("PostgreSQL");
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    public void setMajoration(Double majoration) {
        this.majoration = majoration;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public Double getMajoration() {
        return majoration;
    }

    public Tarif(Double prix, Prestation prestation) throws Exception {
        this.setPrix(prix);
        this.setPrestation(prestation);
        this.setPavillon(prestation.getEscale().getBateau().getPavillon());
    }

    public String toString() {
        return Double.toString(this.getPrix()) + " " + this.getPavillon().getDevise().getValeur();
    }

    public int getTranche() {
        return (this.getFin().isInfinite()) ? 1 : (int) (this.getFin() - this.getDebut());
    }

}
