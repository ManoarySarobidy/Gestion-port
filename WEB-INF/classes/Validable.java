package validation;

import java.sql.Connection;
import java.sql.Statement;
import connection.BddObject;
import utilisateur.Utilisateur;
import utilisateur.Profile;

public class Validable extends BddObject<Validable> {
    
    String id;
    String primaryKey;
    int etat;
    String validation;
    Profile profile;

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public String getValidation() {
        return validation;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getEtat() {
        return etat;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getEtatLettre() {
        String lettre = "";
        switch (this.getEtat()) {
            case 1:
                lettre = "Cree";
                break;
            case 10:
                lettre = "Valide";
                break;
            case 20:
                lettre = "Paye";
                break;
            default:
                lettre = "Pas de status";
                break;
        }
        return lettre;
    }

    public void validate(Utilisateur user) throws Exception {
        if (user == null) throw new Exception("Vous devait etre authentifier pour pouvoir faire des validations");
        if (!user.getProfile().getIdProfile().equals(this.getProfile().getIdProfile())) throw new Exception("Votre profile " + user.getProfile().getNom() + " n'a pas acces a cette validation");
        Connection connection = null;
        try {
            connection = BddObject.getPostgreSQL();
            this.update(connection, user);
            connection.commit();
        } catch (Exception e) {
            if (connection != null) connection.rollback();
            throw e;
        } finally {
            if (connection != null) connection.close();
        }
    }

    public void update(Connection connection, Utilisateur user) throws Exception {
        String sql = "UPDATE %s SET etat = 10 WHERE %s='%s'";
        sql = String.format(sql, this.getTable(), this.getPrimaryKey(), this.getId());
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        sql = "INSERT INTO %s (id_validation, id_user, %s) VALUES ('%s', '%s', '%s')";
        this.setPrefix("VAL");
        this.setCountPK(9);
        this.setFunctionPK("nextval('seq_id_validation')");
        sql = String.format(sql, this.getValidation(), this.getPrimaryKey(), this.buildPrimaryKey(connection), user.getIdUtilisateur(), this.getId());
        statement.executeUpdate(sql);
        statement.close();
    }

}
