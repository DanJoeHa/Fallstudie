
USE `fallstudie` ;

-- -----------------------------------------------------
-- Table `Fallstudie`.`Rolle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Rolle` (
  `Rollenbezeichnung` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Rollenbezeichnung`));

-- -----------------------------------------------------
-- Table `Fallstudie`.`Berechtigung`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Berechtigung` (
  `Name` VARCHAR(45) NOT NULL,
  `Beschreibung` TEXT NULL,
  PRIMARY KEY (`Name`));

-- -----------------------------------------------------
-- Table `Fallstudie`.`Rollenberechtigungen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Rollenberechtigungen` (
  `Rollenbezeichnung` VARCHAR(45) NOT NULL,
  `Berechtigungsname` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`Rollenbezeichnung`, `Berechtigungsname`),
  INDEX `Berechtigungsname_idx` (`Berechtigungsname` ASC),
  CONSTRAINT `Rollenbezeichnung_FK`
    FOREIGN KEY (`Rollenbezeichnung`)
    REFERENCES `Rolle` (`Rollenbezeichnung`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Berechtigungsname_FK`
    FOREIGN KEY (`Berechtigungsname`)
    REFERENCES `Berechtigung` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `Fallstudie`.`Bereich`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bereich` (
  `BereichID` INT NOT NULL AUTO_INCREMENT,
  `Beschreibung` VARCHAR(45) NOT NULL,
  `Kurzbezeichnung` VARCHAR(45) NOT NULL,
  `Aktiv` TINYINT(1) NOT NULL DEFAULT 1,
  `Leiter` VARCHAR(45) NULL,
  PRIMARY KEY (`BereichID`),
  INDEX `Bereichsleiter_FK_idx` (`Leiter` ASC);
  
-- -----------------------------------------------------
-- Table `Fallstudie`.`Arbeitsgruppe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Arbeitsgruppe` (
  `ArbeitsgruppeID` INT NOT NULL AUTO_INCREMENT,
  `Leiter` VARCHAR(45) NULL,
  `Bereich` INT NOT NULL,
  `Beschreibung` VARCHAR(45) NOT NULL,
  `Kurzbezeichnung` VARCHAR(45) NOT NULL,
  `Aktiv` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`ArbeitsgruppeID`),
  INDEX `Arbeitsgruppe_Bereich_FK_idx` (`Bereich` ASC),
  INDEX `Arbeitsgruppe_Leiter_FK_idx` (`Leiter` ASC),
  CONSTRAINT `Arbeitsgruppe_Bereich_FK`
    FOREIGN KEY (`Bereich`)
    REFERENCES `Bereich` (`BereichID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  
-- -----------------------------------------------------
-- Table `Fallstudie`.`Mitarbeiter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Mitarbeiter` (
  `Benutzername` VARCHAR(45) NOT NULL,
  `Arbeitsgruppe` INT NULL,
  `Rolle` VARCHAR(45) NOT NULL,
  `Passwort` VARCHAR(45) NOT NULL,
  `Aktiv` TINYINT(1) NULL DEFAULT 1,
  `Vorname` VARCHAR(45) NULL,
  `Nachname` VARCHAR(45) NULL,
  `LetzterLogin` DATE NULL,
  `Bereich` INT NULL,
  PRIMARY KEY (`Benutzername`),
  INDEX `Arbeitsgruppe_idx` (`Arbeitsgruppe` ASC),
  INDEX `Rolle_idx` (`Rolle` ASC),
  INDEX `Mitarbeiter_Bereich_FK_idx` (`Bereich` ASC),
  CONSTRAINT `Mitarbeiter_Arbeitsgruppe_FK`
    FOREIGN KEY (`Arbeitsgruppe`)
    REFERENCES `Arbeitsgruppe` (`ArbeitsgruppeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Mitarbeiter_Rolle_FK`
    FOREIGN KEY (`Rolle`)
    REFERENCES `Rolle` (`Rollenbezeichnung`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Mitarbeiter_Bereich_FK`
    FOREIGN KEY (`Bereich`)
    REFERENCES `Bereich` (`BereichID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `Fallstudie`.`Eintrag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Eintrag` (
  `EintragID` INT NOT NULL AUTO_INCREMENT,
  `Datum` DATE NOT NULL,
  `Arbeitsgruppe` INT NOT NULL,
  `Schriftwechsel` INT NULL,
  `Erstattungen` INT NULL,
  PRIMARY KEY (`EintragID`),
  INDEX `Arbeitsgruppe_FK_idx` (`Arbeitsgruppe` ASC),
  CONSTRAINT `Eintrag_Mitarbeiter_FK`
    FOREIGN KEY (`Arbeitsgruppe`)
    REFERENCES `Arbeitsgruppe` (`ArbeitsgruppeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `Fallstudie`.`Wochenuebersicht`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Wochenuebersicht` (
  `Kalenderjahr` INT NOT NULL,
  `Kalenderwoche` INT NOT NULL,
  `Schriftwechselsumme` INT NULL,
  `Erstattungensumme` INT NULL,
  `Arbeitsgruppe` INT NOT NULL,
  `Bereich` INT NOT NULL,
  PRIMARY KEY (`Kalenderjahr`, `Kalenderwoche`),
  INDEX `Arbeitsgruppe_Einträge_FK_idx` (`Arbeitsgruppe` ASC),
  INDEX `Bereich_hat_Einträge_FK_idx` (`Bereich` ASC),
  CONSTRAINT `Arbeitsgruppe_hat_Einträge_FK`
    FOREIGN KEY (`Arbeitsgruppe`)
    REFERENCES `Arbeitsgruppe` (`ArbeitsgruppeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Bereich_hat_Einträge_FK`
    FOREIGN KEY (`Bereich`)
    REFERENCES `Bereich` (`BereichID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `Fallstudie`.`Jahresuebersicht`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Jahresuebersicht` (
  `Kalenderjahr` INT NOT NULL,
  `Schriftwechselsumme` INT NULL,
  `Erstattungensumme` INT NULL,
  `Arbeitsgruppe` INT NOT NULL,
  `Bereich` INT NOT NULL,
  PRIMARY KEY (`Kalenderjahr`),
  INDEX `Jahresuebersicht_hat_Bereich_idx` (`Bereich` ASC),
  INDEX `Jahresuebersicht_hat_Gruppe_idx` (`Arbeitsgruppe` ASC),
  CONSTRAINT `Jahresuebersicht_hat_Bereich`
    FOREIGN KEY (`Bereich`)
    REFERENCES `Bereich` (`BereichID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Jahresuebersicht_hat_Gruppe`
    FOREIGN KEY (`Arbeitsgruppe`)
    REFERENCES `Arbeitsgruppe` (`ArbeitsgruppeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);




Kommentar:

ALTER TABLE Bereich ADD CONSTRAINT `Bereichsleiter_FK`
    FOREIGN KEY (`Leiter`)
    REFERENCES `Mitarbeiter` (`Benutzername`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE Arbeitsgruppe ADD CONSTRAINT `Arbeitsgruppe_Leiter_FK`
    FOREIGN KEY (`Leiter`)
    REFERENCES `Mitarbeiter` (`Benutzername`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;












