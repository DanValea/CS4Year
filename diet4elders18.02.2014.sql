CREATE DATABASE  IF NOT EXISTS `dietforelders` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dietforelders`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: dietforelders
-- ------------------------------------------------------
-- Server version	5.5.16

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `idCategory` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`idCategory`),
  UNIQUE KEY `idCategory_UNIQUE` (`idCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Vegetable mixture'),(2,'Beef meat'),(3,'Egg products'),(4,'Rice products'),(5,'Fish products'),(6,'Chicken meat'),(7,'Basic products of other cereals'),(8,'Edible fungi'),(9,'Tubers'),(10,'Wheat basic products'),(11,'Miscellaneous fruit'),(12,'Cream'),(13,'Sugar products'),(14,'Fruit vegetables'),(15,'Lamb meat'),(16,'Pork meat'),(17,'Liver');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoryparent`
--

DROP TABLE IF EXISTS `categoryparent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoryparent` (
  `idChild` int(11) NOT NULL,
  `idParent` int(11) NOT NULL,
  KEY `idChild` (`idChild`),
  KEY `idParent` (`idParent`),
  CONSTRAINT `categoryparent_ibfk_1` FOREIGN KEY (`idChild`) REFERENCES `category` (`idCategory`),
  CONSTRAINT `categoryparent_ibfk_2` FOREIGN KEY (`idParent`) REFERENCES `category` (`idCategory`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoryparent`
--

LOCK TABLES `categoryparent` WRITE;
/*!40000 ALTER TABLE `categoryparent` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoryparent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food` (
  `idFood` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(250) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`idFood`),
  KEY `type` (`type`),
  CONSTRAINT `food_ibfk_1` FOREIGN KEY (`type`) REFERENCES `foodtype` (`idFoodType`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'Vegetables sour soup','Ciorba de legume',1),(2,'Beef sour soup with vegetables','Ciorba taraneasca de vita',1),(3,'Greek sour soup with beef','Ciorba a la grec de vita',1),(4,'Carp sour soup','Ciorba de crap',1),(5,'Chicken soup with dumplings','Supa de pui cu galuste',1),(6,'Sour soup with potatoes and mushrooms','Supa cu cartofi si ciuperci',1),(7,'Goulash soup','Supa gulas',1),(8,'Apple soup','Supa cu mere',1),(9,'Soup with rice and mushrooms','Supa cu ciuperci si orez',1),(10,'Chicken sour soup with tomatoes','Ciorba cu pui si rosii',1),(11,'Greek sour soup with chicken','Ciorba a la grec de pui',1),(12,'Greek sour soup with lamb','Ciorba a la grec cu miel',1),(13,'Beef sour soup with cabbage','Ciorba cu carne de vita si varza',1),(14,'Pork sour soup with vegetables','Ciorba taraneasca cu carne de porc',1),(15,'Greek sour soup with pork','Ciorba a la grec de porc',1),(16,'Mashed potatoes with grilled sirloin pork','Piure cu muschi de porc la gratar',2),(17,'Mashed potatoes with grilled pork liver','Piure cu ficat de porc la gratar',2),(18,'Mashed potatoes with chicken roast','Piure cu friptura de pui la tava',2),(19,'Vegetables with lamb roast','Legume cu friptura de miel la tava',2),(20,'Rice with grilled sirloin pork','Pilaf cu muschi de porc la gratar',2),(21,'Rice with grilled pork liver','Pilaf cu ficat de porc la gratar',2),(22,'Rice  with chicken roast','Pilaf cu friptura de pui la tava',2),(23,'Mashed potatoes with grilled sirloin beef','Piure cu muschi de vita la gratar',2),(24,'Rice with grilled sirloin beef','Pilaf cu muschi de vita la gratar',2),(25,'Vegetables with grilled sirloin pork','Legume cu muschi de porc la gratar',2),(26,'Vegetables with grilled sirloin beef','Legume cu muschi de vita la gratar',2),(27,'Vegetables with chicken roast','Legume cu friptura de pui la tava',2),(28,'Boiled potatoes with grilled sirloin pork','Cartofi natur cu muschi de porc la gratar',2),(29,'Boiled potatoes with grilled sirloin beef','Cartofi natur cu muschi de vita la gratar',2),(30,'Boiled potatoes with chicken roast','Cartofi natur cu friptura de pui',2);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodcategory`
--

DROP TABLE IF EXISTS `foodcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foodcategory` (
  `idFood` int(11) NOT NULL,
  `idCategory` int(11) NOT NULL,
  KEY `idFood` (`idFood`),
  KEY `idCategory` (`idCategory`),
  CONSTRAINT `foodcategory_ibfk_1` FOREIGN KEY (`idFood`) REFERENCES `food` (`idFood`),
  CONSTRAINT `foodcategory_ibfk_2` FOREIGN KEY (`idCategory`) REFERENCES `category` (`idCategory`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodcategory`
--

LOCK TABLES `foodcategory` WRITE;
/*!40000 ALTER TABLE `foodcategory` DISABLE KEYS */;
INSERT INTO `foodcategory` VALUES (1,1),(2,1),(2,2),(3,2),(3,3),(3,4),(4,5),(4,1),(5,6),(5,7),(5,1),(6,8),(6,9),(7,2),(7,1),(8,11),(8,12),(8,13),(9,4),(9,8),(10,6),(10,14),(11,3),(11,4),(11,6),(12,3),(12,4),(14,16),(14,2),(15,3),(15,4),(15,16),(12,15),(16,16),(16,9),(17,17),(17,9),(18,6),(18,9),(19,15),(19,1),(20,16),(20,4),(21,17),(21,4),(22,4),(22,6),(23,9),(23,2),(24,2),(24,4),(25,1),(25,16),(26,1),(26,2),(27,6),(27,1),(28,9),(28,16),(29,9),(29,2),(30,9),(30,6);
/*!40000 ALTER TABLE `foodcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodentry`
--

DROP TABLE IF EXISTS `foodentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foodentry` (
  `idFood` int(11) NOT NULL,
  `idIngredient` int(11) NOT NULL,
  `quantity` double NOT NULL,
  KEY `idFood` (`idFood`),
  KEY `idIngredient` (`idIngredient`),
  CONSTRAINT `foodentry_ibfk_1` FOREIGN KEY (`idFood`) REFERENCES `food` (`idFood`),
  CONSTRAINT `foodentry_ibfk_2` FOREIGN KEY (`idIngredient`) REFERENCES `ingredient` (`idIngredient`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodentry`
--

LOCK TABLES `foodentry` WRITE;
/*!40000 ALTER TABLE `foodentry` DISABLE KEYS */;
INSERT INTO `foodentry` VALUES (1,69,120),(1,62,17),(1,81,2),(1,75,130),(1,59,170),(1,34,4),(1,108,1),(1,109,0.5),(2,8,75),(2,69,30),(2,71,20),(2,79,20),(2,62,20),(2,59,50),(2,57,10),(2,82,20),(2,75,35),(2,68,10),(2,67,20),(2,82,20),(2,108,2),(2,111,3),(3,8,75),(3,62,20),(3,69,20),(3,79,20),(3,42,40),(3,112,2.5),(3,94,10),(3,91,5),(3,108,4),(4,9,140),(4,62,20),(4,69,20),(4,75,80),(4,71,20),(4,24,5),(4,108,4),(5,6,130),(5,62,15),(5,69,30),(5,79,10),(5,71,15),(5,93,30),(5,89,15),(5,108,4),(5,109,1),(6,59,100),(6,64,50),(6,69,20),(6,62,20),(6,71,40),(6,75,30),(6,34,5),(6,42,40),(6,112,7.5),(6,91,5),(6,108,2),(6,109,1),(7,8,85),(7,59,80),(7,53,10),(7,62,30),(7,89,10),(7,91,20),(7,34,5),(7,108,4),(8,26,200),(8,24,5),(8,33,20),(8,91,10),(8,89,10),(8,107,5),(8,108,2),(9,64,45),(9,34,5),(9,33,10),(9,94,10),(9,91,10),(9,108,2),(10,6,90),(10,75,200),(10,69,20),(10,62,20),(10,79,10),(10,94,10),(10,108,4),(11,6,90),(11,69,20),(11,71,20),(11,79,10),(11,62,20),(11,33,20),(11,91,5),(11,112,7.5),(11,24,10),(11,94,10),(11,108,4),(12,2,80),(12,69,20),(12,71,20),(12,79,10),(12,62,20),(12,33,20),(12,91,5),(12,112,7.5),(12,24,10),(12,94,10),(12,63,20),(12,108,4),(13,8,75),(13,82,130),(13,69,15),(13,71,15),(13,75,25),(13,62,20),(13,53,5),(13,42,40),(13,111,2.5),(13,91,5),(13,112,5),(13,108,4),(14,5,70),(14,69,20),(14,79,20),(14,59,50),(14,57,10),(14,82,20),(14,71,20),(14,75,20),(14,62,20),(14,53,5),(14,52,10),(14,67,20),(14,24,5),(14,108,4),(15,5,80),(15,69,20),(15,71,20),(15,79,20),(15,62,20),(15,33,20),(15,91,5),(15,112,7.5),(15,24,10),(15,94,10),(15,108,4),(16,59,150),(16,43,20),(16,32,3),(16,108,3.5),(16,5,140),(17,59,150),(17,43,20),(17,32,3),(17,108,3),(17,34,2),(17,10,100),(18,59,150),(18,43,20),(18,32,3),(18,108,4),(18,34,5),(18,6,150),(19,59,150),(19,108,3.5),(19,2,160),(19,34,5),(19,69,20),(19,79,10),(19,62,5),(19,81,4),(19,109,2),(20,94,45),(20,108,3.5),(20,62,15),(20,113,4),(20,109,1),(20,34,7.5),(20,5,180),(21,94,45),(21,108,3.5),(21,62,15),(21,113,4),(21,109,1),(21,34,9),(21,10,150),(22,94,45),(24,94,45),(23,59,150),(23,43,20),(23,32,3),(23,108,3.5),(23,8,150),(23,26,10),(23,34,2),(24,108,3.5),(24,62,15),(24,113,4),(24,109,1),(24,34,8.5),(24,8,150),(24,26,10),(22,108,4.5),(22,62,15),(22,113,4),(22,109,1),(22,34,8.5),(22,6,150),(26,59,180),(26,50,90),(26,69,40),(26,94,12),(26,36,2.5),(26,113,4),(26,108,3.5),(26,6,150),(25,59,180),(25,50,90),(25,69,40),(25,94,12),(25,36,2.5),(25,113,4),(25,108,3.5),(25,5,180),(27,59,180),(27,50,90),(27,69,40),(27,94,12),(27,36,2.5),(27,113,4),(27,108,4),(27,6,150),(30,59,250),(30,113,5),(30,108,5),(30,6,150),(28,59,250),(28,113,5),(28,108,4.5),(28,5,180),(29,59,250),(29,113,4.5),(29,108,4.5),(29,34,2.5),(29,8,150);
/*!40000 ALTER TABLE `foodentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodmeal`
--

DROP TABLE IF EXISTS `foodmeal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foodmeal` (
  `idFood` int(11) NOT NULL,
  `idMeal` int(11) NOT NULL,
  KEY `idFood` (`idFood`),
  KEY `idMeal` (`idMeal`),
  CONSTRAINT `foodmeal_ibfk_1` FOREIGN KEY (`idFood`) REFERENCES `food` (`idFood`),
  CONSTRAINT `foodmeal_ibfk_2` FOREIGN KEY (`idMeal`) REFERENCES `meal` (`idMeal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodmeal`
--

LOCK TABLES `foodmeal` WRITE;
/*!40000 ALTER TABLE `foodmeal` DISABLE KEYS */;
INSERT INTO `foodmeal` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(21,2),(22,2),(23,2),(24,2),(25,2),(26,2),(27,2),(28,2),(29,2),(30,2),(16,2),(17,2),(18,2),(19,2),(20,2);
/*!40000 ALTER TABLE `foodmeal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodtype`
--

DROP TABLE IF EXISTS `foodtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foodtype` (
  `idFoodType` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`idFoodType`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodtype`
--

LOCK TABLES `foodtype` WRITE;
/*!40000 ALTER TABLE `foodtype` DISABLE KEYS */;
INSERT INTO `foodtype` VALUES (1,'Soup'),(2,'Main dish'),(3,'Salad'),(4,'Dessert');
/*!40000 ALTER TABLE `foodtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredient` (
  `idIngredient` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `calories` double NOT NULL,
  `proteins` double NOT NULL COMMENT 'Quantity of proteins contained in 100 g or ml of that ingredient',
  `fats` double NOT NULL COMMENT 'Quantity of fats contained in 100 g or ml of that ingredient',
  `carbohydrates` double NOT NULL COMMENT 'Quantity of carbohydrates contained in 100 g or ml of that ingredient',
  `calcium` double NOT NULL DEFAULT '0',
  `iron` double NOT NULL DEFAULT '0',
  `sodium` double NOT NULL DEFAULT '0',
  `vitaminA` double NOT NULL DEFAULT '0',
  `vitaminB` double NOT NULL DEFAULT '0',
  `vitaminC` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`idIngredient`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (1,'Turkey',179,24.5,8.5,0,110,0,750,0,0.29,14.5),(2,'Lamb',260,18,20,0,9,2.4,85,0,0.15,1.5),(3,'Mutton',181,17,12,0,13,0,84,0,0.1,0.5),(4,'Pork, high fat',340,15,30,0,8,2.2,40,0,0.75,0.5),(5,'Pork, low fat',142,20.4,6.3,0,10,0,65,0,0.75,0.5),(6,'Chicken',142,21,6,0,10,2,85,0,0.18,0),(7,'Beef, high fat',277,12,24.5,0,10,0,70,0,0.15,0.5),(8,'Beef, low fat',104,20.4,2.2,0,10,4,70,0,0.15,0.5),(9,'Carp',104,18.9,2.8,0,60,45,750,0,0.69,24.7),(10,'Pork liver',146.2,19,3,6,10,12,0,0,0.3,26),(11,'Herring in tomato sauce',170,16.2,10.4,1.8,20,45,675,0,0.27,10.2),(12,'Herrings',167,18,10,0,57,1.1,118,0.04,0.06,0.5),(13,'Herring in oil',325,13.7,28.9,0,30,1,1000,0.05,0,0),(14,'Carp roe',192,19.5,12.1,0,60,60,350,0,0.48,14.4),(15,'Mackerel in tomato sauce',164,14.5,10.6,1.6,10,60,575,0,0.42,1),(16,'Mackerel in oil',259,16.2,20.7,0,130,60,550,0,0.38,9.5),(17,'Baloney',290,10.2,26.8,0,0,0,1000,0,0.2,0),(18,'Liver pate',261,19.6,19.5,0,30,2.5,545,0,0.33,18.8),(19,'Summer sausage',519,20,47,0,0,0,1200,0,0,0),(20,'Horse mackerel',114,21,3,0,200,15,475,0,0.98,18.1),(21,'Horse mackerel in tomato sauce',125,15.5,6.6,0,30,0,25,0,0.41,24.8),(22,'Cherries',21,1,0.3,18,0,0,0,0,0.22,22),(23,'Grapefruit',30,0.5,0.2,6.5,19,0.3,2,0,0.06,40),(24,'Lemons',36,0.9,0.7,6.2,12,45,0,0,0.17,23.8),(25,'Tangerines',40,0.8,0.1,8.7,23,0.3,0,0,0.9,15.5),(26,'Apples',67,0.3,0.5,15,6,0.35,1,0.03,0.04,9),(27,'Pears',79,1,1,16,13,0.3,3,0.01,0.02,6.1),(28,'Oranges',47,0.8,0.2,10.1,19,0.3,0,0,1.51,15),(29,'Plums',89,0.6,0.1,21,16,0.5,25,0.1,0.8,0),(30,'Grapes',93,2.1,1.7,18,19,0.5,2,0.03,0.05,5),(31,'Raisins',306,2.5,0.5,71,60,3.3,27,0.02,0.15,0),(32,'Margarine',764,0,82.5,0,0,0.08,0.1,0,0,0),(33,'Cream',297,2.5,29.5,3,110,0.17,80,0,0.03,1),(34,'Sunflower oil',930,0,100,0,0,0,0,0,0,0),(35,'Olive oil',900,0,100,0,0.5,0.08,0.1,0,0,0),(36,'Butter',721,6,74,2,16,0.19,10,1,0,0),(37,'Lard',927,0.2,99.5,0,30,30,250,0,0.14,1.2),(39,'Cottage cheese',155,13,9,4.5,162,0,30,0,0.03,1.5),(40,'Processed cheese',366,7,36,0.9,10,15,0,0,0.85,9),(41,'Pressed cheese',233,25,19,1,708,1,1400,0,0.06,0),(42,'Yogurt',50,3.2,2.6,4,125,0.05,50,0,0.03,0),(43,'Cow milk',65,3.5,3.5,4.5,125,0.05,50,0.02,0.04,1.5),(44,'Milk powder',498,27,24,40,939,0.6,410,0,0.28,5),(45,'Sheep salty cheese ',305,18.9,24,1,380,0,2000,0,0,0),(46,'Cow salty cheese ',273,19.4,20.4,1,70,0,1875,0,0,0),(47,'Pepper',112,1.2,8,8,35,0.7,0,0,0.08,100),(48,'Tomato broth',62,3.6,0,11.6,130,0,175,0,0.01,5.6),(49,'Cucumbers in vinegar',9,0.6,0,1.5,70,60,700,0,0.91,18.7),(50,'Green beans',18,1.1,0.4,2.5,230,30,150,0,0.85,24.3),(51,'Bell pepper in vinegar',15,0.7,0,3,0,45,25,0,0.29,23.8),(52,'Pea green beans',72,6.5,0.5,10.8,160,0,625,0,0.91,20.3),(53,'Tomato paste',85,5.4,0,15.4,160,60,650,0,0.31,10.1),(54,'Vegetable stew',73,1,6,3.1,100,45,900,0,0.39,5.5),(55,'Sour cabbage',18,1.2,0,3.3,140,60,575,0,0.74,6.2),(56,'Eggplant',22,1.8,0.3,2.4,18,0.9,355,0.18,0.06,15),(57,'Red bell pepper',39,1.3,0.4,7.3,1385,15,0,0,0,40),(58,'Green bell pepper',17,1.1,0.2,2.5,20,0.5,0,0,0.56,24.3),(59,'Potatoes',88,2,0.2,19,14,0.9,4,0.01,0.1,27),(60,'New potatoes',80,1.7,0.2,17.4,15,1,3,0.01,0.11,30),(61,'Cucumber',19,1.3,0.2,2.9,21,0.8,3,0,0.05,9.6),(62,'Dried onion',40,1.5,0.2,8,40,0.5,9,0.02,0.03,20),(63,'Green onion',20,1,0.2,3,120,30,800,0,0.69,0),(64,'Mushrooms',35,5,0.5,2.3,12,1,6,0,0.21,4),(65,'Cauliflower',30,2.8,0.3,3.9,22,1.1,20,0.03,0.13,72),(66,'Courgettes',18,0.9,0.1,3.2,68,0.8,4,1.7,0.09,27),(67,'Green beans (raw)',33,2,0.2,5.7,44,1,2,0.19,0.07,19),(68,'Pea green beans (raw)',96,8.4,0.5,14,50,15,400,0,0.08,12.2),(69,'Carrots',45,1.5,0.3,8.8,23,0.8,48,0,0.04,6),(70,'Parsnip',72,1.4,0.5,15,230,45,575,0,0.2,22.3),(71,'Parsley roots',55,3.7,1,7.6,195,7.7,29,2.47,0.11,197),(72,'Leek',54,2.3,0.4,9.9,170,3,76,0,0.19,30),(73,'Radishes',19,0.3,0.1,3.8,37,1.1,15,0.01,0.06,21),(75,'Tomatoes',20,1.1,0.3,3.1,11,0.6,3,0.33,0.08,30),(77,'Beetroot',43,1.3,0.1,9,120,0.5,145,2,0.03,34),(78,'Spinach',25,3.5,0.3,2,10,3.1,8,9,0.04,8),(79,'Celery root',33,1.4,0.3,5.9,55,0.5,98,0,0.05,7),(80,'Nettles',68,7.9,0.7,7.1,590,590,0,0,1,175),(81,'Garlic',137,7.2,0.2,26,38,1.5,32,0,0.2,14),(82,'White cabbage',33,1.8,0.2,5.8,70,0.5,13,0.01,0.05,50),(83,'Bruxelles cabbage',50,4,0.5,7,50,0.5,15,0,0.03,45),(85,'Beans',303,23,1.7,47,190,15,300,0,0.41,19.7),(86,'Black olives',156,1.6,15,3.5,85,1.6,0,0.09,0,0),(87,'Green olives',177,12.5,10,8.1,80,1,0,0,0,0),(88,'Nuts',650,21,59,3.7,82,2.1,4,0.01,0.4,3),(89,'Chicken eggs',171,14,12,0.6,20,15,175,0,0.2,9.5),(90,'Cookies',425,8.2,9.5,74,10,30,500,0,0.61,5.5),(91,'Wheat flour',349,11,1.4,71,80,15,925,0,0.35,23.6),(92,'Corn flour',351,9.6,1.7,72.1,70,0,500,0,0.63,23.2),(93,'Semolina',358,9.4,0.9,75.4,100,30,350,0,0.98,6.4),(94,'Rice',354,8.1,1.2,75.5,240,15,150,0,0.75,7.2),(95,'White wheat bread',247,7.5,0.4,52,140,60,975,0,0.36,5.4),(96,'Intermediate wheat bread',234,7.5,0.7,43,100,0,100,0,0.03,17.8),(97,'Wholemeal wheat bread',242,8.4,1.2,48,0,15,775,0,0.05,17),(98,'Graham bread',256,9.1,1,51,60,60,275,0,0.28,24.6),(99,'Egg pasta',386,10.2,2.2,79.1,170,30,100,0,0.89,9.2),(100,'Regular pasta',360,10.9,0.6,75.6,130,60,175,0,0.94,9.6),(101,'Milk toffee',394,0,0,96,190,15,725,0,0.32,15.9),(102,'Milk chocolate',605,6.9,40,50,120,15,575,0,0.14,12.8),(103,'Cherry jam',282,0.77,0,68,50,0,475,0,0.91,22.3),(104,'Apricot jam',240,0.65,0,58,20,45,300,0,0.3,0.1),(105,'Sour cherry jam',250,0.88,0,60,20,60,875,0,0.76,15.1),(106,'Honey',304,0.5,0,0.2,10,45,75,0,0.99,17.1),(107,'Sugar',410,0,0,100,110,60,150,0,0.64,22.9),(108,'Salt',0,0,0,0,24,0.33,38758,0,0,0),(109,'Black pepper',255,10.95,3.26,64.81,437,28.86,44,0,0,21),(111,'Vinegar',21,0,0,0.93,7,0.2,5,0,0,0),(112,'Egg yolk',322,15.9,26.5,3.6,0,0,0,0,0,0),(113,'Parsley leaves',116,1.8,0.5,26,34,0.8,9,2.31,0.09,22);
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meal` (
  `idMeal` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`idMeal`),
  UNIQUE KEY `idMeal_UNIQUE` (`idMeal`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
INSERT INTO `meal` VALUES (1,'Breakfast'),(2,'Lunch'),(3,'Dinner');
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-02-18 22:09:45
