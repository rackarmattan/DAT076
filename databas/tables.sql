DROP OWNED BY rackarmattan CASCADE;

CREATE TABLE Fruits(
	name VARCHAR(20) NOT NULL,
	color VARCHAR(20) NOT NULL,
	kind VARCHAR(20) NOT NULL,
	origin VARCHAR(30) NOT NULL,
	PRIMARY KEY(name)
);

CREATE TABLE Users(
	name VARCHAR(20) NOT NULL,
	password VARCHAR(20) NOT NULL,
	email VARCHAR(20) NOT NULL UNIQUE,
	PRIMARY KEY(name)
);

	