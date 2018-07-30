create user 'portfolioapp'@'localhost' identified by 'derPassword';
--
grant select 
   on enterprise.* 
   to 'portfolioapp'@'localhost';
grant select 
   on quote.*      
   to 'portfolioapp'@'localhost';
--
grant select, insert, update, delete 
   on portfolio.*  
   to 'portfolioapp'@'localhost';
