CREATE Table exercises(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    musclegroup varchar(255),
    trainingId int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (trainingId) REFERENCES trainings(id)
   )