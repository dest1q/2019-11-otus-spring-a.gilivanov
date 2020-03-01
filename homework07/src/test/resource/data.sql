insert into authors(id, full_name) values(1, 'Test Author');

insert into genres(id, name) values(1, 'Test Genre');

insert into books(id, title, author_id, genre_id)
values(1, 'Test Book', 1, 1);