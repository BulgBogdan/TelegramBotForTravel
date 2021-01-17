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
  `city_name` VARCHAR(45)  NOT NULL,
  `city_info` VARCHAR(200) NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

insert into `Bot-Travel`.`cities` (city_name, city_info)
values ('Москва', 'Не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить)))');
insert into `Bot-Travel`.`cities` (city_name, city_info)
values ('Минск',
        'Было бы здорово посетить Национальную Библиотеку Беларуси. Но нужно знать о дресс-коде, лучше не одевать красно-белые тона одежды)))');
