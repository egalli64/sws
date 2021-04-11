drop table if exists users;

--
create table users (
    user_id integer auto_increment primary key,
    username varchar(25) unique not null,
    password varchar(60)
);

-- {bcrypt}
insert into users (username, password) values('tom', '$2a$10$BsXAGpkEe6YRV2KbJ996ReSkflDfZgPxpaDq/6B7Y15nVBT6yuo3W');

-- {noop}
-- insert into users (username, password) values('tom', 'password');