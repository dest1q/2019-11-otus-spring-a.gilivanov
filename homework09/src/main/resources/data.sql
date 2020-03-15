insert into authors(full_name) values('Sapkovsky');
insert into authors(full_name) values('Conan Doyle');

insert into genres(name) values('Fantasy');
insert into genres(name) values('Detective');

insert into books(title, author_id, genre_id)
values('The Witcher', (select id from authors where full_name = 'Sapkovsky'), (select id from genres where name = 'Fantasy'));
insert into books(title, author_id, genre_id)
values('The Hound of the Baskervilles', (select id from authors where full_name = 'Conan Doyle'), (select id from genres where name = 'Detective'));
insert into books(title, author_id, genre_id)
values('The Valley of Fear', (select id from authors where full_name = 'Conan Doyle'), (select id from genres where name = 'Detective'));
