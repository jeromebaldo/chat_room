CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users (username, email, password) VALUES ('norbert', 'norbert@example.com', 'password123');
INSERT INTO users (username, email, password) VALUES ('nicolas', 'nicolas@example.com', 'securepassword');
INSERT INTO users (username, email, password) VALUES ('stéphanie', 'stephanie@example.com', 'mypassword');
INSERT INTO users (username, email, password) VALUES ('audrey', 'audrey@example.com', 'audreypass');