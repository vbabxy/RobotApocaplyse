

DROP TABLE IF EXISTS `infection_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `infection_report` (
                                    `id` bigint NOT NULL AUTO_INCREMENT,
                                    `reporter` bigint DEFAULT NULL,
                                    `reported` bigint DEFAULT NULL,
                                    PRIMARY KEY (`id`),
                                    KEY `fk_survior_reporter_id_idx` (`reporter`),
                                    KEY `fk_survivor_reported_id_idx` (`reported`),
                                    CONSTRAINT `fk_survior_reporter_id` FOREIGN KEY (`reporter`) REFERENCES `survivor` (`id`),
                                    CONSTRAINT `fk_survivor_reported_id` FOREIGN KEY (`reported`) REFERENCES `survivor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `survivor_id` bigint DEFAULT NULL,
                             `has_water` tinyint DEFAULT NULL,
                             `has_food` tinyint DEFAULT NULL,
                             `has_medication` tinyint DEFAULT NULL,
                             `has_ammunition` tinyint DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `fk_survivor_id_idx` (`survivor_id`),
                             CONSTRAINT `fk_survivor_id` FOREIGN KEY (`survivor_id`) REFERENCES `survivor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `latitude` decimal(18,2) DEFAULT NULL,
                            `longitude` decimal(18,2) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,10.30,10.83),(2,67.30,16.83);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survivor`
--

DROP TABLE IF EXISTS `survivor`;

CREATE TABLE `survivor` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `name` varchar(100) DEFAULT NULL,
                            `age` bigint DEFAULT NULL,
                            `gender` varchar(10) DEFAULT NULL,
                            `is_infected` tinyint DEFAULT NULL,
                            `location_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `fk_location_id_key_idx` (`location_id`),
                            CONSTRAINT `fk_location_id_key` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


