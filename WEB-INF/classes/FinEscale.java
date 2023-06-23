package escale;

import java.beans.BeanProperty;
import java.sql.Timestamp;
import connection.BddObject;
import connection.annotation.ForeignKey;
import connection.annotation.PrimaryKey;
import connection.annotation.ColumnName;

import escale.Escale;

public class FinEscale extends BddObject<DebutEscale>{
    @ColumnName("id_fin")
    String idFin;
    String idDebut;
    Timestamp fin;
    double cours;


    public FinEscale(){
        this.setTable("fin_escale");
        this.setCountPK(7);
        this.setFunctionPK("nextval('seq_id_fin')");
        this.setPrefix("FES");
        this.setConnection("PostgreSQL");
    }
    public FinEscale(String idDebut, String fin, String cours){
        this();
        this.setFin(Prevision.toDate(fin));
        this.setCours(Double.valueOf(cours));
    }
    public String getIdFin() {
        return idFin;
    }
    public void setIdFin(String idFin) {
        this.idFin = idFin;
    }
    public String getIdDebut() {
        return idDebut;
    }
    public void setIdDebut(String idDebut) {
        this.idDebut = idDebut;
    }
    public Timestamp getFin() {
        return fin;
    }
    public void setFin(Timestamp fin) {
        this.fin = fin;
    }
    public double getCours() {
        return cours;
    }
    public void setCours(double cours) {
        this.cours = cours;
    }
    
    
}
