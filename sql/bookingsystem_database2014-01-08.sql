CREATE DATABASE  IF NOT EXISTS `bookingsystem` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bookingsystem`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: bookingsystem
-- ------------------------------------------------------
-- Server version	5.6.15-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `addition`
--

DROP TABLE IF EXISTS `addition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `publish` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addition`
--

LOCK TABLES `addition` WRITE;
/*!40000 ALTER TABLE `addition` DISABLE KEYS */;
INSERT INTO `addition` VALUES (6,'Alo','Des',1.21,1),(7,'MAlo','a',1,1),(8,'MAlo','a',2,1),(9,'MAlo','a',22.21,1),(10,'MAlo','a',225.21,1),(11,'HAlo','DASd',123.5,1),(12,'asda','asdas',1231.23,1),(13,'asda','asdas',1231.23,1),(14,'asda','asdas',1231.23,1),(15,'asd','asd',3,1),(16,'asd','asd',123,1),(17,'asd','asd',123,1),(18,'asd','asd',123,1),(19,'asd','asd',123,1),(20,'asd','asd',123,1),(21,'asd','asd',123,1),(22,'xzcz','czx',543,1);
/*!40000 ALTER TABLE `addition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `building_no` int(11) NOT NULL,
  `apartment_no` int(11) DEFAULT NULL,
  `postcode` varchar(8) NOT NULL,
  `country` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (19,'Krakow','Wadowicka',50,12,'31–962','Polska'),(20,'Zator','Wroclawska',21,2,'54-612','Polska'),(21,'Warszawa','Niska',21,1,'05-500','Polska'),(22,'Legnica\n','Zawiszy',12,NULL,'59-200','Polska'),(23,'Malec','Krucza',35,NULL,'32-605','Polska'),(24,'Dwory','Zimna',15,NULL,'32-600','Polska'),(25,'Ustrzyki','Stawowa',86,NULL,'38-705','Polska'),(26,'Szczecin','Wroclawska',110,2,'78-400','Polska'),(27,'Zakopane','Opolska',30,2,'34-503','Polska'),(28,'Nowy Targ','Warszawska',25,NULL,'34-434','Polska'),(29,'Warszawa','Ulca',12,NULL,'36-695','Australia'),(30,'Jankow','jankowice',10,NULL,'301-12','United States'),(31,'Warszawa','polonk',12,NULL,'23-233','United Kingdom'),(32,'Lipsk','Wola',2,NULL,'23-233','Poland'),(33,'WAwa','klapa',1,NULL,'23-233','Poland'),(34,'Bierzanw','Zakopan',540,NULL,'30-580','Poland');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
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
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Marek','Ryszka',1,NULL,'a@gmail.com','1','a',21,'2013-12-30 08:36:42','0000-00-00 00:00:00'),(2,'Joanna','Maj',2,NULL,'b@gmail.com','2','b',22,'2013-12-30 09:09:26','0000-00-00 00:00:00'),(3,'Krystyna','Dworek',3,NULL,'c@gmail.com','2','c',23,'2013-12-30 09:23:24','0000-00-00 00:00:00'),(4,'Adam','Kozak',5,NULL,'d@gmail.com','3','d',24,'2013-12-30 09:24:54','0000-00-00 00:00:00'),(5,'Zenek','Breszka',12345678901,1231231233,'for@fun.pl','+48 123123123','qwe123',31,'2014-01-07 00:54:30','0000-00-00 00:00:00'),(6,'Jan','Kwas',1234235423,NULL,'u@haj.pl','+48 123123123','qwe123',32,'2014-01-07 01:02:47','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `phone_number` varchar(25) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'Wysoka','Short Description','+91 123 123 123','kontakt@wysoka-garden.pl',25),(2,'Kryniczka','Short Description','+48 123123123','kontakt@kryniczka-garden.pl',27),(3,'Rykowikso','Short Description','+48 123123123','kontakt@rykowisko-garden.pl',28),(4,'Klas','Przyjemny hotelik','481216321','wp@wp.pl',34);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_client`
--

DROP TABLE IF EXISTS `hotel_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel_client` (
  `client_id` bigint(20) NOT NULL,
  `hotel_id` bigint(20) NOT NULL,
  PRIMARY KEY (`client_id`,`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_client`
--

LOCK TABLES `hotel_client` WRITE;
/*!40000 ALTER TABLE `hotel_client` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_user`
--

DROP TABLE IF EXISTS `hotel_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel_user` (
  `hotel_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`hotel_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_user`
--

LOCK TABLES `hotel_user` WRITE;
/*!40000 ALTER TABLE `hotel_user` DISABLE KEYS */;
INSERT INTO `hotel_user` VALUES (1,12),(2,12),(3,12),(4,12);
/*!40000 ALTER TABLE `hotel_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price`
--

DROP TABLE IF EXISTS `price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_type` varchar(10) DEFAULT NULL,
  `person_type` varchar(10) NOT NULL,
  `value` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price`
--

LOCK TABLES `price` WRITE;
/*!40000 ALTER TABLE `price` DISABLE KEYS */;
INSERT INTO `price` VALUES (1,'Apartment','Adult',1500),(2,'Small Room','Adult',100),(3,'Big Room','Adult',300),(4,'King ap.','Adult',3000),(5,'ASd','Adult',123),(6,'ASd','Adult',123),(7,'AAAA','Kid',12);
/*!40000 ALTER TABLE `price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `date_from` date NOT NULL,
  `date_to` date NOT NULL,
  `person_count` int(11) NOT NULL,
  `date_edit` date DEFAULT NULL,
  `client_id` bigint(20) NOT NULL,
  `status_id` bigint(20) NOT NULL,
  `entry_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `price` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (6,'ZZenak Zaprasza','2014-01-08','2014-01-26',2,NULL,5,13,'2014-01-07 21:10:30','2014-01-07 21:10:30',0),(7,'TEstowa rezerwacja','2014-01-31','2014-02-28',3,NULL,1,13,'2014-01-07 23:36:48','2014-01-07 23:36:48',2991.67);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_no` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `bed` varchar(5) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `hotel_id` bigint(20) DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,1,'Nowy','Haha',NULL,5,2,100),(11,12,'Nam','Kla','None',3,3,100),(12,171,'Nowszy','Kakslkdaf kjsdhakjsd asjdhasd jkasdhkjasdh ashdjkasdhk','None',6,2,300);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_addition`
--

DROP TABLE IF EXISTS `room_addition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_addition` (
  `room_id` bigint(20) NOT NULL,
  `addition_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_addition`
--

LOCK TABLES `room_addition` WRITE;
/*!40000 ALTER TABLE `room_addition` DISABLE KEYS */;
INSERT INTO `room_addition` VALUES (1,6),(10,12),(10,10),(10,8),(10,7),(11,10),(11,15),(11,14),(11,7),(11,12),(12,8),(12,10),(12,14);
/*!40000 ALTER TABLE `room_addition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_price`
--

DROP TABLE IF EXISTS `room_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_price` (
  `room_id` bigint(20) NOT NULL,
  `price_id` bigint(20) NOT NULL,
  PRIMARY KEY (`room_id`,`price_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_price`
--

LOCK TABLES `room_price` WRITE;
/*!40000 ALTER TABLE `room_price` DISABLE KEYS */;
INSERT INTO `room_price` VALUES (1,1),(2,1),(3,3),(4,2),(5,4);
/*!40000 ALTER TABLE `room_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_reservation`
--

DROP TABLE IF EXISTS `room_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_reservation` (
  `room_id` bigint(20) NOT NULL,
  `reservation_id` bigint(20) NOT NULL,
  PRIMARY KEY (`room_id`,`reservation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_reservation`
--

LOCK TABLES `room_reservation` WRITE;
/*!40000 ALTER TABLE `room_reservation` DISABLE KEYS */;
INSERT INTO `room_reservation` VALUES (1,4),(1,5),(2,2),(2,3),(2,4),(2,6),(3,1),(3,4),(4,4),(5,4),(11,7);
/*!40000 ALTER TABLE `room_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status_type` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `publish` tinyint(1) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (13,'sda','sadsd',0,'#b53c3f'),(14,'asdfafvxzx','dasavxcz',0,'#65b3dc'),(15,'czxvzx','sdfasf',0,'#64f832');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `pesel` bigint(20) NOT NULL,
  `nip` bigint(20) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `address_id` bigint(20) NOT NULL,
  `phone_number` varchar(25) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `user_type` enum('ADMIN','OWNER','EMPLOYEE') NOT NULL DEFAULT 'EMPLOYEE',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Zenon','Breszka',90030801234,NULL,'z@gmail.pl',19,'792011166','admin','ADMIN','2013-12-29 17:35:15','0000-00-00 00:00:00'),(2,'Rysiu','Hebel',80030801234,NULL,'r@gmail.pl',20,'888011166','user','EMPLOYEE','2013-12-29 17:35:15','0000-00-00 00:00:00'),(12,'TEmp','Hebel',80030801234,NULL,'b@b.pl',26,'888011166','user','OWNER','2013-12-30 09:44:20','0000-00-00 00:00:00'),(13,'qwe','qwe',1234235423,NULL,'sebw20@gmail.com',29,'+48 123123123','qwe123','EMPLOYEE','2014-01-06 23:54:48','0000-00-00 00:00:00'),(14,'Seb','Wloszek',123123123,NULL,'w@w.pl',30,'300600500','qwe123','EMPLOYEE','2014-01-07 00:40:40','0000-00-00 00:00:00'),(15,'Leszek','Gwidzon',12345678901,1231231233,'p@p.pl',33,'+48 123123123','qwe123','EMPLOYEE','2014-01-07 01:14:56','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-08  0:57:04
