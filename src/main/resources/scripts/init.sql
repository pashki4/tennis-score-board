CREATE TABLE players
(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE matches
(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    player1 INTEGER,
    player2 INTEGER,
    winner INTEGER,
    FOREIGN KEY (player1) REFERENCES players (id),
    FOREIGN KEY (player2) REFERENCES players (id),
    FOREIGN KEY (winner) REFERENCES players (id)
);

INSERT INTO players (name) VALUES ('Rafael Nadal');
INSERT INTO players (name) VALUES ('Roger Federer');
INSERT INTO matches (player1, player2, winner) VALUES (1, 2, 2);