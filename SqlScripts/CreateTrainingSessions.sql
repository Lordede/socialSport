CREATE TABLE trainingsessions(
	id int NOT NULL AUTO_INCREMENT,
	creationDate datetime NOT NULL,
	trainingId int NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY trainingId REFERENCES trainings(id)
)