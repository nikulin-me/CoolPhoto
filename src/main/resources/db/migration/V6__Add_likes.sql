create table photo_likes
(
    photo_id bigint not null references photo,
    user_id  bigint not null references usr,
    primary key (user_id, photo_id)
);


