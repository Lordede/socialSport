CREATE Table trainings(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    points decimal(10,2) Not Null,
    userId int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES users(id)
   )