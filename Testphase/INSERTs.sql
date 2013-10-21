
-- -----------------------------------------------------
-- Data for table `fallstudie`.`Bereich`
-- -----------------------------------------------------
START TRANSACTION;
USE `fallstudie`;
INSERT INTO `fallstudie`.`Bereich` (`BereichID`, `Kurzbezeichnung`, `Beschreibung`, `Aktiv`, `Leiter`) VALUES (1, 'LG', 'Leistung Grundsatz', 1, NULL);
INSERT INTO `fallstudie`.`Bereich` (`BereichID`, `Kurzbezeichnung`, `Beschreibung`, `Aktiv`, `Leiter`) VALUES (2, 'LL', 'Leistung Liquidation', 1, NULL);
INSERT INTO `fallstudie`.`Bereich` (`BereichID`, `Kurzbezeichnung`, `Beschreibung`, `Aktiv`, `Leiter`) VALUES (3, 'GT', 'Gesundheitsmanagement', 1, NULL);
INSERT INTO `fallstudie`.`Bereich` (`BereichID`, `Kurzbezeichnung`, `Beschreibung`, `Aktiv`, `Leiter`) VALUES (4, 'LD', 'Leistungsdienst', 1, NULL);
INSERT INTO `fallstudie`.`Bereich` (`BereichID`, `Kurzbezeichnung`, `Beschreibung`, `Aktiv`, `Leiter`) VALUES (5, 'SC Hamburg', 'Service Center Hamburg', 1, NULL);
INSERT INTO `fallstudie`.`Bereich` (`BereichID`, `Kurzbezeichnung`, `Beschreibung`, `Aktiv`, `Leiter`) VALUES (6, 'SC Düsseldorf', 'Service Center Düsseldorf', 1, NULL);
INSERT INTO `fallstudie`.`Bereich` (`BereichID`, `Kurzbezeichnung`, `Beschreibung`, `Aktiv`, `Leiter`) VALUES (7, 'SC Frankfurt', 'Service Center Frankfurt', 1, NULL);
INSERT INTO `fallstudie`.`Bereich` (`BereichID`, `Kurzbezeichnung`, `Beschreibung`, `Aktiv`, `Leiter`) VALUES (8, 'SC Mannheim', 'Service Center Mannheim', 1, NULL);
INSERT INTO `fallstudie`.`Bereich` (`BereichID`, `Kurzbezeichnung`, `Beschreibung`, `Aktiv`, `Leiter`) VALUES (9, 'SC Stuttgart', 'Service Center Stuttgart', 1, NULL);
INSERT INTO `fallstudie`.`Bereich` (`BereichID`, `Kurzbezeichnung`, `Beschreibung`, `Aktiv`, `Leiter`) VALUES (10, 'SC München', 'Service Center München', 1, NULL);

COMMIT;
-- -----------------------------------------------------
-- Data for table `fallstudie`.`Arbeitsgruppe`
-- -----------------------------------------------------
START TRANSACTION;
USE `fallstudie`;
INSERT INTO `fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`, `Leiter`, `Bereich`, `Beschreibung`, `Kurzbezeichnung`, `Aktiv`) VALUES (1, NULL, 1, 'Leistung Grundsatzfragen', 'Leistung Grundsatzfragen', 1);
INSERT INTO `fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`, `Leiter`, `Bereich`, `Beschreibung`, `Kurzbezeichnung`, `Aktiv`) VALUES (2, NULL, 1, 'DRG', 'DRG', 1);
INSERT INTO `fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`, `Leiter`, `Bereich`, `Beschreibung`, `Kurzbezeichnung`, `Aktiv`) VALUES (3, NULL, 1, 'Basistarif,Ruhen,M&M,Notlagentarif', 'Basistarif,Ruhen,M&M,Notlagentarif', 1);
INSERT INTO `fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`, `Leiter`, `Bereich`, `Beschreibung`, `Kurzbezeichnung`, `Aktiv`) VALUES (4, NULL, 2, 'GOÃ„', 'GOÃ„', 1);
INSERT INTO `fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`, `Leiter`, `Bereich`, `Beschreibung`, `Kurzbezeichnung`, `Aktiv`) VALUES (5, NULL, 2, 'GOZ', 'GOZ', 1);
INSERT INTO `fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`, `Leiter`, `Bereich`, `Beschreibung`, `Kurzbezeichnung`, `Aktiv`) VALUES (6, NULL, 3, 'Casemanagement', 'Casemanagement', 1);
INSERT INTO `fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`, `Leiter`, `Bereich`, `Beschreibung`, `Kurzbezeichnung`, `Aktiv`) VALUES (7, NULL, 3, 'Discasemanagement', 'Discasemanagement', 1);
INSERT INTO `fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`, `Leiter`, `Bereich`, `Beschreibung`, `Kurzbezeichnung`, `Aktiv`) VALUES (8, NULL, 3, 'Gesundheitstelefon', 'Gesundheitstelefon', 1);
INSERT INTO `fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`, `Leiter`, `Bereich`, `Beschreibung`, `Kurzbezeichnung`, `Aktiv`) VALUES (9, NULL, 4, 'MLP', 'MLP', 1);
INSERT INTO `fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`, `Leiter`, `Bereich`, `Beschreibung`, `Kurzbezeichnung`, `Aktiv`) VALUES (10, NULL, 4, 'Pflegeversicherung', 'Pflegeversicherung', 1);
INSERT INTO `fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`, `Leiter`, `Bereich`, `Beschreibung`, `Kurzbezeichnung`, `Aktiv`) VALUES (11, NULL, 4, 'Gruppenversicherung', 'Gruppenversicherung', 1);
INSERT INTO `fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`, `Leiter`, `Bereich`, `Beschreibung`, `Kurzbezeichnung`, `Aktiv`) VALUES (12, NULL, 4, 'Auslandsreieversicherung', 'Auslandsreiseversicherung', 1);
INSERT INTO `fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`, `Leiter`, `Bereich`, `Beschreibung`, `Kurzbezeichnung`, `Aktiv`) VALUES (13, NULL, 4, 'Haustarif', 'Haustarif', 1);

COMMIT;




-- -----------------------------------------------------
-- Data for table `fallstudie`.`Rolle`
-- -----------------------------------------------------
START TRANSACTION;
USE `fallstudie`;
INSERT INTO `fallstudie`.`Rolle` (`Rollenbezeichnung`) VALUES ('Bereichsleiter');
INSERT INTO `fallstudie`.`Rolle` (`Rollenbezeichnung`) VALUES ('Fachbereichsorganisation');
INSERT INTO `fallstudie`.`Rolle` (`Rollenbezeichnung`) VALUES ('Gruppenleiter');
INSERT INTO `fallstudie`.`Rolle` (`Rollenbezeichnung`) VALUES ('Sachbearbeiter');
INSERT INTO `fallstudie`.`Rolle` (`Rollenbezeichnung`) VALUES ('Zentralbereichsleiter');

COMMIT;


-- -----------------------------------------------------
-- Data for table `fallstudie`.`Mitarbeiter`
-- -----------------------------------------------------
START TRANSACTION;
USE `fallstudie`;
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Am1', NULL, 'Bereichsleiter', sha1('Am1') , 1, 'Anna', 'Maier', NULL, 1, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Dh2', NULL, 'Bereichsleiter', sha1('Dh2') , 1, 'Daniel', 'Hansen', NULL, 2, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Ms3', NULL, 'Bereichsleiter', sha1('Ms3') , 1, 'Manuel', 'Straus', NULL, 3, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Am4', NULL, 'Bereichsleiter', sha1('Am4') , 1, 'Andreas', 'Mayer', NULL, 4, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Mn5', 1, 'Gruppenleiter', sha1('Mn5') , 1, 'Max', 'Neumann', NULL, 1, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Nv6', 2, 'Gruppenleiter', sha1('Nv6') , 1, 'Nora', 'Vogt', NULL, 1, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Ge7', 3, 'Gruppenleiter', sha1('Ge7') , 1, 'Günter', 'Ehlert', NULL, 1, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Lk9', 4, 'Gruppenleiter', sha1('Lk9') , 1, 'Lena', 'Krause', NULL, 2, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Ch10', 5, 'Gruppenleiter', sha1('Ch10') , 1, 'Charlote', 'Held', NULL, 2, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('No11', 6, 'Gruppenleiter', sha1('No11'), 1, 'Niklas', 'Ostwald', NULL, 3, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Ln12', 7, 'Gruppenleiter', sha1('Ln12'), 1, 'Luisa', 'Nissen', NULL, 3, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Jr13', 8, 'Gruppenleiter', sha1('Jr13'), 1, 'Jakob', 'Rohrbach', NULL, 3, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Eh14', 9, 'Gruppenleiter', sha1('Eh14'), 1, 'Elias', 'Hilgert', NULL, 4, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Sp15', 10, 'Gruppenleiter', sha1('Sp15'), 1, 'Sarah', 'Pacher', NULL, 4, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Em16', 11, 'Gruppenleiter', sha1('Em16'), 1, 'Emma', 'Maier', NULL, 4, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Ll17', 12, 'Gruppenleiter', sha1('Ll17'), 1, 'Lea', 'Lenski', NULL, 4, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Bs18', 13, 'Gruppenleiter', sha1('Bs18'), 1, 'Ben', 'Schmidt', NULL, 4, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Kw19', NULL, 'Fachbereichsorganisation', sha1('Kw19'), 1, 'Kim', 'Wagner', NULL, NULL, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Jw20', NULL, 'Fachbereichsorganisation', sha1('Jw20'), 1, 'Jan', 'Wolf', NULL, NULL, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Mk21', NULL, 'Zentralbereichsleiter', sha1('Mk21'), 1, 'Mario', 'Koch', NULL, NULL, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Th23', 8, 'Sachbearbeiter', sha1('Th23'), 1, 'Tim', 'Hoffmann', NULL, 3, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Lk24', 13, 'Sachbearbeiter', sha1('Lk24'), 1, 'Lena', 'Krause', NULL, 4, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Nr25', 1, 'Sachbearbeiter', sha1('Nr25'), 1, 'Noah', 'Reiser', NULL, 1, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Es26', 2, 'Sachbearbeiter', sha1('Es26'), 1, 'Erik', 'Schweiger', NULL, 1, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Kw27', 3, 'Sachbearbeiter', sha1('Kw27'), 1, 'Klara', 'Weigel', NULL, 1, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Hr29', 4, 'Sachbearbeiter', sha1('Hr29'), 1, 'Heinz', 'Raubach', NULL, 2, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Ms30', 5, 'Sachbearbeiter', sha1('Ms30'), 1, 'Manfred', 'Spielberg', NULL, 2, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Aw31', 6, 'Sachbearbeiter', sha1('Aw31'), 1, 'Annika', 'Welzenbach', NULL, 3, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Tr32', 7, 'Sachbearbeiter', sha1('Tr32'), 1, 'Tom', 'Riemann', NULL, 3, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Ca33', 8, 'Sachbearbeiter', sha1('Ca33'), 1, 'Christian', 'Albersmann', NULL, 4, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Cd34', 9, 'Sachbearbeiter', sha1('Cd45'), 1, 'Christin', 'Dorster', NULL, 4, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Mz35', 10, 'Sachbearbeiter', sha1('Mz35'), 1, 'Mike', 'Zabel', NULL, 4, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Nm36', 11, 'Sachbearbeiter', sha1('Nm36'), 1, 'Nadine', 'Müller', NULL, 4, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Kt37', 12, 'Sachbearbeiter', sha1('Kt37'), 1, 'Karin', 'Thiele', NULL, 4, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Gr38', 13, 'Sachbearbeiter', sha1('Gr38'), 1, 'Georg', 'Reichert', NULL, 4, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Lm39', 5, 'Sachbearbeiter', sha1('Lm39'), 1, 'Lothar', 'Matthäus', NULL, 2, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Tm40', 4, 'Sachbearbeiter', sha1('Tm40'), 1, 'Thomas', 'Müller', NULL, 2, 0);
INSERT INTO `fallstudie`.`Mitarbeiter` (`Benutzername`, `Arbeitsgruppe`, `Rolle`, `Passwort`, `Aktiv`, `Vorname`, `Nachname`, `LetzterLogin`, `Bereich`, `PWChanged`) VALUES ('Mm41', 10, 'Sachbearbeiter', sha1('Mm41'), 1, 'Max', 'Meyer', NULL, 4, 0);

COMMIT;



-- -----------------------------------------------------
-- Data for table `fallstudie`.`Art`
-- -----------------------------------------------------
START TRANSACTION;
USE `fallstudie`;
INSERT INTO `fallstudie`.`Art` (`Name`, `Aktiv`) VALUES ('Schriftwechsel', 1);
INSERT INTO `fallstudie`.`Art` (`Name`, `Aktiv`) VALUES ('Erstattungen', 1);
INSERT INTO `fallstudie`.`Art` (`Name`, `Aktiv`) VALUES ('Telefonate', 1);
INSERT INTO `fallstudie`.`Art` (`Name`, `Aktiv`) VALUES ('Reklamationen', 1);
INSERT INTO `fallstudie`.`Art` (`Name`, `Aktiv`) VALUES ('Notfälle', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fallstudie`.`Berechtigung`
-- -----------------------------------------------------
START TRANSACTION;
USE `fallstudie`;
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Arbeitsgruppe anlegen', 'Darf Arbeitsgruppe anlegen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Arbeitsgruppe bearbeiten', 'Darf Arbeitsgruppe bearbeiten');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Arbeitsgruppe löschen', 'Darf Arbeitsgruppe löschen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Bereich anlegen', 'Darf Bereich anlegen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Bereich bearbeiten', 'Darf Bereich bearbeiten');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Bereich löschen', 'Darf Bereich löschen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Daten erfassen', 'Darf Eintrag erfassen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Drucken', 'Darf Uebersicht drucken');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Eintragsart anlegen', 'Darf neue Eintragsart anlegen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Eintragsart löschen', 'Darf Eintragsart löschen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Jobintervall festlegen', 'Darf Jobintervall festlegen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Lesen alle Arbeitsgruppen eines Bereichs Jahr', 'Darf Jahresuebersicht jeder Arbeitsgruppe eines Bereiches sehen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Lesen alle Arbeitsgruppen eines Bereichs KW', 'Darf Wochenuebersicht jeder Arbeitsgruppe eines Bereiches sehen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Lesen alle Arbeitsgruppen Jahr', 'Darf alle Jahresuebersichten aller Arbeitsgruppen sehen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Lesen alle Arbeitsgruppen KW', 'Darf alle Wochenuebersichten aller Arbeitsgruppen sehen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Lesen alle Bereiche Jahr', 'Darf alle Jahresuebersichten aller Bereiche sehen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Lesen alle Bereiche KW', 'Darf alle Wochenuebersichten aller Bereiche sehen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Lesen eigene Arbeitsgruppe Jahr', 'Darf Jahresuebersicht der eigenen Arbeitsgruppe sehen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Lesen eigene Arbeitsgruppe KW', 'Darf Wochenuebersicht der eigenen Arbeitsgruppe sehen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Mitarbeiter anlegen', 'Darf Mitarbeiter anlegen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Mitarbeiter bearbeiten', 'Darf Mitarbeiter bearbeiten');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Mitarbeiter löschen', 'Darf Mitarbeiter löschen');
INSERT INTO `fallstudie`.`Berechtigung` (`Name`, `Beschreibung`) VALUES ('Passwort ändern', 'Darf Passwort ändern');

COMMIT;


-- -----------------------------------------------------
-- Data for table `fallstudie`.`Config`
-- -----------------------------------------------------
START TRANSACTION;
USE `fallstudie`;
INSERT INTO `fallstudie`.`Config` (`Jobintervall`) VALUES (3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fallstudie`.`Eintrag`
-- -----------------------------------------------------
START TRANSACTION;
USE `fallstudie`;
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (1, '2013-01-01', 1, 50, 1, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (2, '2013-01-02', 1, 20, 1, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (3, '2013-01-10', 1, 50, 2, 2013, 'Erstattungen');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (4, '2013-01-20', 1, 20, 4, 2013, 'Reklamationen');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (5, '2013-01-20', 1, 70, 4, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (6, '2013-02-01', 2, 12, 5, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (7, '2013-02-10', 2, 20, 7, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (8, '2013-02-10', 2, 102, 7, 2013, 'Erstattungen');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (9, '2013-02-20', 2, 20, 8, 2013, 'Reklamationen');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (10, '2013-02-28', 2, 70, 9, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (11, '2013-02-27', 2, 12, 9, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (12, '2013-04-10', 5, 210, 15, 2013, 'Erstattungen');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (13, '2013-05-10', 7, 111, 19, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (14, '2013-10-14', 2, 7, 41, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (15, '2013-10-14', 2, 8, 40, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (16, '2013-10-14', 2, 9, 39, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (17, '2013-10-14', 3, 3, 38, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (18, '2013-10-14', 3, 7, 37, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (19, '2013-10-14', 3, 4, 36, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (20, '2013-10-14', 3, 4, 35, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (21, '2013-10-14', 4, 4, 34, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (22, '2013-10-14', 4, 8, 33, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (23, '2013-10-14', 4, 4, 32, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (24, '2013-10-14', 2, 49, 31, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (25, '2013-10-14', 2, 2, 30, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (26, '2013-10-15', 2, 5, 29, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (27, '2013-10-15', 2, 2, 28, 1999, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (28, '2013-10-15', 2, 6, 27, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (29, '2013-10-15', 2, 3, 26, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (30, '2013-10-15', 2, 902, 25, 20134, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (31, '2013-10-15', 2, 0, 24, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (32, '2013-10-15', 7, 0, 23, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (33, '2013-10-15', 7, 0, 22, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (34, '2013-10-15', 7, 1, 11, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (35, '2013-10-15', 8, 3, 18, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (36, '2013-10-15', 8, 4, 17, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (37, '2013-10-15', 2, 213, 16, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (38, '2013-10-15', 13, 5, 15, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (39, '2013-10-15', 13, 45, 14, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (40, '2013-10-16', 12, 1, 13, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (41, '2013-10-16', 11, 4, 12, 2013, 'Reklamationen');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (42, '2013-10-16', 10, 6, 10, 2013, 'Reklamationen');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (43, '2013-01-01', 1, 50, 1, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (44, '2013-01-02', 1, 20, 1, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (45, '2013-01-10', 1, 50, 2, 2013, 'Erstattungen');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (47, '2013-01-20', 1, 20, 4, 2013, 'Reklamationen');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (48, '2013-01-20', 1, 70, 4, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (49, '2013-02-01', 2, 12, 5, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (50, '2013-02-10', 2, 20, 7, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (51, '2013-02-10', 2, 102, 7, 2013, 'Erstattungen');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (52, '2013-02-20', 2, 20, 8, 2013, 'Reklamationen');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (53, '2013-02-28', 2, 70, 9, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (54, '2013-02-27', 2, 12, 9, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (55, '2013-04-10', 5, 210, 15, 2013, 'Erstattungen');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (56, '2013-05-10', 7, 111, 19, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (57, '2013-10-14', 2, 7, 41, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (58, '2013-10-14', 2, 8, 40, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (59, '2013-10-14', 2, 9, 39, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (60, '2013-10-14', 3, 3, 38, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (61, '2013-10-14', 3, 7, 37, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (62, '2013-10-14', 3, 4, 36, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (63, '2013-10-14', 3, 4, 35, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (64, '2013-10-14', 4, 4, 34, 2013, 'Telefonate');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (65, '2013-10-14', 4, 8, 33, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (66, '2013-10-14', 4, 4, 32, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (67, '2013-10-14', 2, 49, 31, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (68, '2013-10-14', 2, 2, 30, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (69, '2013-10-15', 2, 5, 29, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (70, '2013-10-15', 2, 2, 28, 1999, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (71, '2013-10-15', 2, 6, 27, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (72, '2013-10-15', 2, 3, 26, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (73, '2013-10-15', 2, 902, 25, 20134, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (74, '2013-10-15', 2, 0, 24, 2013, 'Schriftwechsel');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (75, '2013-10-15', 7, 0, 23, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (76, '2013-10-15', 7, 0, 22, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (77, '2013-10-15', 7, 1, 11, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (78, '2013-10-15', 8, 3, 18, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (79, '2013-10-15', 8, 4, 17, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (80, '2013-10-15', 2, 213, 16, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (81, '2013-10-15', 13, 5, 15, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (82, '2013-10-15', 13, 45, 14, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (83, '2013-10-16', 12, 1, 13, 2013, 'Notfälle');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (84, '2013-10-16', 11, 4, 12, 2013, 'Reklamationen');
INSERT INTO `fallstudie`.`Eintrag` (`EintragID`, `Datum`, `Arbeitsgruppe`, `Summe`, `Kalenderwoche`, `Kalenderjahr`, `Art`) VALUES (85, '2013-10-16', 10, 6, 10, 2013, 'Reklamationen');

COMMIT;


-- -----------------------------------------------------
-- Data for table `fallstudie`.`Rollenberechtigungen`
-- -----------------------------------------------------
START TRANSACTION;
USE `fallstudie`;
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Arbeitsgruppe anlegen');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Arbeitsgruppe bearbeiten');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Arbeitsgruppe löschen');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Bereich anlegen');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Bereich bearbeiten');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Bereich löschen');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Gruppenleiter', 'Daten erfassen');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Sachbearbeiter', 'Daten erfassen');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Bereichsleiter', 'Drucken');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Drucken');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Gruppenleiter', 'Drucken');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Zentralbereichsleiter', 'Drucken');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Eintragsart anlegen');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Eintragsart löschen');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Jobintervall festlegen');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Bereichsleiter', 'Lesen alle Arbeitsgruppen eines Bereichs Jahr');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Bereichsleiter', 'Lesen alle Arbeitsgruppen eines Bereichs KW');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Lesen alle Arbeitsgruppen Jahr');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Zentralbereichsleiter', 'Lesen alle Arbeitsgruppen Jahr');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Lesen alle Arbeitsgruppen KW');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Zentralbereichsleiter', 'Lesen alle Arbeitsgruppen KW');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Lesen alle Bereiche Jahr');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Zentralbereichsleiter', 'Lesen alle Bereiche Jahr');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Lesen alle Bereiche KW');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Zentralbereichsleiter', 'Lesen alle Bereiche KW');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Gruppenleiter', 'Lesen eigene Arbeitsgruppe Jahr');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Gruppenleiter', 'Lesen eigene Arbeitsgruppe KW');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Mitarbeiter anlegen');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Mitarbeiter bearbeiten');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Mitarbeiter löschen');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Bereichsleiter', 'Passwort ändern');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Fachbereichsorganisation', 'Passwort ändern');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Gruppenleiter', 'Passwort ändern');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Sachbearbeiter', 'Passwort ändern');
INSERT INTO `fallstudie`.`Rollenberechtigungen` (`Rollenbezeichnung`, `Berechtigungsname`) VALUES ('Zentralbereichsleiter', 'Passwort ändern');

COMMIT;
