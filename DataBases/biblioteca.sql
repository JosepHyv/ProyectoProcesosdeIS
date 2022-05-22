-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Empleados`
--

DROP TABLE IF EXISTS `Empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Empleados` (
  `numEmpleados` int NOT NULL COMMENT 'Esta es la llave primaria',
  `fechaNacimiento` date NOT NULL,
  `nss` varchar(11) NOT NULL,
  `curp` varchar(20) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `contrasenia` varchar(50) NOT NULL,
  `tipoContratacion` varchar(50) NOT NULL,
  `idUsuario` varchar(50) NOT NULL COMMENT 'Columna para llave primaria de Usuarios',
  PRIMARY KEY (`numEmpleados`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `Empleados_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Empleados`
--

LOCK TABLES `Empleados` WRITE;
/*!40000 ALTER TABLE `Empleados` DISABLE KEYS */;
INSERT INTO `Empleados` VALUES (1234,'1989-09-16','ABCD2121212','SAMR890616HVZGPSA1','9552211234','contraChida','Encargado','123456789');
/*!40000 ALTER TABLE `Empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Libros`
--

DROP TABLE IF EXISTS `Libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Libros` (
  `idLibro` int NOT NULL AUTO_INCREMENT COMMENT ' llave primaria',
  `autor` varchar(100) NOT NULL,
  `titulo` varchar(150) NOT NULL,
  `editorial` varchar(50) NOT NULL,
  `edicion` varchar(50) NOT NULL,
  PRIMARY KEY (`idLibro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Libros`
--

LOCK TABLES `Libros` WRITE;
/*!40000 ALTER TABLE `Libros` DISABLE KEYS */;
/*!40000 ALTER TABLE `Libros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Prestamo`
--

DROP TABLE IF EXISTS `Prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Prestamo` (
  `idPrestamo` int NOT NULL AUTO_INCREMENT COMMENT 'llave primaria',
  `fehaPrestamo` date NOT NULL,
  `fechaDevolucion` date NOT NULL,
  `idUsuario` varchar(50) NOT NULL COMMENT 'llave foranea',
  `idLibro` int NOT NULL,
  PRIMARY KEY (`idPrestamo`),
  KEY `idUsuario` (`idUsuario`),
  KEY `idLibro` (`idLibro`),
  CONSTRAINT `Prestamo_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuarios` (`idUsuario`),
  CONSTRAINT `Prestamo_ibfk_2` FOREIGN KEY (`idLibro`) REFERENCES `Libros` (`idLibro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prestamo`
--

LOCK TABLES `Prestamo` WRITE;
/*!40000 ALTER TABLE `Prestamo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RecibosDePagosPorDevolucionesTardias`
--

DROP TABLE IF EXISTS `RecibosDePagosPorDevolucionesTardias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RecibosDePagosPorDevolucionesTardias` (
  `idReciboPago` int NOT NULL COMMENT 'id (tal vez podamos auto incrementarla)',
  `fechaPago` date NOT NULL,
  `metodoPago` varchar(50) NOT NULL,
  `idUsuario` varchar(50) NOT NULL COMMENT 'llave foranea de Usuarios',
  `idPrestamo` int NOT NULL COMMENT 'llave foranea de Prestamos',
  KEY `idUsuario` (`idUsuario`),
  KEY `idPrestamo` (`idPrestamo`),
  CONSTRAINT `RecibosDePagosPorDevolucionesTardias_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuarios` (`idUsuario`),
  CONSTRAINT `RecibosDePagosPorDevolucionesTardias_ibfk_2` FOREIGN KEY (`idPrestamo`) REFERENCES `Prestamo` (`idPrestamo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RecibosDePagosPorDevolucionesTardias`
--

LOCK TABLES `RecibosDePagosPorDevolucionesTardias` WRITE;
/*!40000 ALTER TABLE `RecibosDePagosPorDevolucionesTardias` DISABLE KEYS */;
/*!40000 ALTER TABLE `RecibosDePagosPorDevolucionesTardias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuarios`
--

DROP TABLE IF EXISTS `Usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Usuarios` (
  `idUsuario` varchar(50) NOT NULL COMMENT 'Profesor: Numero de trabajador / Alumno: Matricula',
  `nombres` varchar(50) NOT NULL,
  `apellidoPaterno` varchar(50) NOT NULL,
  `apellidoMaterno` varchar(50) NOT NULL,
  `calle` varchar(100) NOT NULL,
  `numero` varchar(20) NOT NULL,
  `colonia` varchar(50) NOT NULL,
  `municipio` varchar(50) NOT NULL,
  `email` varchar(200) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `tipoUsuario` varchar(50) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuarios`
--

LOCK TABLES `Usuarios` WRITE;
/*!40000 ALTER TABLE `Usuarios` DISABLE KEYS */;
INSERT INTO `Usuarios` VALUES ('123456789','Rick','Sanchez','Morty Soto','imaginaria','325','gucci','xalapayork','testMail@test.com','9552211234','EMPLEADO');
/*!40000 ALTER TABLE `Usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `almacen`
--

DROP TABLE IF EXISTS `almacen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `almacen` (
  `numeroCopias` int NOT NULL,
  `idLibro` int NOT NULL COMMENT 'llave foranea',
  KEY `idLibro` (`idLibro`),
  CONSTRAINT `almacen_ibfk_1` FOREIGN KEY (`idLibro`) REFERENCES `Libros` (`idLibro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `almacen`
--

LOCK TABLES `almacen` WRITE;
/*!40000 ALTER TABLE `almacen` DISABLE KEYS */;
/*!40000 ALTER TABLE `almacen` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-22 12:51:27
