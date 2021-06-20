delete from photo;

insert into photo(id, message, tag, user_id) values
(1, 'first', 'my-tag', 1),
(2, 'second', 'more', 1),
(3, 'third', 'my-tag', 1),
(4, 'fourth', 'another', 2);

alter sequence photo_id_seq restart with 10;