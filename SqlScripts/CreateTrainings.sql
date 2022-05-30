CREATE Table trainings(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    points decimal(10,2) Not Null,
    trainingsplanId int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (trainingsplanId) REFERENCES trainingsplan(id)
   )