-- comments schema

-- !Ups

CREATE TABLE comments (
	id int NOT NULL AUTO_INCREMENT,
	name varchar(20) NOT NULL,
	title varchar(30) NOT NULL,
	comment varchar(255) NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO comments VALUES (DEFAULT, 'firstname', 'firstplay', 'firstcomment', CURRENT_TIME, CURRENT_TIME);

-- !Downs

DROP TABLE comments;