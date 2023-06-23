INSERT INTO devise VALUES 
    ('DEV01EUR', 'euro'),
    ('DEV02USD', 'dollar'),
    ('DEV03MGA', 'ariary');

INSERT INTO type VALUES 
    ('TYP001', 'Petrolier'),
    ('TYP002', 'Paquebot'),
    ('TYP003', 'Commerciale');

INSERT INTO Pavillon VALUES 
    ('PAV01', 'Internationale', 'DEV01EUR'),
    ('PAV02', 'Nationale', 'DEV02USD');

INSERT INTO bateau VALUES
    ('BAT00PA' || nextval('seq_id_bateau') || 'N', 'B1', 23, 15, 'PAV01', 'TYP001'),
    ('BAT00CO' || nextval('seq_id_bateau') || 'I', 'B2', 40, 10, 'PAV02', 'TYP002'),
    ('BAT00PE' || nextval('seq_id_bateau') || 'I', 'B3', 35, 20, 'PAV02', 'TYP003');

INSERT INTO utilisateur VALUES
    ('UTI001', 'Tendry');

INSERT INTO quai VALUES
    ('QUA001', 'Q1', 30),
    ('QUA002', 'Q2', 40),
    ('QUA003', 'Q3', 25);
    ('QUA003', 'Q4', 25);

INSERT INTO prestation VALUES
    ('PRES001', 'Stationnement'),
    ('PRES002', 'Reparation'),
    ('PRES003', 'Approvisionnement');
