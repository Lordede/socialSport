CREATE Table exercises(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    muscleGroup varchar(255),
    exerciseImage MEDIUMBLOB,
    filename varchar(255),
    creationDate datetime NOT NULL,
    PRIMARY KEY (id)
   )
