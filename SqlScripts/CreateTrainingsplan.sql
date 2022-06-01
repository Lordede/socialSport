CREATE Table trainingsplan(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    userId int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES users(id)
   )
   