insert into tAuthors (AuthorID, `FIO`) values (1, 'Эдуард Успенский');
insert into tAuthors (AuthorID, `FIO`) values (2, 'Александр Сергеевич Пушкин');
insert into tAuthors (AuthorID, `FIO`) values (3, 'Михаил Хазин');

insert into tGenre (GenreID, `Name`) values (1, 'Детские сказки');
insert into tGenre (GenreID, `Name`) values (2, 'Экономика');
insert into tGenre (GenreID, `Name`) values (3, 'Фантастика');

insert into tBook (BookID, AuthorID, GenreID, `Name`) values (1, 2, 2, 'Тестовая книга для комментариев');
insert into tBook (BookID, AuthorID, GenreID, `Name`) values (2, 3, 3, 'Вторая книга для комментариев');
insert into tBookComment (BookCommentID, BookID, Comment) values (101, 1, 'Первый комментарий для Тестовая книга');
insert into tBookComment (BookCommentID, BookID, Comment) values (102, 1, 'Второй комментарий для Тестовая книга');