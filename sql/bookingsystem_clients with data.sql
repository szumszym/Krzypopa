-- phpMyAdmin SQL Dump
-- version 4.0.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Czas wygenerowania: 10 Cze 2014, 21:38
-- Wersja serwera: 5.5.8
-- Wersja PHP: 5.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `bookingsystem_clients`
--
CREATE DATABASE IF NOT EXISTS `bookingsystem_clients` DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;
USE `bookingsystem_clients`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `building_no` varchar(11) NOT NULL,
  `apartment_no` int(11) DEFAULT NULL,
  `postcode` varchar(8) NOT NULL,
  `country` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Zrzut danych tabeli `address`
--

INSERT INTO `address` (`id`, `city`, `street`, `building_no`, `apartment_no`, `postcode`, `country`) VALUES
(7, 'Katowice', 'Krakowska', '2', 1, '33-450', 'Poland'),
(8, 'Szczecin', 'Gdanska', '56', 7, '12-450', 'Poland'),
(11, 'Malbork', 'Krótka', '8', NULL, '65-002', 'Poland');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `pesel` bigint(20) NOT NULL,
  `nip` bigint(20) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `phone_number` varchar(25) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `address_id` bigint(20) NOT NULL,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6nxjf59jdjxiysy7qke8l36j8` (`address_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Zrzut danych tabeli `client`
--

INSERT INTO `client` (`id`, `first_name`, `last_name`, `pesel`, `nip`, `email`, `phone_number`, `pass`, `address_id`, `register_date`, `update_date`) VALUES
(1, 'Jan', 'Nowak', 85010101234, NULL, 'jan.nowak@gmail.pl', '791234123', 'jan', 7, '2014-01-04 09:11:25', NULL),
(2, 'Jerzy', 'Kowalski', 85010101234, NULL, 'jerzy.kowalski@gmail.pl', '791234123', 'jerzy', 8, '2014-01-04 09:11:26', NULL),
(3, 'Jolanta', 'Iksińska', 89123134509, NULL, 'jola.x@gmail.pl', '887676555', 'jola', 11, '2014-02-03 19:17:55', NULL);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_6nxjf59jdjxiysy7qke8l36j8` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
