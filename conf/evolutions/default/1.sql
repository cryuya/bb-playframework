-- comments schema

-- !Ups
CREATE TABLE users (
	id int NOT NULL AUTO_INCREMENT,
	name varchar(20) NOT NULL,
	password varchar(30) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO users VALUES 
	(DEFAULT, 'user1', 'password'),
	(DEFAULT, 'user2', 'password'),
	(DEFAULT, 'user3', 'password')
;

CREATE TABLE comments (
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	name varchar(20) NOT NULL,
	title varchar(30) NOT NULL,
	comment varchar(255) NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY(user_id),
    REFERENCES users(id)
);

INSERT INTO comments VALUES 
	(DEFAULT, 1, 'user1', 'かめかめ', 'いるかいるか', CURRENT_TIME, CURRENT_TIME),
	(DEFAULT, 2, 'user2', 'かめかめかめ', 'いるか　かもめ　いるか', CURRENT_TIME, CURRENT_TIME),
	(DEFAULT, 2, 'user2', 'たぬきぺんぎん', 'ねこいぬいぬいぬ', CURRENT_TIME, CURRENT_TIME),
	(DEFAULT, 2, 'user2', 'ねこぺんぎんねこ', 'ねこねこねこねこ', CURRENT_TIME, CURRENT_TIME),
	(DEFAULT, 3, 'user3', 'かめいぬ', 'いぬかめかかめめ', CURRENT_TIME, CURRENT_TIME)
;

-- !Downs

DROP TABLE comments;
DROP TABLE users;