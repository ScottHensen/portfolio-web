DROP DATABASE `enterprise`;
--
CREATE DATABASE `enterprise` /*!40100 DEFAULT CHARACTER SET utf8 */;
--
CREATE TABLE `enterprise`.`clients` (
  `client_id`        int(11)     NOT NULL AUTO_INCREMENT 
                                 COMMENT 'Unique ID (internal) for a client',
  `user_id`          int(11)     NOT NULL  
                                 COMMENT 'Unique ID (internal) for a user',
  `first_name`       varchar(45) DEFAULT NULL 
                                 COMMENT 'The client''s first name',
  `middle_name`      varchar(45) DEFAULT NULL 
                                 COMMENT 'The client''s middle name',
  `last_name`        varchar(45) DEFAULT NULL 
                                 COMMENT 'The client''s last name',
  `legal_name`       varchar(45) NOT NULL 
                                 COMMENT 'The client''s legal name',
  `avatar_id`        int(11)       
                                 COMMENT 'Unique ID (internal) for an avatar',
  `creation_ts`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 
                                 COMMENT 'Timestamp applied when this row is created',
  `creation_user_id` int(11)     NOT NULL  
                                 COMMENT 'Unique ID (internal) for the user who created this row',
  `revision_ts`      timestamp    
                                 COMMENT 'Timestamp applied each time this row is revised',
  `revision_user_id` int(11)      
                                 COMMENT 'Unique ID (internal) for the user who created this row',
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `client_id_UNIQUE` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 
 COMMENT='Contains Enterprise clients';
--
CREATE TABLE `enterprise`.`users` (
  `user_id`          int(11)     NOT NULL AUTO_INCREMENT 
                                 COMMENT 'Unique ID (internal) for a user',
  `user_name`        varchar(45) DEFAULT NULL 
                                 COMMENT 'The user''s first name',
  `creation_ts`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 
                                 COMMENT 'Timestamp applied when this row is created',
  `creation_user_id` int(11)     NOT NULL  
                                 COMMENT 'Unique ID (internal) for the user who created this row',
  `revision_ts`      timestamp    
                                 COMMENT 'Timestamp applied each time this row is revised',
  `revision_user_id` int(11)      
                                 COMMENT 'Unique ID (internal) for the user who created this row',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 
 COMMENT='Contains Enterprise Users';
--
CREATE TABLE `enterprise`.`avatars` (
  `avatar_id`        int(11)     NOT NULL AUTO_INCREMENT 
                                 COMMENT 'Unique ID (internal) for an avatar',
  `avatar`           LONGBLOB    NOT NULL 
                                 COMMENT 'An image file used for visual representation of an object', 
  `creation_ts`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 
                                 COMMENT 'Timestamp applied when this row is created',
  `creation_user_id` int(11)     NOT NULL  
                                 COMMENT 'Unique ID (internal) for the user who created this row',
  `revision_ts`      timestamp    
                                 COMMENT 'Timestamp applied each time this row is revised',
  `revision_user_id` int(11)      
                                 COMMENT 'Unique ID (internal) for the user who created this row',
  PRIMARY KEY (`avatar_id`),
  UNIQUE KEY `avatar_id_UNIQUE` (`avatar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 
 COMMENT='Contains Enterprise Avatars';

--
CREATE TABLE `enterprise`.`securities` (
  `security_id`      int(11)     NOT NULL AUTO_INCREMENT 
                                 COMMENT 'Unique ID (internal) for this security',
  `symbol_id`        varchar(9)  NOT NULL 
                                 COMMENT 'The symbol used to trade the security on the market',
  `security_name`    varchar(45) DEFAULT NULL 
                                 COMMENT 'The name of the security',
  `sector_name`      varchar(45) DEFAULT NULL 
                                 COMMENT 'The name of the market sector to which this security belongs',
  `creation_ts`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 
                                 COMMENT 'Timestamp applied when this row is created',
  `creation_user_id` int(11)     NOT NULL  
                                 COMMENT 'Unique ID (internal) for the user who created this row',
  `revision_ts`      timestamp    
                                 COMMENT 'Timestamp applied each time this row is revised',
  `revision_user_id` int(11)      
                                 COMMENT 'Unique ID (internal) for the user who created this row',
  PRIMARY KEY (`security_id`),
  UNIQUE KEY `security_id_UNIQUE` (`security_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 
  COMMENT='Securities traded on the equity market'
  ;
-- 
CREATE TABLE `enterprise`.`exchanges` (
  `exchange_id`      int(11)     NOT NULL AUTO_INCREMENT
                                 COMMENT 'Unique ID (internal) for this exchange',
  `exchange_name`    varchar(45) NOT NULL
                                 COMMENT 'The name of the exchange',
  `creation_ts`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 
                                 COMMENT 'Timestamp applied when this row is created',
  `creation_user_id` int(11)     NOT NULL  
                                 COMMENT 'Unique ID (internal) for the user who created this row',
  `revision_ts`      timestamp    
                                 COMMENT 'Timestamp applied each time this row is revised',
  `revision_user_id` int(11)      
                                 COMMENT 'Unique ID (internal) for the user who created this row',
  PRIMARY KEY (`exchange_id`),
  UNIQUE KEY `exchange_id_UNIQUE` (`exchange_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 
  COMMENT='Contains exchanges through which securities are traded'
  ;
