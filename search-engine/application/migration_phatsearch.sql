-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: PhatSearch
-- Source Schemata: PhatSearch
-- Created: Sat Dec  8 14:52:30 2018
-- Workbench Version: 8.0.13
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema PhatSearch
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `PhatSearch` ;
CREATE SCHEMA IF NOT EXISTS `PhatSearch` ;

-- ----------------------------------------------------------------------------
-- Table PhatSearch.Frequencies
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `PhatSearch`.`Frequencies` (
  `idFrequency` INT(11) NOT NULL AUTO_INCREMENT,
  `idWebPage` INT(11) NOT NULL,
  `idWord` INT(11) NOT NULL,
  `frequency` INT(11) NOT NULL,
  PRIMARY KEY (`idFrequency`),
  INDEX `FK_Frequencies_WebPages_idx` (`idWebPage` ASC),
  INDEX `FK_Frequencies_Words_idx` (`idWord` ASC),
  CONSTRAINT `FK_Frequencies_WebPages`
    FOREIGN KEY (`idWebPage`)
    REFERENCES `PhatSearch`.`WebPages` (`idWebPage`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Frequencies_Words`
    FOREIGN KEY (`idWord`)
    REFERENCES `PhatSearch`.`Words` (`idWord`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table PhatSearch.WebPages
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `PhatSearch`.`WebPages` (
  `idWebPage` INT(11) NOT NULL AUTO_INCREMENT,
  `webPageLink` VARCHAR(2083) NOT NULL,
  PRIMARY KEY (`idWebPage`),
  UNIQUE INDEX `webPageLink_UNIQUE` (`webPageLink`(255) ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table PhatSearch.Words
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `PhatSearch`.`Words` (
  `idWord` INT(11) NOT NULL AUTO_INCREMENT,
  `word` VARCHAR(2083) NOT NULL,
  PRIMARY KEY (`idWord`),
  UNIQUE INDEX `word_UNIQUE` (`word`(255) ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Routine PhatSearch.insertFrequecy
-- ----------------------------------------------------------------------------
DELIMITER $$

DELIMITER $$
USE `PhatSearch`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertFrequency`(
IN idUrl INT, 
IN idWord INT, 
IN freq INT
)
BEGIN
		  INSERT INTO Frequencies(idWebPage, idWord, frequency) VALUES(idUrl, idWord, freq);
END$$

DELIMITER ;

-- ----------------------------------------------------------------------------
-- Routine PhatSearch.insertURLAndReturnID
-- ----------------------------------------------------------------------------
DELIMITER $$

DELIMITER $$
USE `PhatSearch`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertURLAndReturnID`(IN inUrl varchar(2083), OUT idResultWord INT)
BEGIN
      INSERT INTO WebPages(webPageLink) VALUES(inUrl);	
      SELECT idWebPage INTO idResultWord FROM WebPages WHERE webPageLink = inUrl;
END$$

DELIMITER ;

-- ----------------------------------------------------------------------------
-- Routine PhatSearch.insertWordAndReturnID
-- ----------------------------------------------------------------------------
DELIMITER $$

DELIMITER $$
USE `PhatSearch`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertWordAndReturnID`(IN inWord varchar(2083), OUT idResultWord INT)
BEGIN
      INSERT INTO Words(word) VALUES(inWord);	
      SELECT idWord INTO idResultWord FROM Words WHERE word = inWord;
END$$

DELIMITER ;
SET FOREIGN_KEY_CHECKS = 1;
