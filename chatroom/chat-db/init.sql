CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);

INSERT INTO users (username, email) VALUES ('norbert', 'norbert@example.com');
INSERT INTO users (username, email) VALUES ('nicolas', 'nicolas@example.com');
INSERT INTO users (username, email) VALUES ('stéphanie', 'stephanie@example.com');
INSERT INTO users (username, email) VALUES ('audrey', 'audrey@example.com');