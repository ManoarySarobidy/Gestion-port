package escale;

import java.beans.BeanProperty;
import java.sql.Timestamp;
import connection.BddObject;
import connection.annotation.ForeignKey;
import connection.annotation.PrimaryKey;
import connection.annotation.ColumnName;

import escale.Escale;

public class DebutEscale extends BddObject<DebutEscale> {
    
    @ColumnName("id_debut")
    String idDebut;
    String reference;
    Timestamp debut;

    public void setIdDebut(String idDebut) throws Exception{
        if (idDebut == null) throw new Exception("IdDebut est null");
        if (idDebut.isEmpty()) throw new Exception("ID pour " + this.getClass().getSimpleName() + " est vide");
        this.idDebut = idDebut;
    }
    public String getIdDebut() {
        return idDebut;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
    public String getReference() {
        return reference;
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }
    public Timestamp getDebut() {
        return debut;
    }

    public DebutEscale() throws Exception {
        this.setTable("debut_escale");
        this.setCountPK(7);
        this.setFunctionPK("nextval('seq_id_debuT')");
        this.setPrefix("DES");
        this.setConnection("PostgreSQL");
    }

    public DebutEscale(String reference, Timestamp debut) throws Exception{
        this();
        setReference(reference);
        setDebut(debut);
    }

}
