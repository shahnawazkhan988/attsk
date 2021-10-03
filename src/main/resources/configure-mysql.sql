# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE attsk_dev;
CREATE DATABASE attsk_prod;

#Create database service accounts
CREATE USER 'attsk_dev_user'@'localhost' IDENTIFIED BY 'attsk1';
CREATE USER 'attsk_prod_user'@'localhost' IDENTIFIED BY 'attsk1';
CREATE USER 'attsk_dev_user'@'%' IDENTIFIED BY 'attsk1';
CREATE USER 'attsk_prod_user'@'%' IDENTIFIED BY 'attsk1';

#Database grants
GRANT SELECT ON attsk_dev.* to 'attsk_dev_user'@'localhost';
GRANT INSERT ON attsk_dev.* to 'attsk_dev_user'@'localhost';
GRANT DELETE ON attsk_dev.* to 'attsk_dev_user'@'localhost';
GRANT UPDATE ON attsk_dev.* to 'attsk_dev_user'@'localhost';
GRANT SELECT ON attsk_prod.* to 'attsk_prod_user'@'localhost';
GRANT INSERT ON attsk_prod.* to 'attsk_prod_user'@'localhost';
GRANT DELETE ON attsk_prod.* to 'attsk_prod_user'@'localhost';
GRANT UPDATE ON attsk_prod.* to 'attsk_prod_user'@'localhost';
GRANT SELECT ON attsk_dev.* to 'attsk_dev_user'@'%';
GRANT INSERT ON attsk_dev.* to 'attsk_dev_user'@'%';
GRANT DELETE ON attsk_dev.* to 'attsk_dev_user'@'%';
GRANT UPDATE ON attsk_dev.* to 'attsk_dev_user'@'%';
GRANT SELECT ON attsk_prod.* to 'attsk_prod_user'@'%';
GRANT INSERT ON attsk_prod.* to 'attsk_prod_user'@'%';
GRANT DELETE ON attsk_prod.* to 'attsk_prod_user'@'%';
GRANT UPDATE ON attsk_prod.* to 'attsk_prod_user'@'%';
