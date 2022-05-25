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

CREATE TABLE users (
	id int NOT NULL AUTO_INCREMENT,
	name varchar(20) NOT NULL,
	password varchar(30) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO comments VALUES (DEFAULT, 'firstname', 'firstplay', 'firstcomment', CURRENT_TIME, CURRENT_TIME);
INSERT INTO users VALUES (DEFAULT, 'name', 'password');

!Downs

DROP TABLE comments;
DROP TABLE users;