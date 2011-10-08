CREATE USER 'testuser'@'localhost' IDENTIFIED BY '123abc';
GRANT SELECT, INSERT, UPDATE, DELETE ON SAKILA.* TO 'testuser'@'localhost';
GRANT SELECT ON mysql.proc TO 'testuser'@'localhost';

SHOW GRANTS FOR 'testuser'@'localhost';