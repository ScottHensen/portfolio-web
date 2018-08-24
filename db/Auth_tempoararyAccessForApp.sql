create user 'portfolioapp'@'localhost' identified by 'derPassword';
--
grant select 
   on enterprise.* 
   to 'portfolioapp'@'localhost';
grant select 
   on quote.*      
   to 'portfolioapp'@'localhost';
--
-- grant select, insert, update, delete 
grant all	-- TEMPORARY; SO I CAN USE AUTO-DDL
   on portfolio.*  
   to 'portfolioapp'@'localhost';
