DROP TABLE IF EXISTS tBookComment;
DROP TABLE IF EXISTS tBook;
DROP TABLE IF EXISTS tAuthors;
DROP TABLE IF EXISTS tGenre;

CREATE TABLE tAuthors(AuthorID numeric(15,0) PRIMARY KEY, FIO VARCHAR(255));

DROP INDEX IF EXISTS XPKtAuthors;
create unique index XPKtAuthors on tAuthors (AuthorID);

DROP INDEX IF EXISTS XAK1tAuthors;
create unique index XAK1tAuthors on tAuthors (FIO);
------
CREATE TABLE tGenre(GenreID numeric(15,0) PRIMARY KEY, Name VARCHAR(60));

DROP INDEX IF EXISTS XPKtGenre;
create unique index XPKtGenre on tGenre (GenreID);

DROP INDEX IF EXISTS XAK1tGenre;
create unique index XAK1tGenre on tGenre (Name);
------
CREATE TABLE tBook(
BookID numeric(15,0) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
AuthorID numeric(15,0),
GenreID numeric(15,0),
Name VARCHAR(255),
FOREIGN KEY (AuthorID) REFERENCES tAuthors (AuthorID),
FOREIGN KEY (GenreID) REFERENCES tGenre (GenreID)
);

DROP INDEX IF EXISTS XPKtBook;
create unique index XPKtBook on tBook (BookID);
------
CREATE TABLE tBookComment(
BookCommentID numeric(15,0) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
BookID numeric(15,0),
Comment VARCHAR(255),
FOREIGN KEY (BookID) REFERENCES tBook (BookID)
);

