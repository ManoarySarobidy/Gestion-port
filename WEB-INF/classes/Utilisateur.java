package utilisateur;

import connection.BddObject;
import escale.Prestation;
import escale.Escale;
import escale.Tarif;
import connection.annotation.PrimaryKey;
import connection.annotation.ForeignKey;

public class Utilisateur extends BddObject<Utilisateur> {

    @PrimaryKey
    String idUtilisateur;
    String nom;
    @ForeignKey
    Profile profile;

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    public Profile getProfile() {
        return profile;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception {
        if (nom == null) throw new Exception("Nom de l'Utilisateur est null");
        if (nom.isEmpty()) throw new Exception("Nom de l'utilisateur est vide");
        this.nom = nom;
    }

    public void setIdUtilisateur(String idUtilisateur) throws Exception {
        if (idUtilisateur == null) throw new Exception("L'ID Utilisateur est null");
        if (idUtilisateur.isEmpty()) throw new Exception("ID" + this.getClass().getSimpleName() + " est vide");
        this.idUtilisateur = idUtilisateur;
    }

    public Utilisateur(String nom) throws Exception {
        this();
        this.setNom(nom);
    }

    public Utilisateur(String idUtilisateur, String nom, Profile profile) throws Exception {
        this();
        this.setIdUtilisateur(idUtilisateur);
        this.setNom(nom);
        this.setProfile(profile);
    }

    public Utilisateur() throws Exception {
        this.setTable("utilisateur");
    }

}
