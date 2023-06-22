package bateau;

import connection.BddObject;
import connection.annotation.ForeignKey;
import connection.annotation.PrimaryKey;

public class Pavillon extends BddObject<Pavillon> {

    @PrimaryKey
    String idPavillon;
    String nom;
    @ForeignKey
    Devise devise;

    public String getIdPavillon() {
        return idPavillon;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception {
        if (nom == null) throw new Exception("Nom est null");
        if (nom.isEmpty()) throw new Exception("Nom est vide");
        this.nom = nom;
    }

    public void setIdPavillon(String idPavillon) throws Exception {
        if (idPavillon == null) throw new Exception("idPavillon est null");
        if (idPavillon.isEmpty()) throw new Exception("Le champ idPavillon est vide");
        this.idPavillon = idPavillon;
    }

    public void setDevise(Devise devise) throws Exception {
        if (devise == null) throw new Exception("idPavillon est null");
        this.devise = devise;
    }

    public Devise getDevise() {
        return devise;
    }

    public Pavillon() throws Exception {
        this.setTable("pavillon");
        this.setConnection("PostgreSQL");
    }
    
}
