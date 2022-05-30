CREATE Table exercises(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    muscleGroup varchar(255),
    exerciseImage MEDIUMBLOB,
    PRIMARY KEY (id)
   )