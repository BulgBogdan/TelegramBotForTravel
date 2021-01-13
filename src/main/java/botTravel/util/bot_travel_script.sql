-- -----------------------------------------------------
-- Schema Bot-Travel
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Bot-Travel`;

-- -----------------------------------------------------
-- Schema Bot-Travel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Bot-Travel` DEFAULT CHARACTER SET utf8;
USE `Bot-Travel`;

-- -----------------------------------------------------
-- Table `Bot-Travel`.`cities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Bot-Travel`.`cities`;

CREATE TABLE IF NOT EXISTS `Bot-Travel`.`cities`
(
  `id`        INT          NOT NULL AUTO_INCREMENT,
  `city`      VARCHAR(45)  NOT NULL,
  `city_info` VARCHAR(200) NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;