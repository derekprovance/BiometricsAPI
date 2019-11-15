--
-- Table structure for table `blood_sugar`
--

DROP TABLE IF EXISTS `blood_sugar`;
CREATE TABLE `blood_sugar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  `mg_dL` int(11) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Table structure for table `connected_api_access`
--

DROP TABLE IF EXISTS `connected_api_access`;
CREATE TABLE `connected_api_access` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `token` varchar(500) NOT NULL,
  `api` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Table structure for table `daily_log`
--

DROP TABLE IF EXISTS `daily_log`;
CREATE TABLE `daily_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `log_entry` text NOT NULL,
  `day_rating` int(11) NOT NULL,
  `productivity_rating` int(11) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Table structure for table `daily_statistics`
--

DROP TABLE IF EXISTS `daily_statistics`;
CREATE TABLE `daily_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `max_hr` int(11) DEFAULT NULL,
  `min_hr` int(11) DEFAULT NULL,
  `resting_hr` int(11) DEFAULT NULL,
  `total_steps` int(11) DEFAULT NULL,
  `highly_active_seconds` int(11) DEFAULT NULL,
  `active_seconds` int(11) DEFAULT NULL,
  `sedentary_seconds` int(11) DEFAULT NULL,
  `sleeping_seconds` int(11) DEFAULT NULL,
  `max_stress_level` int(11) DEFAULT NULL,
  `low_stress_duration` int(11) DEFAULT NULL,
  `medium_stress_duration` int(11) DEFAULT NULL,
  `high_stress_duration` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `entry_date` (`date`)
) ENGINE=InnoDB AUTO_INCREMENT=1135 DEFAULT CHARSET=latin1;

--
-- Table structure for table `heart_rate`
--

DROP TABLE IF EXISTS `heart_rate`;
CREATE TABLE `heart_rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datetime` datetime DEFAULT NULL,
  `hr_value` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=497156 DEFAULT CHARSET=latin1;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `meal_log`
--

DROP TABLE IF EXISTS `meal_log`;
CREATE TABLE `meal_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `calories` int(11) DEFAULT NULL,
  `carbs` float DEFAULT NULL,
  `date` varchar(255) NOT NULL,
  `fat` float DEFAULT NULL,
  `fiber` float DEFAULT NULL,
  `log_id` bigint(20) NOT NULL,
  `meal_type_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `protein` float DEFAULT NULL,
  `sodium` float DEFAULT NULL,
  `unit` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7ajn5t4mc05qoybc41s0g24ck` (`log_id`)
) ENGINE=MyISAM AUTO_INCREMENT=165 DEFAULT CHARSET=latin1;

--
-- Table structure for table `medical_log`
--

DROP TABLE IF EXISTS `medical_log`;
CREATE TABLE `medical_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `date_changed` date DEFAULT NULL,
  `sno_med_ct_code` varchar(45) DEFAULT NULL,
  `icd_code` varchar(45) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `updated` datetime NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Table structure for table `movement_data`
--

DROP TABLE IF EXISTS `movement_data`;
CREATE TABLE `movement_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datetime` datetime DEFAULT NULL,
  `movement` decimal(5,4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1231799 DEFAULT CHARSET=latin1;

--
-- Table structure for table `sleep`
--

DROP TABLE IF EXISTS `sleep`;
CREATE TABLE `sleep` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sleep_start` datetime DEFAULT NULL,
  `sleep_end` datetime DEFAULT NULL,
  `deep_sleep` int(11) DEFAULT NULL,
  `light_sleep` int(11) DEFAULT NULL,
  `rem_sleep` int(11) DEFAULT NULL,
  `awake_sleep` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1042 DEFAULT CHARSET=latin1;

--
-- Table structure for table `sleep_movement`
--

DROP TABLE IF EXISTS `sleep_movement`;
CREATE TABLE `sleep_movement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `activity_level` decimal(14,13) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=415838 DEFAULT CHARSET=latin1;

--
-- Table structure for table `symptom`
--

DROP TABLE IF EXISTS `symptom`;
CREATE TABLE `symptom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `location` varchar(45) DEFAULT NULL,
  `severity` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `medical_log_id` int(11) NOT NULL,
  `datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk1_idx` (`medical_log_id`),
  CONSTRAINT `fk1` FOREIGN KEY (`medical_log_id`) REFERENCES `medical_log` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Table structure for table `water_consumption`
--

DROP TABLE IF EXISTS `water_consumption`;
CREATE TABLE `water_consumption` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `date` date NOT NULL,
  `unit` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5yorc53t3ff6gchr2rrqf7f5p` (`date`)
) ENGINE=MyISAM AUTO_INCREMENT=93 DEFAULT CHARSET=latin1;
