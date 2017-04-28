DROP TABLE IF EXISTS library;

CREATE TABLE library
(
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
  book_name   VARCHAR(55)     NOT NULL,
  author      VARCHAR(55)     NOT NULL,
  description VARCHAR(255),
  icon        LONGBLOB
);