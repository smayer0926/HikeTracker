SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS hikes (
  id int PRIMARY KEY auto_increment,
  hikeName VARCHAR,
  hikeLocation VARCHAR,
  hikeNotes VARCHAR,
  hikeRating INT,
  hikeCompleted BOOLEAN,
);