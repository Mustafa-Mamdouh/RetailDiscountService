CREATE TABLE `RetailDiscount`.`user_types` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `RetailDiscount`.`user_types` (`type_name`) VALUES ('REGULAR');
INSERT INTO `RetailDiscount`.`user_types` (`type_name`) VALUES ('EMPLOYEE');
INSERT INTO `RetailDiscount`.`user_types` (`type_name`) VALUES ('AFFILIATE');


CREATE TABLE `RetailDiscount`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `f_name` VARCHAR(45) NOT NULL,
  `l.name` VARCHAR(45) NOT NULL,
  `registeration_date` DATETIME NOT NULL,
  `userType` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_type_fk_idx` (`userType` ASC) VISIBLE,
  CONSTRAINT `user_type_fk`
    FOREIGN KEY (`userType`)
    REFERENCES `RetailDiscount`.`user_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `RetailDiscount`.`discount_configuration` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `config_name` VARCHAR(255) NOT NULL,
  `config_value` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `config_name_UNIQUE` (`config_name` ASC) VISIBLE);

