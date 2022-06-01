CREATE Table exercises(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    musclegroupId int,
    trainingId int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (trainingId) REFERENCES trainings(id),
    FOREIGN KEY (musclegroupId) REFERENCES musclegroups(id)
   )