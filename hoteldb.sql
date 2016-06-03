-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: hoteldb
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `habitacion`
--

DROP TABLE IF EXISTS `habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `habitacion` (
  `idH` int(11) NOT NULL,
  `numero` varchar(45) NOT NULL,
  `Tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitacion`
--

LOCK TABLES `habitacion` WRITE;
/*!40000 ALTER TABLE `habitacion` DISABLE KEYS */;
INSERT INTO `habitacion` VALUES (1,'1001','Sencilla'),(2,'1002','Sencilla'),(3,'1003','Sencilla'),(4,'1004','Sencilla'),(5,'1005','Doble'),(6,'1006','Sencilla'),(7,'1007','Sencilla'),(8,'1008','Sencilla'),(9,'1009','Doble'),(10,'1010','Sencilla'),(11,'2001','Sencilla'),(12,'2002','Sencilla'),(13,'2003','Sencilla'),(14,'2004','Sencilla'),(15,'2005','Doble'),(16,'2006','Sencilla'),(17,'2007','Sencilla'),(18,'2008','Sencilla'),(19,'2009','Doble'),(20,'2010','Sencilla'),(21,'3001','Sencilla'),(22,'3002','Sencilla'),(23,'3003','Sencilla'),(24,'3004','Sencilla'),(25,'3005','Doble'),(26,'3006','Sencilla'),(27,'3007','Sencilla'),(28,'3008','Sencilla'),(29,'3009','Doble'),(30,'3010','Sencilla'),(31,'4001','Sencilla'),(32,'4002','Sencilla'),(33,'4003','Sencilla'),(34,'4004','Sencilla'),(35,'4005','Doble'),(36,'4006','Sencilla'),(37,'4007','Sencilla'),(38,'4008','Sencilla'),(39,'4009','Doble'),(40,'4010','Sencilla'),(41,'5001','Sencilla'),(42,'5002','Sencilla'),(43,'5003','Sencilla'),(44,'5004','Sencilla'),(45,'5005','Doble'),(46,'5006','Sencilla'),(47,'5007','Sencilla'),(48,'5008','Sencilla'),(49,'5009','Doble'),(50,'5010','Sencilla');
/*!40000 ALTER TABLE `habitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserva` (
  `idR` int(11) NOT NULL,
  `idH` int(11) NOT NULL,
  `Fecha` varchar(45) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idR`,`idH`),
  KEY `idH_idx` (`idH`),
  CONSTRAINT `idH` FOREIGN KEY (`idH`) REFERENCES `habitacion` (`idH`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-01  7:17:17
