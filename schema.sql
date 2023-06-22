CREATE TABLE devise (
    idDevise VARCHAR(50) PRIMARY KEY,
    valeur VARCHAR(50) NOT NULL
);

CREATE TABLE pavillon (
    idPavillon VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    idDevise VARCHAR(50) REFERENCES devise(idDevise)
);

CREATE TABLE type (
    idType VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(50) NOT NULL
);

CREATE SEQUENCE seq_id_bateau
    start with 1
    increment by 1
    minvalue 0;

CREATE TABLE bateau (
    idBateau VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    profondeur DOUBLE PRECISION NOT NULL DEFAULT 0,
    remorquage INTEGER NOT NULL DEFAULT 0,
    idPavillon VARCHAR REFERENCES pavillon(idPavillon),
    idType VARCHAR REFERENCES type(idType)
);

CREATE SEQUENCE seq_id_prevision
    start with 1
    increment by 1
    minvalue 0
    maxvalue 100
    cycle;

CREATE SEQUENCE seq_escale
    start with 1
    increment by 1
    minvalue 0
    maxvalue 1000;

CREATE TABLE prevision (
    idPrevision VARCHAR(50) PRIMARY KEY,
    idBateau VARCHAR(50) REFERENCES bateau(idBateau),
    arrive TIMESTAMP NOT NULL,
    depart TIMESTAMP NOT NULL,
    reference VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE utilisateur (
    idUtilisateur VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(50) NOT NULL
);

CREATE SEQUENCE seq_id_historique
    start with 1
    increment by 1
    minvalue 0;

CREATE TABLE historique (
    idHistorique VARCHAR(50) PRIMARY KEY,
    idPrevision VARCHAR(50) REFERENCES prevision(idPrevision),
    idUtilisateur VARCHAR(50) REFERENCES utilisateur(idUtilisateur),
    action VARCHAR(50) NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE quai (
    idQuai VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    profondeur DOUBLE PRECISION NOT NULL DEFAULT 0
);

CREATE TABLE prestation (
    idPrestation VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(50) NOT NULL
);

CREATE TABLE tarif (
    idTarif VARCHAR(50) PRIMARY KEY,
    idQuai VARCHAR(50) REFERENCES quai(idQuai),
    idPrestation VARCHAR(50) REFERENCES prestation(idPrestation),
    idType VARCHAR(50) REFERENCES type(idType),
    heure_debut TIME WITH TIME ZONE NOT NULL,
    heure_fin TIME WITH TIME ZONE NOT NULL,
    majoration DOUBLE PRECISION NOT NULL DEFAULT 0,
    debut DOUBLE PRECISION NOT NULL DEFAULT 0,
    fin DOUBLE PRECISION NOT NULL DEFAULT 0,
    idPavillon VARCHAR(50) REFERENCES pavillon(idPavillon),
    prix DOUBLE PRECISION NOT NULL DEFAULT 0
);

CREATE OR REPLACE VIEW v_liste_prevision_a_venir AS
SELECT *
FROM prevision
WHERE arrive > NOW();
