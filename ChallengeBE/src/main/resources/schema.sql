CREATE TABLE MOVIE
(
  id IDENTITY NOT NULL,
  title VARCHAR2(100) NOT NULL,
  date DATE NOT NULL,
  rank REAL NOT NULL,
  revenue NUMERIC
);

INSERT INTO MOVIE (title, date, rank, revenue) VALUES ('Back to the Future', '1985-07-03', 8.5, 2000000);
INSERT INTO MOVIE (title, date, rank, revenue) VALUES ('The Shawshank Redemption', '1994-09-10', 9.3, 25000000);
INSERT INTO MOVIE (title, date, rank, revenue) VALUES ('The Lord of the Rings: The Fellowship of the Ring', '2001-12-21', 8.8, 93000000);
INSERT INTO MOVIE (title, date, rank, revenue) VALUES ('Memento', '2001-06-29', 8.4, 9000000);
INSERT INTO MOVIE (title, date, rank, revenue) VALUES ('Joker', '2019-09-03', 8.4, 55000000);
INSERT INTO MOVIE (title, date, rank, revenue) VALUES ('The Rum Diary', '2011-12-22', 6.1, 45000000);
INSERT INTO MOVIE (title, date, rank, revenue) VALUES ('Deep Water 1', '2012-03-18', 5.4, 480000);
INSERT INTO MOVIE (title, date, rank, revenue) VALUES ('Halo', '2010-08-23', 7.4, 15000000);
INSERT INTO MOVIE (title, date, rank, revenue) VALUES ('Spider-Man: No Way Home', '2021-12-22', 8.1, 35000000);
INSERT INTO MOVIE (title, date, rank, revenue) VALUES ('Game of Thrones', '2011-11-18', 9.2, 12500000);
INSERT INTO MOVIE (title, date, rank, revenue) VALUES ('Almost Genius', '2015-12-30', 1.8, 80000);
INSERT INTO MOVIE (title, date, rank, revenue) VALUES ('Deep Water 2', '2022-03-18', 4.4, 980000);

CREATE TABLE ACTOR
(
  id IDENTITY NOT NULL,
  name VARCHAR2(100) NOT NULL,
  birth_date DATE NOT NULL,
  gender enum('MALE','FEMALE') NOT NULL,
  movie_id INT NOT NULL,
  CONSTRAINT FK_actor_movie FOREIGN KEY (movie_id) REFERENCES MOVIE (id)  
);

INSERT INTO ACTOR (name, birth_date, gender, movie_id) VALUES('Joaquin Phoenix', '1974-10-28', 'MALE', 5);
INSERT INTO ACTOR (name, birth_date, gender, movie_id) VALUES('Robert De Niro', '1943-08-17', 'MALE', 5);
INSERT INTO ACTOR (name, birth_date, gender, movie_id) VALUES('Zazie Beetz', '1991-05-25', 'FEMALE', 5);
INSERT INTO ACTOR (name, birth_date, gender, movie_id) VALUES('Morgan Freeman', '1937-06-01', 'MALE', 2);
INSERT INTO ACTOR (name, birth_date, gender, movie_id) VALUES('Tim Robbins', '1958-10-16', 'MALE', 2);
INSERT INTO ACTOR (name, birth_date, gender, movie_id) VALUES('Bob Gunton', '1945-11-15', 'MALE', 2);
