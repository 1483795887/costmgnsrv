drop table if exists user;

CREATE TABLE user
(
  id         int PRIMARY KEY AUTO_INCREMENT,
  userid     varchar(20) unique NOT NULL,
  password   varchar(20)        not null,
  name       varchar(20)        not null,
  department varchar(10)        not null,
  post       varchar(10)        not null,
  inpost     bool
);

insert into user (userid, password, name, department, post, inpost)
VALUES ("20200329001", "123456", "张三", "管理", "系统管理员", true)