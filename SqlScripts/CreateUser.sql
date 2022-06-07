CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email varchar(255) UNIQUE,
    username varchar(255) UNIQUE,
    firstname varchar(255),
    lastname varchar(255),
    pwd varchar(255),
    isAdmin boolean DEFAULT FALSE,
    profilePicture MEDIUMBLOB
);
