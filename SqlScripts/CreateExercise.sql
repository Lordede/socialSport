CREATE Table exercises(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    musclegroup varchar(255),
    trainingId int NOT NULL,
    exerciseImage MEDIUMBLOB,
    PRIMARY KEY (id),
   )