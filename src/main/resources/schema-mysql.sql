CREATE TABLE IF NOT EXISTS users(
    Id bigint NOT NULL PRIMARY KEY,
    userName VARCHAR(256),
    userMatricola VARCHAR(256),
    userPass VARCHAR(256),
    userRole VARCHAR(256)
);
