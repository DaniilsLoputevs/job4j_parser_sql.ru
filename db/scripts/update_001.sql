create table if not exists posts (
    id         serial primary key,
    name       varchar(200),
    text       text,
    link       varchar(100) UNIQUE
    );