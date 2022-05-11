-- comments schema

-- !Ups

CREATE TABLE comments (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(20) NOT NULL,
    title varchar(255) NOT NULL,
    comment varchar(255) NOT NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL,
    PRIMARY KEY (id)
);

-- !Downs

DROP TABLE comments;