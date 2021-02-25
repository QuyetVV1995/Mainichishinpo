create database mainichishinpo;
use mainichishinpo;

create table user(
id bigint not null primary key auto_increment,
email varchar(255) not null,
fullname varchar(255),
password varchar(255)
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

alter table users_roles add  foreign key(user_id) references user(id);
alter table users_roles add  foreign key(role_id) references role(id);
alter table comment add foreign key(post_id) references post(id);
alter table comment add foreign key(user_id) references user(id);
alter table post add constraint FK72mt33dhhs48hf9gcqrq4fxte foreign key (user_id) references user (id);
alter table post_tag add constraint FKac1wdchd2pnur3fl225obmlg0 foreign key (tag_id) references tag (id);
alter table post_tag add constraint FKc2auetuvsec0k566l0eyvr9cs foreign key (post_id) references post (id);


-- --password:123
insert into user(fullname, email, password) values ('QuyetVV', 'quyeta2ubqn@gmail.com','$2a$10$GBCQjLA5BstflUfAlS/NUecsupn/5M6/Zshea3d9YJiRoAStcJ5EC');

insert into role (name) values ('ROLE_USER');
insert into role (name) values ('ROLE_ADMIN');

insert into users_roles(user_id, role_id) values (1,2);

insert into tag(name) values('N1');
insert into tag(name) values('N2');
insert into tag(name) values('N3');
insert into tag(name) values('N4');
insert into tag(name) values('N5');
insert into tag(name) values('Tiếng nhật IT');
insert into tag(name) values('Java cơ bản');
insert into tag(name) values('Spring Boot');
insert into tag(name) values('Từ vựng ');
insert into tag(name) values('Kanji ');
insert into tag(name) values('Ngữ pháp ');
insert into tag(name) values('Đọc hiểu');
insert into tag(name) values('Đề thi');

insert into post(title, content, user_id, create_at) values ('Tieu de bai viet so 1', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem sed id ipsam, aperiam ea tempore enim eveniet repellendus eaque vero iusto veniam natus atque, saepe porro harum provident minima? Debitis saepe explicabo ad optio, nobis labore sequi temporibus doloribus sunt?', 1, now());
insert into post(title, content, user_id, create_at) values ('Tieu de bai viet so 2', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem sed id ipsam, aperiam ea tempore enim eveniet repellendus eaque vero iusto veniam natus atque, saepe porro harum provident minima? Debitis saepe explicabo ad optio, nobis labore sequi temporibus doloribus sunt?', 1, now());
insert into post(title, content, user_id, create_at) values ('Tieu de bai viet so 3', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem sed id ipsam, aperiam ea tempore enim eveniet repellendus eaque vero iusto veniam natus atque, saepe porro harum provident minima? Debitis saepe explicabo ad optio, nobis labore sequi temporibus doloribus sunt?', 1, now());
insert into post(title, content, user_id, create_at) values ('Tieu de bai viet so 4', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem sed id ipsam, aperiam ea tempore enim eveniet repellendus eaque vero iusto veniam natus atque, saepe porro harum provident minima? Debitis saepe explicabo ad optio, nobis labore sequi temporibus doloribus sunt?', 1, now());

insert into post_tag(tag_id, post_id) values (1,1);
insert into post_tag(tag_id, post_id) values (9,1);
insert into post_tag(tag_id, post_id) values (10,1);
insert into post_tag(tag_id, post_id) values (11,1);
insert into post_tag(tag_id, post_id) values (12,1);
insert into post_tag(tag_id, post_id) values (13,1);

insert into post_tag(tag_id, post_id) values (2,2);
insert into post_tag(tag_id, post_id) values (9,2);
insert into post_tag(tag_id, post_id) values (10,2);
insert into post_tag(tag_id, post_id) values (11,2);
insert into post_tag(tag_id, post_id) values (12,2);
insert into post_tag(tag_id, post_id) values (13,2);

insert into post_tag(tag_id, post_id) values (3,3);
insert into post_tag(tag_id, post_id) values (9,3);
insert into post_tag(tag_id, post_id) values (10,3);
insert into post_tag(tag_id, post_id) values (11,3);
insert into post_tag(tag_id, post_id) values (12,3);
insert into post_tag(tag_id, post_id) values (13,3);

