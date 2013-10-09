SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `Fallstudie` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Fallstudie` ;

-- -----------------------------------------------------
-- Table `Fallstudie`.`Rolle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Fallstudie`.`Rolle` (
  `Rollenbezeichnung` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Rollenbezeichnung`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Fallstudie`.`Mitarbeiter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Fallstudie`.`Mitarbeiter` (
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
    REFERENCES `Fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Mitarbeiter_Rolle_FK`
    FOREIGN KEY (`Rolle`)
    REFERENCES `Fallstudie`.`Rolle` (`Rollenbezeichnung`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Mitarbeiter_Bereich_FK`
    FOREIGN KEY (`Bereich`)
    REFERENCES `Fallstudie`.`Bereich` (`BereichID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Fallstudie`.`Bereich`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Fallstudie`.`Bereich` (
  `BereichID` INT NOT NULL AUTO_INCREMENT,
  `Beschreibung` VARCHAR(45) NOT NULL,
  `Kurzbezeichnung` VARCHAR(45) NOT NULL,
  `Aktiv` TINYINT(1) NOT NULL DEFAULT 1,
  `Leiter` VARCHAR(45) NULL,
  PRIMARY KEY (`BereichID`),
  INDEX `Bereichsleiter_FK_idx` (`Leiter` ASC),
  CONSTRAINT `Bereichsleiter_FK`
    FOREIGN KEY (`Leiter`)
    REFERENCES `Fallstudie`.`Mitarbeiter` (`Benutzername`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Fallstudie`.`Arbeitsgruppe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Fallstudie`.`Arbeitsgruppe` (
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
    REFERENCES `Fallstudie`.`Bereich` (`BereichID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Arbeitsgruppe_Leiter_FK`
    FOREIGN KEY (`Leiter`)
    REFERENCES `Fallstudie`.`Mitarbeiter` (`Benutzername`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'check ob Mitarbeiter Leiter ist, wenn ja dann False und neue /* comment truncated */ /*n Mitarbeiter als leiter zuordnen.*/';


-- -----------------------------------------------------
-- Table `Fallstudie`.`Eintrag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Fallstudie`.`Eintrag` (
  `EintragID` INT NOT NULL AUTO_INCREMENT,
  `Datum` DATE NOT NULL,
  `Arbeitsgruppe` INT NOT NULL,
  `Schriftwechsel` INT NULL,
  `Erstattungen` INT NULL,
  PRIMARY KEY (`EintragID`),
  INDEX `Arbeitsgruppe_FK_idx` (`Arbeitsgruppe` ASC),
  CONSTRAINT `Eintrag_Mitarbeiter_FK`
    FOREIGN KEY (`Arbeitsgruppe`)
    REFERENCES `Fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '!!\nSELECT WEEKOFYEAR(KALENDERWOCHE) gibt Kalenderwoche zurüc /* comment truncated */ /*k.

SELECT YEAR(Kalenderwoche) FROM EINTRAG gibt Jahr zurück.

Beim Löschen von Mitarbeiter wird die Rolle entzogen.*/';


-- -----------------------------------------------------
-- Table `Fallstudie`.`Wochenuebersicht`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Fallstudie`.`Wochenuebersicht` (
  `Kalenderjahr` INT NOT NULL,
  `Kalenderwoche` INT NOT NULL,
  `Arbeitsgruppe` INT NULL,
  `Bereich` INT NULL,
  `Schriftwechselsumme` INT NOT NULL,
  `Erstattungensumme` INT NOT NULL,
  PRIMARY KEY (`Kalenderjahr`, `Kalenderwoche`, `Arbeitsgruppe`, `Bereich`),
  INDEX `Arbeitsgruppe_Einträge_FK_idx` (`Arbeitsgruppe` ASC),
  INDEX `Bereich_hat_Einträge_FK_idx` (`Bereich` ASC),
  CONSTRAINT `Arbeitsgruppe_hat_Einträge_FK`
    FOREIGN KEY (`Arbeitsgruppe`)
    REFERENCES `Fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Bereich_hat_Einträge_FK`
    FOREIGN KEY (`Bereich`)
    REFERENCES `Fallstudie`.`Bereich` (`BereichID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Wird im Programm generiert.';


-- -----------------------------------------------------
-- Table `Fallstudie`.`Jahresuebersicht`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Fallstudie`.`Jahresuebersicht` (
  `Kalenderjahr` INT NOT NULL,
  `Arbeitsgruppe` INT NULL,
  `Bereich` INT NULL,
  `Schriftwechselsumme` INT NOT NULL,
  `Erstattungensumme` INT NOT NULL,
  PRIMARY KEY (`Kalenderjahr`, `Arbeitsgruppe`, `Bereich`),
  INDEX `Jahresuebersicht_hat_Bereich_idx` (`Bereich` ASC),
  INDEX `Jahresuebersicht_hat_Gruppe_idx` (`Arbeitsgruppe` ASC),
  CONSTRAINT `Jahresuebersicht_hat_Bereich`
    FOREIGN KEY (`Bereich`)
    REFERENCES `Fallstudie`.`Bereich` (`BereichID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Jahresuebersicht_hat_Gruppe`
    FOREIGN KEY (`Arbeitsgruppe`)
    REFERENCES `Fallstudie`.`Arbeitsgruppe` (`ArbeitsgruppeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'generiert aus Wochenübersichten';


-- -----------------------------------------------------
-- Table `Fallstudie`.`Berechtigung`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Fallstudie`.`Berechtigung` (
  `Name` VARCHAR(45) NOT NULL,
  `Beschreibung` TEXT NULL,
  PRIMARY KEY (`Name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Fallstudie`.`Rollenberechtigungen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Fallstudie`.`Rollenberechtigungen` (
  `Rollenbezeichnung` VARCHAR(45) NOT NULL,
  `Berechtigungsname` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`Rollenbezeichnung`, `Berechtigungsname`),
  INDEX `Berechtigungsname_idx` (`Berechtigungsname` ASC),
  CONSTRAINT `Rollenbezeichnung_FK`
    FOREIGN KEY (`Rollenbezeichnung`)
    REFERENCES `Fallstudie`.`Rolle` (`Rollenbezeichnung`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Berechtigungsname_FK`
    FOREIGN KEY (`Berechtigungsname`)
    REFERENCES `Fallstudie`.`Berechtigung` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
