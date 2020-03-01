create table authors(
  id identity primary key,
  full_name varchar(255) not null
);

create table genres(
  id identity primary key,
  name varchar(255) not null
);

create table books(
  id identity primary key,
  title varchar(255) not null,
  author_id integer not null,
  genre_id integer not null,
  foreign key (author_id) references authors (id),
  foreign key (genre_id) references genres (id)
);