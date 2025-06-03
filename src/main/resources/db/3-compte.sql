-------
-- Ajout d'extension à PostgreSQL
-------
DROP EXTENSION IF EXISTS pgcrypto;
CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


-------
-- compte
-------

INSERT INTO compte (id_compte, pseudo, empreinte_mdp, email, role_admin ) VALUES
( 1, 'admin', crypt( 'admin', gen_salt('bf')), 'admin@mail.com', TRUE ),
( 2, 'max', crypt('max', gen_salt('bf')), 'max@mail.com', FALSE ),
( 3, 'mika', crypt('mika', gen_salt('bf')), 'mika@mail.com', FALSE ),
( 4, 'tom', crypt('tom', gen_salt('bf')), 'tom@mail.com', FALSE ),
( 5, 'eva', crypt('eva', gen_salt('bf')), 'eva@mail.com', FALSE );

ALTER TABLE compte      ALTER COLUMN id_compte     RESTART WITH 10;

INSERT INTO boldair.evenement (nom, nb_repas, nbre_participants, date_evenement, lieu, heure, date_limite_inscription, tarif_repas) VALUES (
    'BOL DAIR',
    3,
    100,
    '2025-06-15',
    'Parc National Fazao-Malfakassa',
    '08:30:00',
    '2025-06-10',
    2500
);


INSERT INTO Benevole (type, nom_ben, prenom, email, telephone, disponibilite) VALUES
('interne', 'Durand', 'Alice', 'alice.durand@boldair.org', '0612345678', '2025-06-01'),
('externe', 'Martin', 'Louis', 'louis.martin@gmail.com', '0623456789', '2025-07-10'),
('interne', 'Bernard', 'Claire', 'claire.bernard@boldair.org', '0634567890', '2025-05-30'),
('externe', 'Petit', 'Julien', 'julien.petit@yahoo.com', '0645678901', '2025-08-05'),
('interne', 'Robert', 'Sophie', 'sophie.robert@boldair.org', '0656789012', '2025-06-20'),
('externe', 'Richard', 'Paul', 'paul.richard@outlook.com', '0667890123', '2025-07-15'),
('interne', 'Durand', 'Emma', 'emma.durand@boldair.org', '0678901234', '2025-07-01'),
('externe', 'Lefevre', 'Lucas', 'lucas.lefevre@gmail.com', '0689012345', '2025-08-01'),
('interne', 'Moreau', 'Camille', 'camille.moreau@boldair.org', '0690123456', '2025-09-01'),
('externe', 'Fournier', 'Hugo', 'hugo.fournier@gmail.com', '0601234567', '2025-06-10');


INSERT INTO Equipe (nom_equipe, dossard, accord_donnee) VALUES
('Les Rapides', 'RAP001', 1),
('Les Aiglons', 'AIG002', 1),
('Team Phoenix', 'PHX003', 0),
('Les Tigres', 'TIG004', 1),
('Code Warriors', 'CWR005', 1);



INSERT INTO Participant
(id_equipe, nom_part, prenom_part, email_part, date_nais, telephone_part, accord_image, certificat_med, aut_parentale)
VALUES
-- Équipe 1
(1, 'Martin', 'Julie', 'julie.martin@example.com', '2004-05-10', '0611223344', 1,
 decode('UERGQ2VydDE=', 'base64'), decode('QXV0UGFyZW50MU==', 'base64')),
(1, 'Durand', 'Leo', 'leo.durand@example.com', '2004-11-20', '0611223345', 1,
 decode('UERGQ2VydDI=', 'base64'), decode('QXV0UGFyZW50Mg==', 'base64')),

-- Équipe 2
(2, 'Lemoine', 'Sarah', 'sarah.lemoine@example.com', '2005-06-20', '0611223346', 1,
 decode('UERGQ2VydDM=', 'base64'), decode('QXV0UGFyZW50Mw==', 'base64')),
(2, 'Bernard', 'Hugo', 'hugo.bernard@example.com', '2005-09-30', '0611223347', 1,
 decode('UERGQ2VydDQ=', 'base64'), decode('QXV0UGFyZW50NA==', 'base64')),

-- Équipe 3
(3, 'Thomas', 'Alice', 'alice.thomas@example.com', '2003-02-14', '0611223348', 1,
 decode('UERGQ2VydDU=', 'base64'), decode('QXV0UGFyZW50NQ==', 'base64')),
(3, 'Roux', 'Lucas', 'lucas.roux@example.com', '2003-07-18', '0611223349', 1,
 decode('UERGQ2VydDY=', 'base64'), decode('QXV0UGFyZW50Ng==', 'base64')),

-- Équipe 4
(4, 'Girard', 'Emma', 'emma.girard@example.com', '2006-01-10', '0611223350', 1,
 decode('UERGQ2VydDc=', 'base64'), decode('QXV0UGFyZW50Nw==', 'base64')),
(4, 'Noel', 'Arthur', 'arthur.noel@example.com', '2006-03-25', '0611223351', 1,
 decode('UERGQ2VydDg=', 'base64'), decode('QXV0UGFyZW50OA==', 'base64')),

-- Équipe 5
(5, 'Robin', 'Chloe', 'chloe.robin@example.com', '2004-12-05', '0611223352', 1,
 decode('UERGQ2VydDk=', 'base64'), decode('QXV0UGFyZW50OQ==', 'base64')),
(5, 'Mercier', 'Nino', 'nino.mercier@example.com', '2004-08-15', '0611223353', 1,
 decode('UERGQ2VydDEw', 'base64'), decode('QXV0UGFyZW50MTA=', 'base64'));

 INSERT INTO Inscription_Equipe (id_equipe, id_ev, statut) VALUES
(1, 1, 'Attente'),
(2, 1, 'Validé'),
(3, 1, 'Refusé'),
(4, 1, 'Attente'),
(5, 1, 'Validé');

