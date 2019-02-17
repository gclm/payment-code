/**
  建表语句,创建数据表 pay_user
 */
DROP TABLE IF EXISTS pay_user;
CREATE TABLE pay_user(
   id VARCHAR(50) PRIMARY KEY,
   username VARCHAR(30),
   password VARCHAR (25),
   email VARCHAR(100),
   nickname VARCHAR (50)
);


/**
  建表语句
 */
DROP TABLE IF EXISTS pay_qr;
CREATE TABLE pay_qr(
   id VARCHAR(50) PRIMARY KEY,
   qr_title VARCHAR(30),
   qr_value VARCHAR(25),
   qr_img VARCHAR(100),
   user_id VARCHAR (50)
);