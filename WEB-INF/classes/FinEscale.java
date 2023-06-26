package escale;

import java.beans.BeanProperty;
import java.sql.Connection;
import java.sql.Timestamp;
import connection.BddObject;
import connection.annotation.ForeignKey;
import connection.annotation.PrimaryKey;
import connection.annotation.ColumnName;
import escale.Escale;
import prevision.Prevision;

public class FinEscale extends BddObject<FinEscale> {
    
    @ColumnName("id_fin")
    String idFin;
    @ColumnName("id_debut")
    String idDebut;
    Timestamp fin;
    double cours;

    public FinEscale() throws Exception{
        this.setTable("fin_escale");
        this.setCountPK(7);
        this.setFunctionPK("nextval('seq_id_fin')");
        this.setPrefix("FES");
        this.setConnection("PostgreSQL");
    }

    public FinEscale(String idDebut, String fin, String cours) throws Exception{
        this();
       // Connection conn = BddObject.getPostgreSQL();
        this.setIdDebut(idDebut);
        this.setFin(Prevision.toDate(fin));
        this.setCours(Double.valueOf(cours));
        //conn.close();
    }
    public void ajouterEscale() throws Exception{
        
        Connection conn = null;
        try{
            conn = BddObject.getPostgreSQL();
            this.setIdFin(buildPrimaryKey(conn));
            this.insert(conn);
            conn.commit();
        }
        catch(Exception e){
            throw new Exception(e);
        }
        finally{
            conn.close();
        }
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
