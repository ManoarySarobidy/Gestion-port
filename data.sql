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
    ('QUA001', 'Q1', 35),
    ('QUA002', 'Q2', 45),
    ('QUA003', 'Q3', 25);

INSERT INTO prestation VALUES
    ('PRES001', 'Stationnement'),
    ('PRES002', 'Reparation'),
    ('PRES003', 'Approvisionnement');

INSERT INTO limite VALUES
    ('LIMST01', 'PRES001', 60, 10000),
    ('LIMRE02', 'PRES002', 40, 15000);

INSERT INTO tranche VALUES
    ('TRAST01', 'PRES001', 0, 15),
    ('TRAST02', 'PRES001', 15, 30),
    ('TRAST03', 'PRES001', 30, 45),
    ('TRAST04', 'PRES001', 45, 60),
    ('TRAST04', 'PRES001', 45, 'infinity');

INSERT INTO detailtarif VALUES
    ('TARSTQ1PEI001', 'QUA001', 'PRES001', 'TYP001', 'PAV01'),
    ('TARSTQ1PEN001', 'QUA001', 'PRES001', 'TYP001', 'PAV02'),
    ('TARSTQ1PAI002', 'QUA001', 'PRES001', 'TYP002', 'PAV01'),
    ('TARSTQ1PAN002', 'QUA001', 'PRES001', 'TYP002', 'PAV02'),
    ('TARSTQ1COI003', 'QUA001', 'PRES001', 'TYP003', 'PAV01'),
    ('TARSTQ1CON003', 'QUA001', 'PRES001', 'TYP003', 'PAV02');

INSERT INTO tarif VALUES
    ('TAR001', 'TARSTQ1PEI001', 'TRAST01', 20000),
    ('TAR002', 'TARSTQ1PEI001', 'TRAST02', 30000),
    ('TAR003', 'TARSTQ1PEI001', 'TRAST03', 45000),
    ('TAR004', 'TARSTQ1PEI001', 'TRAST04', 60000),
    ('TAR005', 'TARSTQ1PEN001', 'TRAST01', 40000),
    ('TAR006', 'TARSTQ1PEN001', 'TRAST02', 55000),
    ('TAR007', 'TARSTQ1PEN001', 'TRAST03', 60000),
    ('TAR008', 'TARSTQ1PEN001', 'TRAST04', 72000),
    ('TAR009', 'TARSTQ1PAI002', 'TRAST01', 5000),
    ('TAR010', 'TARSTQ1PAI002', 'TRAST02', 8000),
    ('TAR011', 'TARSTQ1PAI002', 'TRAST03', 10000),
    ('TAR012', 'TARSTQ1PAI002', 'TRAST04', 22000),
    ('TAR013', 'TARSTQ1PAN002', 'TRAST01', 10000),
    ('TAR014', 'TARSTQ1PAN002', 'TRAST02', 16000),
    ('TAR015', 'TARSTQ1PAN002', 'TRAST03', 20000),
    ('TAR016', 'TARSTQ1PAN002', 'TRAST04', 23000),
    ('TAR017', 'TARSTQ1COI003', 'TRAST01', 10000),
    ('TAR018', 'TARSTQ1COI003', 'TRAST02', 12000),
    ('TAR019', 'TARSTQ1COI003', 'TRAST03', 20000),
    ('TAR020', 'TARSTQ1COI003', 'TRAST04', 25000),
    ('TAR021', 'TARSTQ1CON003', 'TRAST01', 20000),
    ('TAR022', 'TARSTQ1CON003', 'TRAST02', 23000),
    ('TAR023', 'TARSTQ1CON003', 'TRAST03', 25000),
    ('TAR024', 'TARSTQ1CON003', 'TRAST04', 30000);