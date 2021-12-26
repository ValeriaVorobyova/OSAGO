-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sosago
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorization`
--

DROP TABLE IF EXISTS `authorization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorization` (
  `idauthorization` int NOT NULL,
  `login` varchar(1000) NOT NULL,
  `password` varchar(1000) NOT NULL,
  PRIMARY KEY (`idauthorization`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorization`
--

LOCK TABLES `authorization` WRITE;
/*!40000 ALTER TABLE `authorization` DISABLE KEYS */;
INSERT INTO `authorization` VALUES (1,'63A9F0EA7BB98050796B649E85481845','63A9F0EA7BB98050796B649E85481845');
/*!40000 ALTER TABLE `authorization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carsdata`
--

DROP TABLE IF EXISTS `carsdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carsdata` (
  `idcars` int NOT NULL AUTO_INCREMENT,
  `year` varchar(4) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `power` varchar(4) NOT NULL,
  `region` varchar(90) NOT NULL,
  `drivers` varchar(45) NOT NULL,
  `VIN` varchar(17) NOT NULL,
  `period` varchar(45) NOT NULL,
  `number` varchar(8) NOT NULL,
  PRIMARY KEY (`idcars`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carsdata`
--

LOCK TABLES `carsdata` WRITE;
/*!40000 ALTER TABLE `carsdata` DISABLE KEYS */;
INSERT INTO `carsdata` VALUES (64,'2008','Ford','CMAX','120','Москва','Ограничено','123VR4D6D7S3K9G6N','1 год','М127ОА'),(65,'2008','Ford','CMAX','120','Москва','Ограничено','N8F5YEHJ573RYI56I','1 год','Н567ЕТ');
/*!40000 ALTER TABLE `carsdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driverdata`
--

DROP TABLE IF EXISTS `driverdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driverdata` (
  `iddriversdata` int NOT NULL AUTO_INCREMENT,
  `idcar` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `patronymic` varchar(45) DEFAULT NULL,
  `birthDate` varchar(45) NOT NULL,
  `passport` varchar(45) NOT NULL,
  `passportDate` varchar(45) NOT NULL,
  `driverCardNumber` varchar(45) NOT NULL,
  `experience` varchar(45) NOT NULL,
  `driverRating` varchar(45) NOT NULL,
  PRIMARY KEY (`iddriversdata`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driverdata`
--

LOCK TABLES `driverdata` WRITE;
/*!40000 ALTER TABLE `driverdata` DISABLE KEYS */;
INSERT INTO `driverdata` VALUES (45,'64','Ирина','Иванова','Ивановна','1993-11-05','1234 098787','2021-11-29','1239867523','2 года','11'),(46,'65','Ирина','Евстратова','Ивановна','1984-11-30','6789 457876','2021-11-29','5789765345','2 года','12'),(47,'65','Анна','Мирина','Николаевна','1994-12-03','0987 878767','2021-12-02','8976678756','2 года','12');
/*!40000 ALTER TABLE `driverdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experience_ratio`
--

DROP TABLE IF EXISTS `experience_ratio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `experience_ratio` (
  `idexperience_ratio` int NOT NULL AUTO_INCREMENT,
  `Experience` varchar(45) NOT NULL,
  `16-21` double NOT NULL,
  `22-24` double NOT NULL,
  `25-29` double NOT NULL,
  `30-34` double NOT NULL,
  `35-39` double NOT NULL,
  `40-49` double NOT NULL,
  `50-59` double NOT NULL,
  `60` double NOT NULL,
  PRIMARY KEY (`idexperience_ratio`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experience_ratio`
--

LOCK TABLES `experience_ratio` WRITE;
/*!40000 ALTER TABLE `experience_ratio` DISABLE KEYS */;
INSERT INTO `experience_ratio` VALUES (1,'0 лет',1.87,1.77,1.77,1.63,1.63,1.63,1.63,1.6),(2,'1 год',1.87,1.77,1.69,1.63,1.63,1.63,1.63,1.6),(3,'2 года',1.87,1.77,1.63,1.63,1.63,1.63,1.63,1.6),(4,'3-4 года',1.66,1.04,1.04,1.04,0.99,0.96,0.96,0.93),(5,'5-6 лет',1.66,1.04,1.04,1.04,0.96,0.96,0.96,0.93),(6,'7-9 лет',1,1.04,1.04,1.01,0.96,0.96,0.96,0.93),(7,'10-14 лет',1,1,1.01,0.96,0.96,0.96,0.96,0.93),(8,'15 и более',1,1,1,0.96,0.96,0.96,0.96,0.93);
/*!40000 ALTER TABLE `experience_ratio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insurance_case`
--

DROP TABLE IF EXISTS `insurance_case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurance_case` (
  `idinsurance_case` int NOT NULL AUTO_INCREMENT,
  `driverLastName` varchar(45) NOT NULL,
  `driverFirstName` varchar(45) NOT NULL,
  `driverPatronymic` varchar(45) DEFAULT NULL,
  `driverPassport` varchar(45) NOT NULL,
  `payment` double NOT NULL,
  `caseDate` varchar(45) NOT NULL,
  `carNumber` varchar(45) NOT NULL,
  `CaseDescription` varchar(200) NOT NULL,
  PRIMARY KEY (`idinsurance_case`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurance_case`
--

LOCK TABLES `insurance_case` WRITE;
/*!40000 ALTER TABLE `insurance_case` DISABLE KEYS */;
INSERT INTO `insurance_case` VALUES (10,'Иванова','Ирина','Ивановна','1234 098787',30000,'2021-12-17','Н567ЕТ','Авария');
/*!40000 ALTER TABLE `insurance_case` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insurance_case_ratio`
--

DROP TABLE IF EXISTS `insurance_case_ratio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurance_case_ratio` (
  `idinsurance_case_ratio` int NOT NULL AUTO_INCREMENT,
  `rating` varchar(45) NOT NULL,
  `ratio` double NOT NULL,
  `0` varchar(45) NOT NULL,
  `1` varchar(45) NOT NULL,
  `2` varchar(45) NOT NULL,
  `3` varchar(45) NOT NULL,
  `От 4` varchar(45) NOT NULL,
  PRIMARY KEY (`idinsurance_case_ratio`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurance_case_ratio`
--

LOCK TABLES `insurance_case_ratio` WRITE;
/*!40000 ALTER TABLE `insurance_case_ratio` DISABLE KEYS */;
INSERT INTO `insurance_case_ratio` VALUES (1,'М',2.45,'0','М','М','М','М'),(2,'0',2.3,'1','М','М','М','М'),(3,'1',1.55,'2','М','М','М','М'),(4,'2',1.4,'3','1','М','М','М'),(5,'3',1,'4','1','М','М','М'),(6,'4',0.95,'5','2','1','М','М'),(7,'5',0.9,'6','3','1','М','М'),(8,'6',0.85,'7','4','2','М','М'),(9,'7',0.8,'8','4','2','М','М'),(10,'8',0.75,'9','5','2','М','М'),(11,'9',0.7,'10','5','2','1','М'),(12,'10',0.65,'11','6','3','1','М'),(13,'11',0.6,'12','6','3','1','М'),(14,'12',0.55,'13','6','3','1','М'),(15,'13',0.44,'13','7','3','1','М');
/*!40000 ALTER TABLE `insurance_case_ratio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insurance_policy`
--

DROP TABLE IF EXISTS `insurance_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurance_policy` (
  `idinsurance_policy` int NOT NULL AUTO_INCREMENT,
  `insurerID` int NOT NULL,
  `carID` int NOT NULL,
  `baseRate` double NOT NULL,
  `powerRatio` double NOT NULL,
  `countDriversRatio` double NOT NULL,
  `periodRatio` double NOT NULL,
  `experienceRatio` double NOT NULL,
  `safeDriveRatio` double NOT NULL,
  `regionRatio` double NOT NULL,
  `totalPrice` double NOT NULL,
  `date` varchar(45) NOT NULL,
  PRIMARY KEY (`idinsurance_policy`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurance_policy`
--

LOCK TABLES `insurance_policy` WRITE;
/*!40000 ALTER TABLE `insurance_policy` DISABLE KEYS */;
INSERT INTO `insurance_policy` VALUES (11,28,64,3500,1.2,1,1,1.63,1,2,13692,'2021-12-16'),(12,29,65,3500,1.2,1,1,1.63,1,2,13692,'2021-12-16');
/*!40000 ALTER TABLE `insurance_policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insurerdata`
--

DROP TABLE IF EXISTS `insurerdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurerdata` (
  `idinsurerData` int NOT NULL AUTO_INCREMENT,
  `idcar` int NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `patronymic` varchar(45) DEFAULT NULL,
  `birthDate` varchar(45) NOT NULL,
  `passport` varchar(45) NOT NULL,
  `passportDate` varchar(45) NOT NULL,
  `passportDepartment` varchar(90) NOT NULL,
  PRIMARY KEY (`idinsurerData`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurerdata`
--

LOCK TABLES `insurerdata` WRITE;
/*!40000 ALTER TABLE `insurerdata` DISABLE KEYS */;
INSERT INTO `insurerdata` VALUES (28,64,'Анна','Никитина','Борисовна','1987-01-02','1890 785676','2021-11-29','Отдел МВД'),(29,65,'Иван','Чепхих','Иванович','1991-10-31','8978 676556','2021-12-03','Отдел МВД');
/*!40000 ALTER TABLE `insurerdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region_ratio`
--

DROP TABLE IF EXISTS `region_ratio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region_ratio` (
  `idregion_ratio` int NOT NULL AUTO_INCREMENT,
  `region` varchar(60) NOT NULL,
  `ratio` double NOT NULL,
  PRIMARY KEY (`idregion_ratio`)
) ENGINE=InnoDB AUTO_INCREMENT=319 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region_ratio`
--

LOCK TABLES `region_ratio` WRITE;
/*!40000 ALTER TABLE `region_ratio` DISABLE KEYS */;
INSERT INTO `region_ratio` VALUES (1,'Москва',2),(2,'Санкт-Петербург',1.8),(3,'Абакан, Саяногорск, Черногорск',1),(4,'Архангельск',1.8),(5,'Астрахань',1.7),(6,'Байконур',1),(7,'Барнаул',1.7),(8,'Белгород',1.3),(9,'Биробиджан',0.6),(10,'Благовещенск',1.7),(11,'Брянск',1.5),(12,'Буйнакск, Дербент, Каспийск, Махачкала, Хасавюрт',0.7),(170,'Великий Новгород',1.3),(171,'Владивосток',1.4),(172,'Владикавказ',1),(173,'Владимир',1.6),(174,'Волгоград',1.3),(175,'Вологда',1.7),(176,'Воронеж',1.5),(177,'Горно-Алтайск',1.3),(178,'Екатеринбург',1.8),(179,'Иваново',1.8),(180,'Ижевск',1.6),(181,'Иркутск',1.7),(182,'Йошкар-Ола',1.4),(183,'Казань',2),(184,'Калининград',1.1),(185,'Калуга',1.2),(186,'Карачаево-Черкесская Республика',1),(187,'Кемерово',1.9),(188,'Киров',1.4),(189,'Кисловодск, Михайловск, Ставрополь',1.2),(190,'Кострома',1.3),(191,'Краснодар',1.8),(192,'Красноярск',1.8),(193,'Курган',1.4),(194,'Курск',1.2),(195,'Кызыл',0.6),(196,'Ленинградская область',1.3),(197,'Липецк',1.3),(198,'Магадан',0.7),(199,'Московская область',1.7),(200,'Мурманск',2.2),(201,'Назрань',0.6),(202,'Нальчик, Прохладный',1),(203,'Ненецкий автономный округ',0.8),(204,'Нижний Новгород',1.8),(205,'Новороссийск',1.8),(206,'Новосибирск',1.7),(207,'Омск',1.6),(208,'Орел',1.2),(209,'Оренбург',1.7),(210,'Остальная Амурская область',1),(211,'Остальная Архангельская область',0.85),(212,'Остальная Астраханская область',0.8),(213,'Остальная Белгородская область',0.8),(214,'Остальная Брянская область',0.7),(215,'Остальная Владимирская область',1),(216,'Остальная Волгоградская область',0.7),(217,'Остальная Вологодская область',0.9),(218,'Остальная Воронежская область',0.8),(219,'Остальная Еврейская автономная область',0.6),(220,'Остальная Ивановская область',0.9),(221,'Остальная Ингушетия',0.6),(222,'Остальная Иркутская область',0.8),(223,'Остальная Кабардино-Балкария',0.7),(224,'Остальная Калининградская область',0.8),(225,'Остальная Калмыкия',0.6),(226,'Остальная Калужская область',0.9),(227,'Остальная Карелия',0.8),(228,'Остальная Кемеровская область',1.1),(229,'Остальная Кировская область',0.8),(230,'Остальная Коми',1),(231,'Остальная Костромская область',0.7),(232,'Остальная Курганская область',0.6),(233,'Остальная Курская область',0.7),(234,'Остальная Липецкая область',0.8),(235,'Остальная Магаданская область',0.6),(236,'Остальная Мордовия',0.8),(237,'Остальная Мурманская область',1.2),(238,'Остальная Нижегородская область',1),(239,'Остальная Новгородская область',0.9),(240,'Остальная Новосибирская область',0.9),(241,'Остальная Омская область',0.9),(242,'Остальная Оренбургская область',0.8),(243,'Остальная Орловская область',0.7),(244,'Остальная Пензенская область',0.7),(245,'Остальная Псковская область',0.7),(246,'Остальная Республика Бурятия',0.6),(247,'Остальная Республика Марий Эл',0.7),(248,'Остальная Ростовская область',0.8),(249,'Остальная Рязанская область',0.9),(250,'Остальная Самарская область',0.9),(251,'Остальная Саратовская область',0.7),(252,'Остальная Сахалинская область',0.9),(253,'Остальная Свердловская область',1),(254,'Остальная Северной Осетия',0.8),(255,'Остальная Смоленская область',0.7),(256,'Остальная Тамбовская область',0.8),(257,'Остальная Тверская область',0.8),(258,'Остальная Томская область',0.9),(259,'Остальная Тульская область',0.9),(260,'Остальная Тыва',0.6),(261,'Остальная Тюменская область',1.1),(262,'Остальная Удмуртия',0.8),(263,'Остальная Ульяновская область',0.9),(264,'Остальная Хакасия',0.6),(265,'Остальная Челябинская область',1),(266,'Остальная Чувашия',0.8),(267,'Остальная Якутия',0.6),(268,'Остальная Ярославская область',0.9),(269,'Остальной Алтай',0.7),(270,'Остальной Алтайский край',0.7),(271,'Остальной Башкортостан',1),(272,'Остальной Дагестан',0.6),(273,'Остальной Забайкальский край',0.6),(274,'Остальной Камчатский край',1),(275,'Остальной Краснодарский край',1),(276,'Остальной Красноярский край',0.9),(277,'Остальной Крым',0.6),(278,'Остальной Пермский край',1.1),(279,'Остальной Приморский край',0.7),(280,'Остальной Ставропольский край',0.7),(281,'Остальной Татарстан',1.1),(282,'Остальной Хабаровский край',0.8),(283,'Остальной ХМАО',1.1),(284,'Пенза',1.4),(285,'Пермь',2),(286,'Петрозаводск',1.3),(287,'Петропавловск-Камчатский',1.3),(288,'Псков',1.2),(289,'Республика Адыгея',1.3),(290,'Ростов-на-Дону',1.8),(291,'Рязань',1.4),(292,'Самара',1.6),(293,'Саранск',1.5),(294,'Саратов',1.6),(295,'Севастополь',0.6),(296,'Симферополь',0.6),(297,'Смоленск',1.2),(298,'Сыктывкар',1.6),(299,'Тамбов',1.2),(300,'Тверь',1.5),(301,'Томск',1.6),(302,'Тула',1.5),(303,'Тюмень',2),(304,'Улан-Удэ',1.3),(305,'Ульяновск',1.5),(306,'Уфа',1.8),(307,'Хабаровск',1.7),(308,'Ханты-Мансийск',1.5),(309,'Чебоксары',1.7),(310,'Челябинск',2.2),(311,'Чеченская Республика',0.6),(312,'Чита',0.7),(313,'Чукотский АО',0.6),(314,'Элиста',1.3),(315,'Южно-Сахалинск',1.5),(316,'Якутск',1.2),(317,'Ямало-Ненецкий АО',1.1),(318,'Ярославль',1.5);
/*!40000 ALTER TABLE `region_ratio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sosago'
--

--
-- Dumping routines for database 'sosago'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-23 19:58:14
