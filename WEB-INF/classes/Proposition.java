package prevision;

import bateau.Bateau;
import escale.Escale;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Vector;
import port.Quai;
import prevision.Prevision;
import port.Port;

public class Proposition extends Prevision {

    Quai quai;
    double attente;

    public double getAttente() {
        return attente;
    }

    public void setAttente(double attente) throws Exception {
        if (attente < 0) throw new Exception("Attente doit etre positif");
        this.attente = attente;
    }

    public void setAttente(String attente) throws Exception {
        if (attente == null) throw new Exception("Attente est null");
        if (attente.isEmpty()) throw new Exception("Attente est vide");
        this.setAttente(Double.parseDouble(attente));
    }

    public void setQuai(Quai quai) {
        this.quai = quai;
    }

    public void setQuai(String idQuai) throws Exception {
        if (idQuai == null) throw new Exception("Quai est null");
        if (idQuai.isEmpty()) throw new Exception("Quai est vide");
        Quai quai = new Quai();
        quai.setIdQuai(idQuai);
        this.setQuai(quai.getById());
    }

    public Quai getQuai() {
        return quai;
    }

    public Proposition() throws Exception {

    }

    public Proposition(String idPrevision, Bateau bateau, Timestamp arrive, Timestamp depart, String reference) throws Exception {
        super(idPrevision, bateau, arrive, depart, reference);
    }

    public static Proposition[] getPropositions(Connection connection) throws Exception {
        Proposition[] propositions = null;
        Prevision prevision = new Prevision();
        Prevision[] previsions = prevision.findAll(connection, "arrive ASC");
        propositions = new Proposition[previsions.length];
        Port port = new Port(new Quai().findAll(connection, null));
        for (int i = 0; i < propositions.length; i++) {
            propositions[i] = new Proposition(previsions[i].getIdPrevision(), previsions[i].getBateau(), previsions[i].getArrive(), previsions[i].getDepart(), previsions[i].getReference());
            port.enleverEscale(propositions[i].getArrive());
            Quai quai = port.getMinQuaiDisponible(propositions[i]);
            if (quai == null) {
                // ! Choix entre l'optimisation de temps et le gaspillage
                quai = port.findMinProfondeur(propositions[i]);
                propositions[i].attendre(quai);
                quai.setEscale(null);
            }
            quai.setEscale(new Escale(propositions[i].getBateau(), propositions[i].getArrive(), propositions[i].getDepart(), propositions[i].getReference()));
            propositions[i].setQuai(quai);
        }
        return propositions;
    }

    public static Proposition[] getPropositions() throws Exception {
        Proposition[] propositions = null;
        try (Connection connection = new Prevision().getConnection()) {
            propositions = getPropositions(connection);
        }
        return propositions;
    }

    public void attendre(Quai quai) throws Exception {
        if (quai.getEscale() == null) throw new Exception("Quai est libre");
        long retard = quai.getEscale().getDepart().getTime() - this.getArrive().getTime();
        this.setArrive(new Timestamp(this.getArrive().getTime() + retard));
        this.setDepart(new Timestamp(this.getDepart().getTime() + retard));
        this.setAttente(convertToMinute(retard));
    }

    public Escale createEscale() throws Exception{
        return new Escale(getBateau(),getArrive(), getDepart(), getReference());
    }
}
