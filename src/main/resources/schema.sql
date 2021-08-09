DROP TABLE IF EXISTS mensauser;
CREATE TABLE mensauser (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    matricola int(128) UNIQUE,
    password VARCHAR(256),
    enabled VARCHAR(256)
);


DROP TABLE IF EXISTS tuser;
CREATE TABLE tuser (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    matricola int(128) UNIQUE,
    password VARCHAR(256),
    enabled VARCHAR(256)
);
