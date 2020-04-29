CREATE DATABASE curso_spring;
CREATE USER 'robot'@'localhost' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON * . * TO 'robot'@'localhost';
FLUSH PRIVILEGES;