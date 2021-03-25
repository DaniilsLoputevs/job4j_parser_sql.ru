drop  table posts;
create table if not exists posts
(
    id        serial primary key,
    post_name varchar(200),
    post_text text,
    link      varchar(150) UNIQUE
);