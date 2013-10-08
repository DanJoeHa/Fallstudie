SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `Fallstudie` ;
CREATE SCHEMA IF NOT EXISTS `Fallstudie` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Fallstudie` ;

-- -----------------------------------------------------
-- Table `Fallstudie`.`Bereich`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Fallstudie`.`Bereich` (
  `Kurzbezeichnung` VARCHAR(45) NOT NULL,
  `Bezeichnung` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Kurzbezeichnung`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Fallstudie`.`Arbeitsgruppe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Fallstudie`.`Arbeitsgruppe` (
  `Kurzbezeichnung` VARCHAR(45) NOT NULL,
  `Bezeichnung` VARCHAR(45) NOT NULL,
  `Bereich` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Kurzbezeichnung`),
  INDEX `Arbeitsgruppe_Bereich_FK_idx` (`Bereich` ASC),
  CONSTRAINT `Arbeitsgruppe_Bereich_FK`
    FOREIGN KEY (`Bereich`)
    REFERENCES `Fallstudie`.`Bereich` (`Kurzbezeichnung`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


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
  `Arbeitsgruppe` VARCHAR(45) NOT NULL,
  `Rolle` VARCHAR(45) NOT NULL,
  `Bereich` VARCHAR(45) NULL,
  `Passwort` VARCHAR(45) NOT NULL,
  `Aktiv` TINYINT(1) NULL DEFAULT 1,
  `Vorname` VARCHAR(45) NULL,
  `Nachname` VARCHAR(45) NULL,
  `LetzterLogin` DATE NULL,
  PRIMARY KEY (`Benutzername`),
  INDEX `Arbeitsgruppe_idx` (`Arbeitsgruppe` ASC),
  INDEX `Rolle_idx` (`Rolle` ASC),
  INDEX `Bereich_idx` (`Bereich` ASC),
  CONSTRAINT `Mitarbeiter_Arbeitsgruppe_FK`
    FOREIGN KEY (`Arbeitsgruppe`)
    REFERENCES `Fallstudie`.`Arbeitsgruppe` (`Kurzbezeichnung`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Mitarbeiter_Rolle_FK`
    FOREIGN KEY (`Rolle`)
    REFERENCES `Fallstudie`.`Rolle` (`Rollenbezeichnung`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Mitarbeiter_Bereich_FK`
    FOREIGN KEY (`Bereich`)
    REFERENCES `Fallstudie`.`Bereich` (`Kurzbezeichnung`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Fallstudie`.`Eintrag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Fallstudie`.`Eintrag` (
  `Benutzername` VARCHAR(20) NOT NULL,
  `Kalenderwoche` INT NOT NULL,
  `Kalenderjahr` INT NOT NULL,
  `Arbeitsgruppe` VARCHAR(45) NOT NULL,
  `Schriftwechsel` INT NULL,
  `Erstattungen` INT NULL,
  `Monat` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Benutzername`, `Kalenderwoche`, `Kalenderjahr`),
  INDEX `Arbeitsgruppe_FK_idx` (`Arbeitsgruppe` ASC),
  CONSTRAINT `Mitarbeiter_Eintrag_FK`
    FOREIGN KEY (`Benutzername`)
    REFERENCES `Fallstudie`.`Mitarbeiter` (`Benutzername`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Eintrag_Mitarbeiter_FK`
    FOREIGN KEY (`Arbeitsgruppe`)
    REFERENCES `Fallstudie`.`Arbeitsgruppe` (`Kurzbezeichnung`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '!!\nSELECT WEEKOFYEAR(KALENDERWOCHE) gibt Kalenderwoche zurück.\n\nSELECT YEAR(Kalenderwoche) FROM EINTRAG gibt Jahr zurück.\n\nBeim Löschen von Mitarbeiter wird die Rolle entzogen.';


-- -----------------------------------------------------
-- Table `Fallstudie`.`Wochenuebersicht`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Fallstudie`.`Wochenuebersicht` (
  `Kalenderjahr` INT NOT NULL,
  `Kalenderwoche` INT NOT NULL,
  `Schriftwechselsumme` INT NULL,
  `Erstattungensumme` INT NULL,
  `Arbeitsgruppe_FK` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Kalenderjahr`, `Kalenderwoche`),
  INDEX `Arbeitsgruppe_idx` (`Arbeitsgruppe_FK` ASC),
  CONSTRAINT `Wochenuebersicht_Arbeitsgruppe_FK`
    FOREIGN KEY (`Arbeitsgruppe_FK`)
    REFERENCES `Fallstudie`.`Arbeitsgruppe` (`Kurzbezeichnung`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Wird im Programm generiert.';


-- -----------------------------------------------------
-- Table `Fallstudie`.`Jahresuebersicht`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Fallstudie`.`Jahresuebersicht` (
  `Kalenderjahr` INT NOT NULL,
  `Schriftwechselsumme` INT NULL,
  `Erstattungensumme` INT NULL,
  PRIMARY KEY (`Kalenderjahr`))
ENGINE = InnoDB;


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
  `Berechtigungsname` VARCHAR(45) NOT NULL,
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
