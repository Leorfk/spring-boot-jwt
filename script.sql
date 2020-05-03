CREATE DATABASE curso_spring;
CREATE USER 'robot'@'localhost' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON * . * TO 'robot'@'localhost';
FLUSH PRIVILEGES;
-- comando para gerar backup do banco de dados local
mysqldump -u <user> -p <dbname> > arquivo.sql
