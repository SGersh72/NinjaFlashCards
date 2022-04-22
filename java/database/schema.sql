BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, deck, card, user_deck_history;

CREATE TABLE users (
	user_id serial NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE deck (
	deck_id serial NOT NULL,
	deck_status int NOT NULL, /* 1 private is default 2 pending or 3 approved */
	deck_name varchar NOT NULL,
	deck_desc varchar NOT NULL,
	owner_id int NOT NULL,
	genre varchar NOT NULL, /*hardcoded 5-10 subjects in vue */
	CONSTRAINT pk_deck_id PRIMARY KEY (deck_id),
	CONSTRAINT fk_owner_id FOREIGN KEY (owner_id) REFERENCES users(user_id)
);

CREATE TABLE card (
	card_id serial NOT NULL,
	question varchar NOT NULL,
	answer varchar NOT NULL,
	image_url varchar,
	deck_id int NOT NULL,
	/* order_num/index_num */
	CONSTRAINT pk_card_id PRIMARY KEY (card_id),
	CONSTRAINT fk_deck_id FOREIGN KEY (deck_id) REFERENCES deck(deck_id)
);

CREATE TABLE user_deck_history (
	user_id int NOT NULL,
	deck_id int NOT NULL,
	score int NOT NULL, /* 1 red, 2 yellow, 3 green */
	CONSTRAINT pk_user_deck_id PRIMARY KEY (user_id, deck_id),
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT fk_deck_id FOREIGN KEY (deck_id) REFERENCES deck(deck_id)
 );

COMMIT TRANSACTION;

