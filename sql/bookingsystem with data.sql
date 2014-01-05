-- phpMyAdmin SQL Dump
-- version 4.0.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Czas wygenerowania: 04 Sty 2014, 10:11
-- Wersja serwera: 5.5.8
-- Wersja PHP: 5.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `bookingsystem`
--
CREATE DATABASE IF NOT EXISTS `bookingsystem`
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;
USE `bookingsystem`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `addition`
--

CREATE TABLE IF NOT EXISTS `addition` (
  `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  AUTO_INCREMENT =1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `id`           BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `city`         VARCHAR(255) NOT NULL,
  `street`       VARCHAR(255) NOT NULL,
  `building_no`  INT(11)      NOT NULL,
  `apartment_no` INT(11) DEFAULT NULL,
  `postcode`     VARCHAR(8)   NOT NULL,
  `country`      VARCHAR(50)  NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  AUTO_INCREMENT =6;

--
-- Zrzut danych tabeli `address`
--

INSERT INTO `address` (`id`, `city`, `street`, `building_no`, `apartment_no`, `postcode`, `country`) VALUES
  (1, 'Krakow', 'Wadowicka', 6, NULL, '12-234', 'Polska'),
  (2, 'Wroclaw', 'Wroclawska', 7, 2, '32-234', 'Polska'),
  (3, 'Krakow', 'Lubicz', 1, NULL, '30-200', 'Polska'),
  (4, 'Wrocław', 'Swidnicka', 120, 10, '44-450', 'Polska'),
  (5, 'Wrocław', 'Swidnicka', 120, 10, '44-450', 'Polska');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `first_name`    VARCHAR(255) NOT NULL,
  `last_name`     VARCHAR(255) NOT NULL,
  `pesel`         BIGINT(20)   NOT NULL,
  `nip`           BIGINT(20) DEFAULT NULL,
  `email`         VARCHAR(255) NOT NULL,
  `phone_number`  VARCHAR(25)  NOT NULL,
  `pass`          VARCHAR(255) NOT NULL,
  `address_id`    BIGINT(20)   NOT NULL,
  `register_date` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date`   TIMESTAMP    NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  AUTO_INCREMENT =3;

--
-- Zrzut danych tabeli `client`
--

INSERT INTO `client` (`id`, `first_name`, `last_name`, `pesel`, `nip`, `email`, `phone_number`, `pass`, `address_id`, `register_date`, `update_date`)
VALUES
  (1, 'Jan', 'Nowak', 85010101234, NULL, 'jan.nowak@gmail.com', '791234123', 'pass', 4, '2014-01-04 09:11:25', NULL),
  (2, 'Jan', 'Nowak', 85010101234, NULL, 'jan.nowak@gmail.com', '791234123', 'pass', 5, '2014-01-04 09:11:26', NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `hotel`
--

CREATE TABLE IF NOT EXISTS `hotel` (
  `id`           BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(255) NOT NULL,
  `description`  VARCHAR(255) DEFAULT NULL,
  `phone_number` VARCHAR(25)  NOT NULL,
  `email`        VARCHAR(255) NOT NULL,
  `address_id`   BIGINT(20)   NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  AUTO_INCREMENT =3;

--
-- Zrzut danych tabeli `hotel`
--

INSERT INTO `hotel` (`id`, `name`, `description`, `phone_number`, `email`, `address_id`) VALUES
  (1, 'Hotel BLABLA', NULL, '+48 0123456789', 'kontakt@hotelblabla.pl', 3),
  (2, 'Hotel BLABLA', NULL, '+48 0123456789', 'kontakt@hotelblabla.pl', 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `hotel_client`
--

CREATE TABLE IF NOT EXISTS `hotel_client` (
  `client_id` BIGINT(20) NOT NULL,
  `hotel_id`  BIGINT(20) NOT NULL,
  PRIMARY KEY (`client_id`, `hotel_id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

--
-- Zrzut danych tabeli `hotel_client`
--

INSERT INTO `hotel_client` (`client_id`, `hotel_id`) VALUES
  (1, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `hotel_user`
--

CREATE TABLE IF NOT EXISTS `hotel_user` (
  `hotel_id` BIGINT(20) NOT NULL,
  `user_id`  BIGINT(20) NOT NULL,
  PRIMARY KEY (`hotel_id`, `user_id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

--
-- Zrzut danych tabeli `hotel_user`
--

INSERT INTO `hotel_user` (`hotel_id`, `user_id`) VALUES
  (2, 1),
  (2, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `price`
--

CREATE TABLE IF NOT EXISTS `price` (
  `id`          BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `room_type`   VARCHAR(10) DEFAULT NULL,
  `person_type` VARCHAR(10) NOT NULL,
  `value`       INT(11)     NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  AUTO_INCREMENT =1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `id`           BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(255) DEFAULT NULL,
  `date_from`    DATE       NOT NULL,
  `date_to`      DATE       NOT NULL,
  `person_count` INT(11)    NOT NULL,
  `date_edit`    DATE DEFAULT NULL,
  `client_id`    BIGINT(20) NOT NULL,
  `status_id`    BIGINT(20) NOT NULL,
  `entry_date`   TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date`  TIMESTAMP  NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  AUTO_INCREMENT =2;

--
-- Zrzut danych tabeli `reservation`
--

INSERT INTO `reservation` (`id`, `name`, `date_from`, `date_to`, `person_count`, `date_edit`, `client_id`, `status_id`, `entry_date`, `update_date`)
VALUES
  (1, 'Rezerwacja 1', '2014-01-04', '2014-01-30', 3, NULL, 2, 1, '2014-01-04 09:11:26', NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
  `room_no`     INT(11)    NOT NULL,
  `name`        VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `bed`         VARCHAR(5) DEFAULT NULL,
  `capacity`    INT(11) DEFAULT NULL,
  `hotel_id`    BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  AUTO_INCREMENT =4;

--
-- Zrzut danych tabeli `room`
--

INSERT INTO `room` (`id`, `room_no`, `name`, `description`, `bed`, `capacity`, `hotel_id`) VALUES
  (1, 1, 'Pokoj goscinny nr 1', NULL, '2x1', 2, 2),
  (2, 1, 'Pokoj goscinny nr 2', NULL, '2x1', 2, 2),
  (3, 1, 'Pokoj goscinny nr 3', NULL, '2x1', 2, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `room_addition`
--

CREATE TABLE IF NOT EXISTS `room_addition` (
  `room_id`     BIGINT(20) NOT NULL,
  `addition_id` BIGINT(20) NOT NULL
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `room_price`
--

CREATE TABLE IF NOT EXISTS `room_price` (
  `room_id`  BIGINT(20) NOT NULL,
  `price_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`room_id`, `price_id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `room_reservation`
--

CREATE TABLE IF NOT EXISTS `room_reservation` (
  `room_id`        BIGINT(20) NOT NULL,
  `reservation_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`room_id`, `reservation_id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

--
-- Zrzut danych tabeli `room_reservation`
--

INSERT INTO `room_reservation` (`room_id`, `reservation_id`) VALUES
  (2, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
  `status_type` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  AUTO_INCREMENT =5;

--
-- Zrzut danych tabeli `status`
--

INSERT INTO `status` (`id`, `status_type`, `description`) VALUES
  (1, 'Oczekuje', 'Rezerwacja oczekuje na potwierdzenie'),
  (2, 'Potwierdzono', 'Potwierdzono ale jeszcze nei zaplacono'),
  (3, 'Zapłacono', 'Gotowa rezerwacja'),
  (4, 'Anulowano', 'Rezerwacja do usunięcia');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id`            BIGINT(20)                         NOT NULL AUTO_INCREMENT,
  `first_name`    VARCHAR(255)                       NOT NULL,
  `last_name`     VARCHAR(255)                       NOT NULL,
  `pesel`         BIGINT(20)                         NOT NULL,
  `nip`           BIGINT(20) DEFAULT NULL,
  `email`         VARCHAR(255)                       NOT NULL,
  `address_id`    BIGINT(20)                         NOT NULL,
  `phone_number`  VARCHAR(25)                        NOT NULL,
  `pass`          VARCHAR(255)                       NOT NULL,
  `user_type`     ENUM('EMPLOYEE', 'OWNER', 'ADMIN') NOT NULL DEFAULT 'EMPLOYEE',
  `register_date` TIMESTAMP                          NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date`   TIMESTAMP                          NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  AUTO_INCREMENT =3;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id`, `first_name`, `last_name`, `pesel`, `nip`, `email`, `address_id`, `phone_number`, `pass`, `user_type`, `register_date`, `update_date`)
VALUES
  (1, 'Zenon', 'Breszka', 90030801234, NULL, 'z@gmail.pl', 1, '792011166', 'admin', 'ADMIN', '2014-01-04 09:11:25',
   NULL),
  (2, 'Rysiu', 'Hebel', 80030801234, NULL, 'r@gmail.pl', 2, '888011166', 'user', 'EMPLOYEE', '2014-01-04 09:11:25',
   NULL);

/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
