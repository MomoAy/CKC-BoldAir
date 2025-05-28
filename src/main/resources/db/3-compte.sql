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
