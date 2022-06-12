CREATE TABLE `paste` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime(6) DEFAULT NULL,
  `expiration_length_in_minutes` int DEFAULT NULL,
  `paste_path` varchar(255) DEFAULT NULL,
  `short_link` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_short_link` (`short_link`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;