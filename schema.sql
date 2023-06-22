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

CREATE table debut_escale(
    id_debut varchar(7) primary key,
    reference varchar(50) references prevision(reference),
    debut timestamp not null
);

CREATE SEQUENCE seq_id_debut
    start with 1
    increment by 1
    minvalue 0;

CREATE table fin_escale(
    id_fin varchar(7) primary key,
    id_debut varchar(7) references debut_escale(id_debut),
    fin timestamp not null,
    cours DOUBLE PRECISION NOT NULL DEFAULT 0
);

CREATE SEQUENCE seq_id_fin
    start with 1
    increment by 1
    minvalue 0;

CREATE TABLE escale_prestation(
    id_escale_prestation varchar(7) primary key,
    id_prestation varchar(7) references prestation(idPrestation),
    reference varchar(7) references prevision(reference),
    id_quai varchar(7) references quai(idQuai),
    debut timestamp,
    fin timestamp,
    prix double precision,
    etat INTEGER
);

CREATE SEQUENCE seq_id_escale_prestation
    start with 1
    increment by 1
    minvalue 0;

CREATE OR REPLACE VIEW v_liste_prevision_a_venir AS
SELECT *
FROM prevision
WHERE arrive > NOW();

create or replace view v_escale as
    select p.idBateau as idBateau, de.debut as debut,
    f_e.fin as fin, p.reference as reference , f_e.cours as cours
    from prevision as p
    join debut_escale as de
    on de.reference = p.reference
    left join fin_escale as f_e
    on f_e.id_debut = de.id_debut;
