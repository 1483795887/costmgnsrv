drop table if exists contract;
drop table if exists plan;
drop table if exists cost;
drop table if exists receipt;
drop table if exists budget;
drop table if exists work;
drop table if exists user;


create table user
(
  id         int primary key auto_increment,
  userid     varchar(20) unique not null,
  password   varchar(20)        not null,
  name       varchar(20)        not null,
  department int                not null,
  post       int                not null,
  inpost     bool
);

CREATE TABLE work
(
  id         int PRIMARY KEY AUTO_INCREMENT,
  user_id    int  NOT NULL,
  date       date NOT NULL,
  status     int  NOT NULL,
  type       int  NOT NULL,
  entity_id  int,
  department int  not null,
  CONSTRAINT work_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE plan
(
  id          int PRIMARY KEY AUTO_INCREMENT,
  title       varchar(10) NOT NULL,
  description varchar(200),
  work_id     int         not null,
  CONSTRAINT plan_work_id_fk FOREIGN KEY (work_id) REFERENCES work (id)
);

CREATE TABLE contract
(
  id            int PRIMARY KEY AUTO_INCREMENT,
  contract_no   varchar(20)   NOT NULL,
  contract_date date          not null,
  money         decimal(5, 2) not null,
  pay_method    varchar(10)   not null,
  pay_request   varchar(10)   not null,
  company       varchar(20)   not null,
  legal_person  varchar(10)   not null,
  last_month    int           not null,
  plan_id       int           not null,
  title         varchar(10)   NOT NULL,
  description   varchar(200),
  work_id       int           not null,
  CONSTRAINT contract_work_id_fk FOREIGN KEY (work_id) REFERENCES work (id),
  CONSTRAINT contract_plan_id_fk FOREIGN KEY (plan_id) REFERENCES plan (id)
);

CREATE TABLE budget
(
  id           int PRIMARY KEY AUTO_INCREMENT,
  year         int           NOT NULL,
  month        int           not null,
  money        decimal(5, 2) not null,
  type         varchar(20)   not null,
  occupy_money decimal(5, 2) not null,
  work_id      int           not null,
  CONSTRAINT budget_work_id_fk FOREIGN KEY (work_id) REFERENCES work (id)
);

CREATE TABLE receipt
(
  id        int PRIMARY KEY AUTO_INCREMENT,
  money     decimal(5, 2) not null,
  type      varchar(20)   not null,
  budget_id int           not null,
  work_id   int           not null,
  CONSTRAINT receipt_work_id_fk FOREIGN KEY (work_id) REFERENCES work (id),
  CONSTRAINT receipt_budget_id_fk FOREIGN KEY (budget_id) REFERENCES budget (id)
);

insert into user (userid, password, name, department, post, inpost)
VALUES ("admin", "123456", "张三", 2, 2, true)