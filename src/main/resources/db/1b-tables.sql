CREATE TABLE compte(
   id_compte BIGINT GENERATED BY DEFAULT AS IDENTITY,
   pseudo VARCHAR(25)  NOT NULL,
   email VARCHAR(50)  NOT NULL,
   empreinte_mdp VARCHAR(68)  NOT NULL,
   role_admin BOOLEAN NOT NULL DEFAULT FALSE,
   PRIMARY KEY(id_compte),
   UNIQUE(pseudo),
   UNIQUE(email)
);

CREATE TABLE Equipe (
   id_equipe BIGINT GENERATED BY DEFAULT AS IDENTITY,
   nom_equipe VARCHAR(50) NOT NULL,
	dossard VARCHAR(256),
	accord_donnee INT NOT NULL CHECK (accord_donnee IN (1,0)),
   PRIMARY KEY(id_equipe)
);

CREATE TABLE Participant (
   id_part BIGINT GENERATED BY DEFAULT AS IDENTITY,
	id_equipe BIGINT NOT NULL,
   nom_part VARCHAR(100),
   prenom_part VARCHAR(100),
   email_part VARCHAR(50),
   date_nais DATE,
   telephone_part VARCHAR(10),
   accord_image INT NOT NULL CHECK (accord_image IN (1,0)),
   certificat_med BYTEA NOT NULL,
   aut_parentale BYTEA,
   PRIMARY KEY(id_part),
	FOREIGN KEY(id_equipe) REFERENCES Equipe(id_equipe)
);

CREATE TABLE Poste (
   id_p BIGINT GENERATED BY DEFAULT AS IDENTITY,
   nom VARCHAR(50) NOT NULL,
   description VARCHAR(256) ,
   heure_debut TIME,
   heure_fin TIME,
   PRIMARY KEY(id_p)
);

CREATE TABLE Benevole (
   id_ben BIGINT GENERATED BY DEFAULT AS IDENTITY,
   type VARCHAR(20) NOT NULL,
   nom_ben VARCHAR(100),
   prenom VARCHAR(100),
   email VARCHAR(100),
   telephone VARCHAR(20),
   disponibilite DATE NOT NULL,
   PRIMARY KEY(id_ben)
);

CREATE TABLE Assignation (
   id_assignation BIGINT GENERATED BY DEFAULT AS IDENTITY,
   id_ben BIGINT NOT NULL,
   id_p BIGINT NOT NULL,
   date_affectation DATE DEFAULT CURRENT_DATE,
   PRIMARY KEY(id_assignation),
   FOREIGN KEY(id_ben) REFERENCES Benevole(id_ben),
   FOREIGN KEY(id_p) REFERENCES Poste(id_p)
);

CREATE TABLE Evenement (
   id_ev BIGINT GENERATED BY DEFAULT AS IDENTITY,
   nom VARCHAR(100) NOT NULL,
   nb_repas INT NOT NULL,
   nbre_participants INT,
   date_evenement DATE NOT NULL,
   lieu VARCHAR(100) NOT NULL,
   heure TIME,
	date_limite_inscription DATE NOT NULL,
   tarif_repas NUMERIC(9,2) NOT NULL,
   PRIMARY KEY(id_ev)
);

CREATE TABLE Notation (
   id BIGINT GENERATED BY DEFAULT AS IDENTITY,
   note INT NOT NULL CHECK (note BETWEEN 0 AND 10),
   commentaire VARCHAR(256),
   id_poste BIGINT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_poste) REFERENCES Poste(id_p)
);

CREATE TABLE Inscription_Equipe (
   id_inscription BIGINT GENERATED BY DEFAULT AS IDENTITY,
   id_equipe BIGINT NOT NULL,
   id_ev BIGINT NOT NULL,
   date_inscription TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   statut VARCHAR(50) DEFAULT 'en_attente' CHECK (statut IN ('Attente', 'Validé', 'Refusé')),
   PRIMARY KEY(id_inscription),
   FOREIGN KEY(id_equipe) REFERENCES Equipe(id_equipe),
   FOREIGN KEY(id_ev) REFERENCES Evenement(id_ev),
   CONSTRAINT unique_equipe_event UNIQUE (id_equipe, id_ev)
);