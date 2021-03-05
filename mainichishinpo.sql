create database IF NOT EXISTS mainichishinpo;
use mainichishinpo;

create table user(
id bigint not null primary key auto_increment,
email varchar(255) not null,
fullname varchar(255),
password varchar(255),
verificationCode varchar(64),
enable boolean
);
create table role(
id bigint not null primary key auto_increment,
name varchar(255)
);
create table users_roles(
id bigint not null primary key auto_increment,
user_id bigint not null,
role_id bigint not null
);

create table comment(
    id bigint not null primary key auto_increment,
    content varchar(255),
    post_id bigint not null,
    user_id bigint not null
);


create table post(
    id bigint not null primary key auto_increment,
    title varchar(255),
    content text,
    user_id bigint not null,
    create_at datetime
);

create table tag(
    id bigint not null primary key auto_increment,
    name varchar(255)
);

create table post_tag(
id bigint not null primary key auto_increment,
tag_id bigint not null,
post_id bigint not null
);

alter table users_roles add foreign key(user_id) references user(id);
alter table users_roles add foreign key(role_id) references role(id);
alter table comment add foreign key(post_id) references post(id);
alter table comment add foreign key(user_id) references user(id);

alter table post add  foreign key (user_id) references user (id);

alter table post_tag add foreign key (tag_id) references tag (id);
alter table post_tag add foreign key (post_id) references post (id);


-- --password:123
insert into user(fullname, email, password) values ('QuyetVV', 'quyeta2ubqn@gmail.com','$2a$10$GBCQjLA5BstflUfAlS/NUecsupn/5M6/Zshea3d9YJiRoAStcJ5EC');
-- --password:123
insert into user(fullname, email, password) values ('abc', 'abc@gmail.com','$2a$10$GBCQjLA5BstflUfAlS/NUecsupn/5M6/Zshea3d9YJiRoAStcJ5EC');


insert into role (name) values ('ROLE_USER');
insert into role (name) values ('ROLE_ADMIN');

insert into users_roles(user_id, role_id) values (1,2);
insert into users_roles(user_id, role_id) values (2,1);

insert into tag(name) values('N1');
insert into tag(name) values('N2');
insert into tag(name) values('N3');
insert into tag(name) values('N4');
insert into tag(name) values('N5');
insert into tag(name) values('IT_Japanese');
insert into tag(name) values('Java_Basic');
insert into tag(name) values('Spring_Boot');
insert into tag(name) values('Vocabulary');
insert into tag(name) values('Kanji ');
insert into tag(name) values('Grammar');
insert into tag(name) values('Reading');
insert into tag(name) values('Exam');

insert into post(title, content, user_id, create_at) values ('Tieu de bai viet so 1', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem sed id ipsam, aperiam ea tempore enim eveniet repellendus eaque vero iusto veniam natus atque, saepe porro harum provident minima? Debitis saepe explicabo ad optio, nobis labore sequi temporibus doloribus sunt?', 1, now());
insert into post(title, content, user_id, create_at) values ('Tieu de bai viet so 2', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem sed id ipsam, aperiam ea tempore enim eveniet repellendus eaque vero iusto veniam natus atque, saepe porro harum provident minima? Debitis saepe explicabo ad optio, nobis labore sequi temporibus doloribus sunt?', 1, now());
insert into post(title, content, user_id, create_at) values ('Tieu de bai viet so 3', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem sed id ipsam, aperiam ea tempore enim eveniet repellendus eaque vero iusto veniam natus atque, saepe porro harum provident minima? Debitis saepe explicabo ad optio, nobis labore sequi temporibus doloribus sunt?', 1, now());
insert into post(title, content, user_id, create_at) values ('Tieu de bai viet so 4', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem sed id ipsam, aperiam ea tempore enim eveniet repellendus eaque vero iusto veniam natus atque, saepe porro harum provident minima? Debitis saepe explicabo ad optio, nobis labore sequi temporibus doloribus sunt?', 1, now());
insert into post(title, content, user_id, create_at) values ('Tieu de bai viet so 6', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem sed id ipsam, aperiam ea tempore enim eveniet repellendus eaque vero iusto veniam natus atque, saepe porro harum provident minima? Debitis saepe explicabo ad optio, nobis labore sequi temporibus doloribus sunt?', 1, now());

insert into post_tag(tag_id, post_id) values (5,1);
insert into post_tag(tag_id, post_id) values (4,1);
insert into post_tag(tag_id, post_id) values (2,2);
insert into post_tag(tag_id, post_id) values (13,5);
insert into post_tag(tag_id, post_id) values (9,6);
insert into post_tag(tag_id, post_id) values (12,6);


insert into comment (content, post_id, user_id) values('comment so 1', 2, 1);
insert into comment (content, post_id, user_id) values('comment so 2', 3, 2);
insert into comment (content, post_id, user_id) values('comment so 3', 4, 2);


select * from post_tag inner join post on post_tag.post_id = post.id where post_tag.tag_id = 10 or post_tag.tag_id = 1 ;

select * from post, post_tag, tag where post.id = post_tag.id and post_tag.tag_id = tag.id and tag.id = 3;

SELECT * FROM post INNER JOIN tag ON  tag.name = 'Kanji';

-- Lay tat ca bai viet N5
SELECT * FROM post inner join post_tag on post_tag.tag_id = 5 ;
use mainichishinpo;

select * from post, post_tag, tag where tag.id = 3 and post_tag.tag_id = tag.id and post_tag.post_id = post.id;



insert into post(title, content, user_id, create_at) values ('Tieu de 1', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem sed id ipsam, aperiam ea tempore enim eveniet repellendus eaque vero iusto veniam natus atque, saepe porro harum provident minima? Debitis saepe explicabo ad optio, nobis labore sequi temporibus doloribus sunt?', 1, now());
insert into post(title, content, user_id, create_at) values ('Tieu de 2', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem sed id ipsam, aperiam ea tempore enim eveniet repellendus eaque vero iusto veniam natus atque, saepe porro harum provident minima? Debitis saepe explicabo ad optio, nobis labore sequi temporibus doloribus sunt?', 1, now());
insert into post(title, content, user_id, create_at) values ('Tieu de 3', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem sed id ipsam, aperiam ea tempore enim eveniet repellendus eaque vero iusto veniam natus atque, saepe porro harum provident minima? Debitis saepe explicabo ad optio, nobis labore sequi temporibus doloribus sunt?', 1, now());
insert into post(title, content, user_id, create_at) values ('Tieu de 4', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem sed id ipsam, aperiam ea tempore enim eveniet repellendus eaque vero iusto veniam natus atque, saepe porro harum provident minima? Debitis saepe explicabo ad optio, nobis labore sequi temporibus doloribus sunt?', 1, now());
insert into post(title, content, user_id, create_at) values ('Tieu de 6', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem sed id ipsam, aperiam ea tempore enim eveniet repellendus eaque vero iusto veniam natus atque, saepe porro harum provident minima? Debitis saepe explicabo ad optio, nobis labore sequi temporibus doloribus sunt?', 1, now());

-- Bai viet so 7: n5, kanji
insert into post_tag(tag_id, post_id) values (10,7);
insert into post_tag(tag_id, post_id) values (5,7);

-- bai viet so 8: n4 tu vung
insert into post_tag(tag_id, post_id) values (4,8);
insert into post_tag(tag_id, post_id) values (9,8);


insert into post_tag(tag_id, post_id) values (2,9);
insert into post_tag(tag_id, post_id) values (13,10);
insert into post_tag(tag_id, post_id) values (9,11);


select * from post_tag where post_tag.tag_id = 10;

alter table post drop column is_admin;

delete from users_roles where id = 11;
delete from comment where user_id = 11;
delete from user where id = 11;




