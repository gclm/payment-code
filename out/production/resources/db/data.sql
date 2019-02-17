/**
  判断  pay_user  pay_qr 存在不，如果不存在就创建该表
 */
drop table  if  exists pay_user
drop table  if  exists pay_qr

/**
  建表语句
 */
create table pay_user(
  id varchar(50) primary,
  username varchar(30),
  password varchar varchar(25),
  email varchar(100),
  nickname varchar (50)
)

/**
  建表语句
 */
create table pay_qr(
  id varchar(50) primary,
  qr_title varchar(30),
  qr_value varchar varchar(25),
  qr_img varchar(255),
  user_id varchar (50)
)