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
  `price` DOUBLE NOT NULL,
  `published` tinyint(1) NOT NULL DEFAULT '0',
  `hotel_id`    BIGINT(20) DEFAULT NULL,

  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `address`
--

INSERT INTO `addition` (`id`, `name`, `description`, `price`, `published`, `hotel_id`) VALUES
  (1, 'TV', 'telewizor FullHD', '5', 1,1),
  (2, 'Balkon', 'wymiary 2x1m', '20', 1,1),
  (3, 'Radio', 'radio stereo', '5', 1,1),
  (4, 'TV', 'telewizor', '0', 1,2),
  (5, 'Balkon', 'wymiary 2x2m', '30', 1,2),
  (6, 'Wieża HiFi', 'wysokiej jakości sprzęt grający', '10', 1,2),
  (7, 'Dodatkowa pościel', 'dodatkowy komplet pościeli', '20', 1,2);
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
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `address`
--

INSERT INTO `address` (`id`, `city`, `street`, `building_no`, `apartment_no`, `postcode`, `country`) VALUES
  (1, 'Wroclaw', 'Oleśnicka', 7, 2, '44-234', 'Poland'),
  (2, 'Krakow', 'Lubicz', 1, NULL, '30-200', 'Poland'),
  (3, 'Berlin', 'Uber Strasse', 88, 2, '01-022', 'Gernamy'),
  (4, 'Wrocław', 'Komandosow', 10, 6, '44-460', 'Poland'),
  (5, 'Ustrzyki Górne', 'Bieszczadzka', 2, NULL, '76-060', 'Poland'),
  (6, 'Wrocław', 'Swidnicka', 120, 10, '44-450', 'Poland'),
  (7, 'Katowice', 'Krakowska', 2, 1, '33-450', 'Poland'),
  (8, 'Szczecin', 'Gdanska', 56, 7, '12-450', 'Poland'),
  (9, 'Kraków', 'Kupa', 2, NULL, '30-022', 'Poland'),
  (10, 'Rzeszów', 'Podpromie', 13, NULL, '55-062', 'Poland');
  ;

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
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `client`
--

INSERT INTO `client` (`id`, `first_name`, `last_name`, `pesel`, `nip`, `email`, `phone_number`, `pass`, `address_id`, `register_date`, `update_date`)
VALUES
  (1, 'Jan', 'Nowak', 85010101234, NULL, 'jan.nowak@gmail.com', '791234123', 'jan', 7, '2014-01-04 09:11:25', NULL),
  (2, 'Jerzy', 'Kowalski', 85010101234, NULL, 'jerzy.kowalski@gmail.com', '791234123', 'jerzy', 8, '2014-01-04 09:11:26', NULL);

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
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `hotel`
--

INSERT INTO `hotel` (`id`, `name`, `description`, `phone_number`, `email`, `address_id`) VALUES
  (1, 'Zajazd pod Politechniką', NULL, '+48 0123456789', 'kontakt@zajazdpk.pl', 9),
  (2, 'Hotel Arruba', NULL, '+48 0123456789', 'kontakt@arruba.pl', 10);

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
  (1, 1),
  (2, 2);

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
  (1, 2),
  (1, 3),
  (2, 4),
  (2, 5),
  (2, 6);

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
  `client_id`    BIGINT(20) NOT NULL,
  `status_id`    BIGINT(20) NOT NULL,
  `entry_date`   TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date`  TIMESTAMP  NULL DEFAULT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `reservation`
--

INSERT INTO `reservation` (`id`, `name`, `date_from`, `date_to`, `person_count`, `client_id`, `status_id`, `entry_date`, `update_date`, `price`)
VALUES
  (1, 'Rezerwacja 1', '2014-01-18', '2014-01-22', 3, 2, 1, '2014-01-18 09:11:26', NULL, 400),
  (2, 'Rezerwacja 2', '2014-01-19', '2014-01-20', 5, 1, 2, '2014-01-18 09:11:26', NULL, 640);

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
  `price` DOUBLE NOT NULL,
  `published` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `room`
--

INSERT INTO `room` (`id`, `room_no`, `name`, `description`, `bed`, `capacity`, `hotel_id`, `price`, `published`) VALUES
  (1, 1, 'Pokoj goscinny nr 1', NULL, '2x1', 2, 1, 60, 1),
  (2, 2, 'Pokoj goscinny nr 2', NULL, '3x1', 3, 1, 100, 1),
  (3, 3, 'Pokoj goscinny nr 3', NULL, '2x2', 4, 1, 90, 1),
  (4, 1, 'Pokoj nr 1', 'apartament z balkonem', '3x2', 6, 2, 160, 1),
  (5, 2, 'Pokoj nr 2', NULL, '1x2', 6, 2, 100, 1);

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

--
-- Zrzut danych tabeli `room`
--

INSERT INTO `room_addition` (`room_id`, `addition_id`) VALUES
  (1, 1),
  (1, 3),
  (2, 1),
  (4, 5);

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
  (2, 1),
  (4, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
  `status_type` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `color` VARCHAR(8) DEFAULT NULL,
  `hotel_id`    BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `status`
--

INSERT INTO `status` (`id`, `status_type`, `description`, `color`,`hotel_id`) VALUES
  (1, 'Oczekuje', 'Rezerwacja oczekuje na potwierdzenie', '#0000ff', 1),
  (2, 'Potwierdzono', 'Potwierdzono ale jeszcze nie zaplacono', '#ffd900', 1),
  (3, 'Zapłacono', 'Gotowa rezerwacja', '#00ff00', 1),
  (4, 'Anulowano', 'Rezerwacja do usunięcia', '#ff0000', 1),
  (5, 'Oczekuje', 'Czeka na potwierdzenie', '#0064ff', 2),
  (6, 'Potwierdzono', 'Jeszcze nie zaplacono', '#ffd964', 2),
  (7, 'Zapłacono', 'Gotowa', '#64ff00', 2),
  (8, 'Anulowano', 'Do usunięcia', '#ff3232', 2);

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
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id`, `first_name`, `last_name`, `pesel`, `nip`, `email`, `address_id`, `phone_number`, `pass`, `user_type`, `register_date`, `update_date`)
VALUES
  (1, 'Zenon', 'Breszka', 90030801234, NULL, 'admin@gmail.pl', 1, '792011166', 'admin', 'ADMIN', '2014-01-04 09:11:25', NULL),
  (2, 'Marianna', 'Nowak', 80070801234, NULL, 'owner@gmail.pl', 2, '555011166', 'owner', 'OWNER', '2014-01-04 09:11:25', NULL),
  (3, 'Rysiu', 'Hebel', 83030801134, NULL, 'emp@gmail.pl', 3, '888011166', 'emp', 'EMPLOYEE', '2014-01-04 09:11:25',NULL),
  (4, 'Piotr', 'Szumacher', 76091201234, NULL, 'owner2@gmail.pl', 4, '555011166', 'owner', 'OWNER', '2014-01-04 09:11:25', NULL),
  (5, 'Mariusz', 'Walec', 55110301234, NULL, 'emp2@gmail.pl', 5, '555011166', 'emp', 'EMPLOYEE', '2014-01-04 09:11:25', NULL),
  (6, 'Martyna', 'Powłoka', 91010701234, NULL, 'emp3@gmail.pl', 6, '555011166', 'emp', 'EMPLOYEE', '2014-01-04 09:11:25', NULL);

/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
