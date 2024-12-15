-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema football-league
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema football-league
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `football-league` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `football-league` ;

-- -----------------------------------------------------
-- Table `football-league`.`Coach`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Coach` (
  `JMBG` CHAR(13) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `Age` INT NOT NULL,
  `NumberOfGames` INT NULL,
  PRIMARY KEY (`JMBG`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`Player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Player` (
  `JMBG` CHAR(13) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `Age` INT NOT NULL,
  `NumberOfGames` INT NULL,
  `TotalMinutes` INT NULL,
  `NumberOfGoals` INT NULL,
  `NumberOfAssists` INT NULL,
  `NumberOfSaves` INT NULL,
  `NumberOfConcededGoals` INT NULL,
  PRIMARY KEY (`JMBG`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`Referee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Referee` (
  `JMBG` CHAR(13) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`JMBG`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`Club`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Club` (
  `ClubName` VARCHAR(45) NOT NULL,
  `ClubAdress` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ClubName`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`Season`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Season` (
  `Year` INT NOT NULL,
  PRIMARY KEY (`Year`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`Round`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Round` (
  `RoundNumber` INT NOT NULL,
  `Season_Year` INT NOT NULL,
  PRIMARY KEY (`RoundNumber`, `Season_Year`),
  INDEX `fk_Round_Season1_idx` (`Season_Year` ASC) VISIBLE,
  CONSTRAINT `fk_Round_Season1`
    FOREIGN KEY (`Season_Year`)
    REFERENCES `football-league`.`Season` (`Year`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`Match`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Match` (
  `MatchID` INT NOT NULL,
  `DateTime` DATETIME NOT NULL,
  `Round_RoundNumber` INT NOT NULL,
  `Referee_JMBG` CHAR(13) NOT NULL,
  `Season_Year` INT NOT NULL,
  PRIMARY KEY (`MatchID`),
  INDEX `fk_Match_Round1_idx` (`Round_RoundNumber` ASC) VISIBLE,
  INDEX `fk_Match_Season1_idx` (`Season_Year` ASC) VISIBLE,
  CONSTRAINT `fk_Match_Round1`
    FOREIGN KEY (`Round_RoundNumber`)
    REFERENCES `football-league`.`Round` (`RoundNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Match_Season1`
    FOREIGN KEY (`Season_Year`)
    REFERENCES `football-league`.`Season` (`Year`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`Stadium`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Stadium` (
  `StadiumAdress` VARCHAR(45) NOT NULL,
  `Capacity` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`StadiumAdress`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`Contract`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Contract` (
  `ContractID` INT NOT NULL,
  `VALUE` INT NOT NULL,
  `SignedDate` DATE NOT NULL,
  `DateOfExpiration` DATE NOT NULL,
  `Club_ClubName` VARCHAR(45) NOT NULL,
  `Season_Year` INT NOT NULL,
  `Player_JMBG` CHAR(13) NOT NULL,
  PRIMARY KEY (`ContractID`),
  INDEX `fk_Contract_Club1_idx` (`Club_ClubName` ASC) VISIBLE,
  INDEX `fk_Contract_Season1_idx` (`Season_Year` ASC) VISIBLE,
  INDEX `fk_Contract_Player1_idx` (`Player_JMBG` ASC) VISIBLE,
  CONSTRAINT `fk_Contract_Club1`
    FOREIGN KEY (`Club_ClubName`)
    REFERENCES `football-league`.`Club` (`ClubName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contract_Season1`
    FOREIGN KEY (`Season_Year`)
    REFERENCES `football-league`.`Season` (`Year`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contract_Player1`
    FOREIGN KEY (`Player_JMBG`)
    REFERENCES `football-league`.`Player` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`President`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`President` (
  `JMBG` CHAR(13) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`JMBG`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`MatchStats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`MatchStats` (
  `NumberOfGoalsHome` INT NULL,
  `NumberOfGoalsGuests` INT NULL,
  `NumberOfFans` INT NULL,
  `NumberOfYellowCards` INT NULL,
  `NumberOfRedCards` INT NULL,
  `NumberOfCorners` INT NULL,
  `Match_MatchID` INT NOT NULL,
  PRIMARY KEY (`Match_MatchID`),
  CONSTRAINT `fk_MatchStats_Match1`
    FOREIGN KEY (`Match_MatchID`)
    REFERENCES `football-league`.`Match` (`MatchID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`PayerStatsInOneMatch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`PayerStatsInOneMatch` (
  `NumberOfAssists` INT NULL,
  `NumberOfGoals` INT NULL,
  `YellowCard` TINYINT NULL,
  `RedCard` TINYINT NULL,
  `NumberOfMinutes` INT NULL,
  `Match_MatchID` INT NOT NULL,
  `Player_JMBG` CHAR(13) NOT NULL,
  `StartingEleven` TINYINT NOT NULL,
  PRIMARY KEY (`Match_MatchID`, `Player_JMBG`),
  INDEX `fk_PayerStatsInOneMatch_Match1_idx` (`Match_MatchID` ASC) VISIBLE,
  INDEX `fk_PayerStatsInOneMatch_Player1_idx` (`Player_JMBG` ASC) VISIBLE,
  CONSTRAINT `fk_PayerStatsInOneMatch_Match1`
    FOREIGN KEY (`Match_MatchID`)
    REFERENCES `football-league`.`Match` (`MatchID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PayerStatsInOneMatch_Player1`
    FOREIGN KEY (`Player_JMBG`)
    REFERENCES `football-league`.`Player` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`Club_has_Match`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Club_has_Match` (
  `Club_ClubName` VARCHAR(45) NOT NULL,
  `Match_MatchID` INT NOT NULL,
  `Role` ENUM('Home', 'Away') NULL,
  PRIMARY KEY (`Club_ClubName`, `Match_MatchID`),
  INDEX `fk_Club_has_Match_Match1_idx` (`Match_MatchID` ASC) VISIBLE,
  INDEX `fk_Club_has_Match_Club1_idx` (`Club_ClubName` ASC) VISIBLE,
  CONSTRAINT `fk_Club_has_Match_Club1`
    FOREIGN KEY (`Club_ClubName`)
    REFERENCES `football-league`.`Club` (`ClubName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Club_has_Match_Match1`
    FOREIGN KEY (`Match_MatchID`)
    REFERENCES `football-league`.`Match` (`MatchID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`Season_has_Club`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Season_has_Club` (
  `Season_Year` INT NOT NULL,
  `Club_ClubName` VARCHAR(45) NOT NULL,
  `NumberOfDefeats` INT NULL,
  `NumberOfVictories` INT NULL,
  `NumberOfScoredGoals` INT NULL,
  `NumberOfConcededGoals` INT NULL,
  `Points` INT NULL,
  PRIMARY KEY (`Season_Year`, `Club_ClubName`),
  INDEX `fk_Season_has_Club_Club1_idx` (`Club_ClubName` ASC) VISIBLE,
  INDEX `fk_Season_has_Club_Season1_idx` (`Season_Year` ASC) VISIBLE,
  CONSTRAINT `fk_Season_has_Club_Season1`
    FOREIGN KEY (`Season_Year`)
    REFERENCES `football-league`.`Season` (`Year`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Season_has_Club_Club1`
    FOREIGN KEY (`Club_ClubName`)
    REFERENCES `football-league`.`Club` (`ClubName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`PlayerStatsInSeason`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`PlayerStatsInSeason` (
  `NumberOfAssists` INT NULL,
  `NumberOfGoals` INT NULL,
  `NumberOfYellowCards` INT NULL,
  `NumberOfRedCards` INT NULL,
  `NumberOfMinutes` INT NULL,
  `Season_Year` INT NOT NULL,
  `Player_JMBG` CHAR(13) NOT NULL,
  `NumberOfTimesInStartingEleven` TINYINT NOT NULL,
  INDEX `fk_PlayerStatsInSeason_Season1_idx` (`Season_Year` ASC) VISIBLE,
  INDEX `fk_PlayerStatsInSeason_Player1_idx` (`Player_JMBG` ASC) VISIBLE,
  PRIMARY KEY (`Season_Year`, `Player_JMBG`),
  CONSTRAINT `fk_PlayerStatsInSeason_Season1`
    FOREIGN KEY (`Season_Year`)
    REFERENCES `football-league`.`Season` (`Year`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PlayerStatsInSeason_Player1`
    FOREIGN KEY (`Player_JMBG`)
    REFERENCES `football-league`.`Player` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`Club_has_Coach`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Club_has_Coach` (
  `Club_ClubName` VARCHAR(45) NOT NULL,
  `Coach_JMBG` CHAR(13) NOT NULL,
  `SignedDate` DATE NOT NULL,
  `DateOfExpiration` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Club_ClubName`, `Coach_JMBG`),
  INDEX `fk_Club_has_Coach_Coach1_idx` (`Coach_JMBG` ASC) VISIBLE,
  INDEX `fk_Club_has_Coach_Club1_idx` (`Club_ClubName` ASC) VISIBLE,
  CONSTRAINT `fk_Club_has_Coach_Club1`
    FOREIGN KEY (`Club_ClubName`)
    REFERENCES `football-league`.`Club` (`ClubName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Club_has_Coach_Coach1`
    FOREIGN KEY (`Coach_JMBG`)
    REFERENCES `football-league`.`Coach` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`Club_has_President`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Club_has_President` (
  `Club_ClubName` VARCHAR(45) NOT NULL,
  `President_JMBG` CHAR(13) NOT NULL,
  `SignedDate` DATE NOT NULL,
  `DateOfExpiration` DATE NOT NULL,
  PRIMARY KEY (`Club_ClubName`, `President_JMBG`),
  INDEX `fk_Club_has_President_President1_idx` (`President_JMBG` ASC) VISIBLE,
  INDEX `fk_Club_has_President_Club1_idx` (`Club_ClubName` ASC) VISIBLE,
  CONSTRAINT `fk_Club_has_President_Club1`
    FOREIGN KEY (`Club_ClubName`)
    REFERENCES `football-league`.`Club` (`ClubName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Club_has_President_President1`
    FOREIGN KEY (`President_JMBG`)
    REFERENCES `football-league`.`President` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`Match_has_Referee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Match_has_Referee` (
  `Match_MatchID` INT NOT NULL,
  `Referee_JMBG` CHAR(13) NOT NULL,
  `Role` ENUM('Main', 'Assistant') NOT NULL,
  PRIMARY KEY (`Match_MatchID`, `Referee_JMBG`),
  INDEX `fk_Match_has_Referee_Referee1_idx` (`Referee_JMBG` ASC) VISIBLE,
  INDEX `fk_Match_has_Referee_Match1_idx` (`Match_MatchID` ASC) VISIBLE,
  CONSTRAINT `fk_Match_has_Referee_Match1`
    FOREIGN KEY (`Match_MatchID`)
    REFERENCES `football-league`.`Match` (`MatchID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Match_has_Referee_Referee1`
    FOREIGN KEY (`Referee_JMBG`)
    REFERENCES `football-league`.`Referee` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football-league`.`Stadium_has_Club`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football-league`.`Stadium_has_Club` (
  `Stadium_StadiumAdress` VARCHAR(45) NOT NULL,
  `Club_ClubName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Stadium_StadiumAdress`, `Club_ClubName`),
  INDEX `fk_Stadium_has_Club_Club1_idx` (`Club_ClubName` ASC) VISIBLE,
  INDEX `fk_Stadium_has_Club_Stadium1_idx` (`Stadium_StadiumAdress` ASC) VISIBLE,
  CONSTRAINT `fk_Stadium_has_Club_Stadium1`
    FOREIGN KEY (`Stadium_StadiumAdress`)
    REFERENCES `football-league`.`Stadium` (`StadiumAdress`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Stadium_has_Club_Club1`
    FOREIGN KEY (`Club_ClubName`)
    REFERENCES `football-league`.`Club` (`ClubName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
