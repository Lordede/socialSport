CREATE TABLE favoriteExercises(
	id int NOT NULL AUTO_INCREMENT,
	exerciseId int NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (exerciseId) REFERENCES exercises(id)
)