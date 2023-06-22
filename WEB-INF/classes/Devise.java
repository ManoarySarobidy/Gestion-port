package bateau;

import connection.BddObject;
import connection.annotation.PrimaryKey;

public class Devise extends BddObject<Devise> {

    @PrimaryKey
    String idDevise;
    String valeur;

    public String getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(String idDevise) throws Exception {
        if (idDevise == null) throw new Exception("Le champ idDevise est null");
        if (idDevise.isEmpty()) throw new Exception("Le champ idDevise est vide");
        this.idDevise = idDevise;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) throws Exception {
        if (valeur == null) throw new Exception("Le champ valeur est null");
        if (valeur.isEmpty()) throw new Exception("Le champ valeur est vide");
        this.valeur = valeur;
    }

    public Devise() throws Exception {
        this.setTable("devise");
    }

}
