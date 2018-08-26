DROP DATABASE `portfolio`;
--
CREATE DATABASE `portfolio` /*!40100 DEFAULT CHARACTER SET utf8 */;
--
CREATE TABLE `portfolio`.`portfolio` (
  `portfolio_id`     int(11)     NOT NULL AUTO_INCREMENT 
                                 COMMENT 'Unique ID (internal) for this portfolio',
  `portfolio_name`   varchar(45) NOT NULL 
                                 COMMENT 'Name that a client has given to this portfolio',
  `client_id`        int(11)     NOT NULL 
                                 COMMENT 'ID of client holding or tracking this portfolio',
  `avatar_id`        int(11)     DEFAULT  NULL
                                 COMMENT 'Unique ID (internal) for an avatar',
  `creation_ts`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 
                                 COMMENT 'Timestamp applied when this row is created',
  `creation_user_id` int(11)     NOT NULL  
                                 COMMENT 'Unique ID (internal) for the user who created this row',
  `revision_ts`      timestamp    
                                 COMMENT 'Timestamp applied each time this row is revised',
  `revision_user_id` int(11)     DEFAULT  NULL
                                 COMMENT 'Unique ID (internal) for the user who created this row',
  PRIMARY KEY (`portfolio_id`),
  UNIQUE KEY `portfolio_id_UNIQUE` (`portfolio_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 
  COMMENT='Portfolios held or tracked by clients	'
  ;
CREATE TABLE `portfolio`.`security` (
  `security_id`      int(11)     NOT NULL AUTO_INCREMENT 
                                 COMMENT 'Unique ID (internal) for this portfolio security',
  `portfolio_id`     int(11)     NOT NULL  
                                 COMMENT 'Unique ID (internal) for a portfolio',
  `security_symbol`  varchar(9)  NOT NULL 
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
  `revision_user_id` int(11)     DEFAULT  NULL 
                                 COMMENT 'Unique ID (internal) for the user who created this row',
  PRIMARY KEY (`security_id`),
--  FOREIGN KEY (`portfolio_id`) 
--  REFERENCES   `portfolio`.`portfolio` (`portfolio_id`)
--  UNIQUE INDEX   `ix_portfolio_symbol` (`portfolio_id` ASC, `security_symbol` ASC),
  CONSTRAINT     `fk_portfolio_id` 
    FOREIGN KEY (`portfolio_id`) 
    REFERENCES   `portfolio`.`portfolio` (`portfolio_id`) 
--    ON DELETE CASCADE 
--    ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 
  COMMENT='Securities held in a portfolio'
  ;
-- 
