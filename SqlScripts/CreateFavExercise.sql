CREATE TABLE favoriteExercises(
	id int NOT NULL AUTO_INCREMENT,
	exerciseId int NOT NULL,
	userId int NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (exerciseId) REFERENCES exercises(id),
	FOREIGN KEY (userId) REFERENCES users(id)
)