CREATE TABLE exercisesToTrainings(
    exerciseId int NOT NULL,
    trainingId int NOT NULL,
    FOREIGN KEY (exerciseId) REFERENCES exercises(id),
    FOREIGN KEY (trainingId) REFERENCES trainings(id)
    );