drop table if exists work;
drop table if exists user;

create table user
(
  id         int primary key auto_increment,
  userid     varchar(20) unique not null,
  password   varchar(20)        not null,
  name       varchar(20)        not null,
  department varchar(10)        not null,
  post       varchar(10)        not null,
  inpost     bool
);

CREATE TABLE work
(
  id      int PRIMARY KEY AUTO_INCREMENT,
  user_id int         NOT NULL,
  date    date        NOT NULL,
  status  varchar(10) NOT NULL,
  title   varchar(10) NOT NULL,
  CONSTRAINT work_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id)
);
CREATE UNIQUE INDEX work_id_uindex
  ON work (id);

insert into user (userid, password, name, department, post, inpost)
VALUES ("admin", "123456", "张三", "管理", "系统管理员", true)