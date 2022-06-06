CREATE TABLE sets(
    id int NOT NULL AUTO_INCREMENT,
    kg decimal NOT NULL,
    rep int NOT NULL,
    exerciseId int NOT NULL,
    trainingId int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (exerciseId) REFERENCES exercises(id),
    FOREIGN KEY (trainingId) REFERENCES trainings(id)
    )