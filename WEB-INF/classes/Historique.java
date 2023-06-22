package prevision;

import connection.BddObject;
import java.sql.SQLException;
import connection.annotation.ForeignKey;
import connection.annotation.PrimaryKey;
import prevision.Prevision;
import utilisateur.Utilisateur;
import java.util.Date;

public class Historique extends BddObject<Historique> {
    
    @PrimaryKey
    String idHistorique;
    @ForeignKey
    Prevision prevision;
    @ForeignKey
    Utilisateur utilisateur;
    String action;
    Date date;

    public void setIdHistorique(String idHistorique) throws Exception {
        if (idHistorique == null) throw new Exception("IdHistorique est null");
        if (idHistorique.isEmpty()) throw new Exception("ID de l'historique est vide");
        this.idHistorique = idHistorique;
    }

    public String getIdHistorique() {
        return idHistorique;
    }

    public void setDate(Date date) throws Exception {
        if (new Date().compareTo(date) > 0) throw new Exception("Date doit etre un evenement a avenir");
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setAction(String action) throws Exception {
        if (action == null) throw new Exception("Action est null");
        if (action.isEmpty()) throw new Exception("Action est vide");
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setUtilisateur(Utilisateur utilisateur) throws Exception {
        if (utilisateur == null) throw new Exception("Utilisateur est null");
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setPrevision(Prevision prevision) throws Exception {
        if (prevision == null) throw new Exception("Prevision est null");
        this.prevision = prevision;
    }

    public Prevision getPrevision() {
        return prevision;
    }

    public Historique() throws Exception {
        this.setTable("historique");
        this.setCountPK(7);
        this.setFunctionPK("nextval('seq_id_historique')");
        this.setPrefix("HRS");
        this.setConnection("PostgreSQL");
    }

    public Historique(Prevision prevision, Utilisateur utilisateur, String action, Date date) throws Exception {
        this();
        this.setPrevision(prevision);
        this.setUtilisateur(utilisateur);
        this.setAction(action);
        this.setDate(date);   
    }

}