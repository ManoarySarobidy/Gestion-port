package bateau;

import connection.annotation.PrimaryKey;
import connection.BddObject;

public class TypeBateau extends BddObject<TypeBateau> {
    
    @PrimaryKey
    String idType;
    String nom;

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) throws Exception {
        if (idType  == null) throw new Exception("ID Type est null");
        if (idType.isEmpty()) throw new Exception("ID Type est vide");
        this.idType = idType;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception {
        if (nom == null) throw new Exception("Nom du type de bateau est null");
        if (nom.isEmpty()) throw new Exception("Nom du type de bateau est vide");
        this.nom = nom;
    }

    public TypeBateau() throws Exception {
        this.setTable("type");
        this.setConnection("PostgreSQL");
    }

}
