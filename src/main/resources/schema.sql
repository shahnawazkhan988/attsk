DROP TABLE IF EXISTS UsersDto;
CREATE TABLE UsersDto (
    Id INT AUTO_INCREMENT  PRIMARY KEY,
    userName VARCHAR(256),
    userMatricola VARCHAR(256),
    userPass VARCHAR(256),
    userRole VARCHAR(256)
);
