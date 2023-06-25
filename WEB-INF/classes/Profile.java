package utilisateur;

import connection.BddObject;
import connection.annotation.ColumnName;
import connection.annotation.PrimaryKey;

public class Profile extends BddObject<Profile> {
    
    @ColumnName("id_profile")
    @PrimaryKey
    String idProfile;
    String nom;

    public void setIdProfile(String idProfile) {
        this.idProfile = idProfile;
    }
    
    public String getIdProfile() {
        return idProfile;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Profile() throws Exception {
        this.setTable("profile");
        this.setConnection("PostgreSQL");
    }

    public Profile(String idProfile) throws Exception {
        this();
        this.setIdProfile(idProfile);
    }
    
    public Profile(String idProfile, String nom) throws Exception {
        this(idProfile);
        this.setNom(nom);
    }
    
}
