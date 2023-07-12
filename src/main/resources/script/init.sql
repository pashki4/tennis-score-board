CREATE TABLE IF NOT EXISTS players
(
    id   BIGINT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT players_pk PRIMARY KEY (id),
    CONSTRAINT players_uq UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS matches
(
    id      BIGINT AUTO_INCREMENT,
    player1 BIGINT NOT NULL,
    player2 BIGINT NOT NULL,
    winner  BIGINT NOT NULL,
    CONSTRAINT matches_pk PRIMARY KEY (id),
    CONSTRAINT matches_players_player1_fk FOREIGN KEY (player1) REFERENCES players (id),
    CONSTRAINT matches_players_player2_fk FOREIGN KEY (player2) REFERENCES players (id),
    CONSTRAINT matches_players_winner_fk FOREIGN KEY (winner) REFERENCES players (id)
);

INSERT INTO players (name)
VALUES ('Elina Svitolina');
INSERT INTO players (name)
VALUES ('Marta Kostyuk');
INSERT INTO players (name)
VALUES ('Anhelina Kalinina');
INSERT INTO players (name)
VALUES ('Kateryna Baindl');
INSERT INTO players (name)
VALUES ('Rafael Nadal');
INSERT INTO players (name)
VALUES ('Roger Federer');
INSERT INTO players (name)
VALUES ('111');
INSERT INTO players (name)
VALUES ('222');
INSERT INTO matches (player1, player2, winner)
VALUES (7, 8, 7);
INSERT INTO matches (player1, player2, winner)
VALUES (8, 7, 8);INSERT INTO matches (player1, player2, winner)
VALUES (7, 8, 7);
INSERT INTO matches (player1, player2, winner)
VALUES (8, 7, 8);
INSERT INTO matches (player1, player2, winner)
VALUES (7, 8, 7);
INSERT INTO matches (player1, player2, winner)
VALUES (8, 7, 8);INSERT INTO matches (player1, player2, winner)
VALUES (7, 8, 7);
INSERT INTO matches (player1, player2, winner)
VALUES (8, 7, 8);
INSERT INTO matches (player1, player2, winner)
VALUES (7, 8, 7);
INSERT INTO matches (player1, player2, winner)
VALUES (8, 7, 8);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);
INSERT INTO matches (player1, player2, winner)
VALUES (1, 2, 1);