package port;

import java.sql.Timestamp;
import java.util.Vector;

// import element.Proposition;

import connection.BddObject;
import port.Quai;
import escale.Escale;
import prevision.Prevision;
import prevision.Proposition;
import escale.Escale;

public class Port {

    Quai[] quais;
    Proposition[] propositions;
    Escale[] escales;

    public void setEscales( Escale[] escales ) throws Exception {
        if( escales == null ) {
            throw new Exception( "The escales can't be bull" );
        }
        this.escales = escales;
    }

    public Escale[] getEscales(){
        return this.escales;
    }


    public void setPropositions( Proposition[] propositions ) throws Exception{
        if( propositions == null ){
            throw new Exception( "The propositions can't be bull" );
        }
        this.propositions = propositions;
    }

    public Proposition[] getPropositions(){
        return this.propositions;
    }

    public Quai[] getQuais() {
        return quais;
    }

    public void setQuais(Quai[] quais) throws Exception {
        if (quais == null) throw new Exception("Quais est null");
        if (quais.length <= 0) throw new Exception("Il n'y pas de quais dans ce port");
        this.quais = quais;
    }

    public Port(Quai[] quais) throws Exception {
        this.setQuais(quais);
    }

    public Port(){}

    public void enleverEscale(Timestamp date) {
        for (Quai quai : quais) {
            if (quai.getEscale() != null && quai.getEscale().getDepart().compareTo(date) <= 0) {
                quai.setEscale(null);
            }
        }
    }

    public Vector<Quai> getQuaiDisponible() {
        Vector<Quai> disponible = new Vector<>();
        for (Quai quai : quais) {
            if (quai.isDisponible()) disponible.add(quai);
        }
        return disponible;
    }

    public Quai getMinQuaiDisponible(Prevision prevision) {
        Vector<Quai> disponible = getQuaiDisponible();
        Quai min = null;
        for (Quai quai : disponible) {
            double difference = quai.getProfondeur() - prevision.getBateau().getProfondeur();
            if (difference >= 0) {
                if (min == null || difference < min.getProfondeur() - prevision.getBateau().getProfondeur()) {
                    min = quai;
                }
            }
        }
        return min;
    }

    // ! Reflexion sur les priorites faire un scenario
    public Quai findTimeMinQuai(Prevision prevision) {
        Quai min = null;
        for (Quai quai : quais) {
            if (quai.getEscale() != null) {
                double difference = quai.getEscale().getDepart().getTime() - prevision.getArrive().getTime();
                if (min == null || difference <= min.getEscale().getDepart().getTime() - prevision.getArrive().getTime()) {
                    min = quai;
                }
            }
        }
        return min;
    }

    public Quai findMinProfondeur(Prevision prevision) {
        Quai min = null;
        for (Quai quai : quais) {
            if (quai.getEscale() != null) {
                double difference = quai.getProfondeur() - prevision.getBateau().getProfondeur();
                if (min == null || difference <= min.getProfondeur() - prevision.getBateau().getProfondeur()) {
                    min = quai;
                }
            }
        }
        return min;
    }

    public static Port createPort() throws Exception{
        Port port = new Port();
        try( java.sql.  Connection connection = BddObject.getPostgreSQL() ){
            Proposition[] props = Proposition.getPropositions(connection);
            Escale[] escales = new Escale().findAll( connection, null );
            port.setEscales( escales );
            port.setPropositions( props );
            return port;
        }
    }

}
