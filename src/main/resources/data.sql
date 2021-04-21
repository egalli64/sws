drop table if exists users;

--
create table users (
    user_id integer auto_increment primary key,
    username varchar(25) unique not null,
    password varchar(60),
    admin boolean default false
);

-- {noop}
insert into users (username, password) values('tom', 'password');