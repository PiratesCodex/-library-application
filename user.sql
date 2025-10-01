/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 8.0.27 : Database - library_application
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`library_application` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `library_application`;

/*Table structure for table `books` */

DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
  `book_id` bigint NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` enum('AVAILABLE','TAKEN') DEFAULT NULL,
  `taken_by_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `FK8iowld7kmcwmj8gvdbfyoyy7s` (`taken_by_user_id`),
  CONSTRAINT `FK8iowld7kmcwmj8gvdbfyoyy7s` FOREIGN KEY (`taken_by_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `books` */

insert  into `books`(`book_id`,`author`,`category`,`due_date`,`name`,`status`,`taken_by_user_id`) values 
(1,'Robert C. Martin','Programming','2026-08-01','Clean Code','TAKEN',NULL),
(2,'James Gosling','Programming',NULL,'Java Fundamentals','AVAILABLE',NULL),
(3,'Robert C. Martin','Programming','2025-10-05','Clean Code','TAKEN',1),
(4,'Thorben Janssen','Programming',NULL,'Hibernate Tips','AVAILABLE',NULL);

/*Table structure for table `membership` */

DROP TABLE IF EXISTS `membership`;

CREATE TABLE `membership` (
  `membership_id` bigint NOT NULL AUTO_INCREMENT,
  `membership_months` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`membership_id`),
  UNIQUE KEY `UK7ax6kj56u4dbtiqmmec1vubqb` (`user_id`),
  CONSTRAINT `FK7ccx5hs0p61m5wrwywf0q2cdv` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `membership` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `membership_end_date` date DEFAULT NULL,
  `membership_start_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_type` enum('GUEST','LOGGEDIN') DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`user_id`,`email`,`membership_end_date`,`membership_start_date`,`name`,`password`,`user_type`) values 
(1,'sagar@gmail.com','2026-12-26','2025-08-01','Sagar','$2a$10$KxqJrErzKU3f6oBk0xvm6uQvwxcLphcrzSPT4rLIzOc3fTAMPyidK','LOGGEDIN');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
