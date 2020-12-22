DROP TABLE IF EXISTS tAuthors;
CREATE TABLE tAuthors(AuthorID numeric(15,0), FIO VARCHAR(255));

DROP INDEX IF EXISTS XPKtAuthors;
create unique index XPKtAuthors on tAuthors (AuthorID);

DROP INDEX IF EXISTS XAK1tAuthors;
create unique index XAK1tAuthors on tAuthors (FIO);
------
DROP TABLE IF EXISTS tGenre;
CREATE TABLE tGenre(GenreID numeric(15,0), Name VARCHAR(60));

DROP INDEX IF EXISTS XPKtGenre;
create unique index XPKtGenre on tGenre (GenreID);

DROP INDEX IF EXISTS XAK1tGenre;
create unique index XAK1tGenre on tGenre (Name);
------
DROP TABLE IF EXISTS tBook;
CREATE TABLE tBook(BookID numeric(15,0), AuthorID numeric(15,0), GenreID numeric(15,0), Name VARCHAR(255));

DROP INDEX IF EXISTS XPKtBook;
create unique index XPKtBook on tBook (BookID);

