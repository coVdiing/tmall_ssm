DROP DATABASE IF EXISTS tmall_ssm;
CREATE DATABASE tmall_ssm DEFAULT CHARACTER SET utf8;
use tmall_ssm;

-- 用户表
DROP TABLE if exists `user`;
CREATE TABLE `user` (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(255) DEFAULT NULL,
	password varchar(255) DEFAULT NULL,
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 分类表
CREATE TABLE category (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(255) DEFAULT NULL,
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 属性表
CREATE TABLE property (
	id int(11) NOT NULL AUTO_INCREMENT,
	cid int(11) DEFAULT NULL,
	name varchar(255) DEFAULT NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_property_category FOREIGN KEY(cid) REFERENCES category(id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- 产品表
CREATE TABLE product (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(255) DEFAULT NULL,	-- 产品名称
	subTitle varchar(255) DEFAULT NULL, -- 小标题
	originalPrice float DEFAULT NULL, -- 原始价格
	promotePrice float DEFAULT NULL, -- 优惠价格 
	stock int(11) DEFAULT NULL, -- 库存
	cid int(11) DEFAULT NULL,
	createDate datetime DEFAULT NULL, -- 创建日期
	PRIMARY KEY (id),
	CONSTRAINT fk_product_category FOREIGN KEY(cid) REFERENCES category (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- 属性值表
CREATE TABLE propertyvalue (
	id int(11) NOT NULL AUTO_INCREMENT,
	pid int(11) DEFAULT NULL,	 -- 产品表的id
	ptid int(11) DEFAULT NULL, -- 属性表的id
	`value` varchar(255) DEFAULT NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_propertyvalue_property FOREIGN KEY(ptid) REFERENCES property(id),
	CONSTRAINT fk_propertyvalue_product FOREIGN KEY(pid) REFERENCES product(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 产品图片表
CREATE TABLE productimage(
	id int(11) NOT NULL AUTO_INCREMENT,
	pid int(11) DEFAULT NULL,  -- 指向产品表的id字段
	type varchar(255) DEFAULT NULL, -- 图片类型，分为单个图片和详情图片两种
	PRIMARY KEY (id),
	CONSTRAINT fk_productimage_product FOREIGN KEY(pid) REFERENCES product(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 评价表
CREATE TABLE review (
	id int(11) NOT NULL AUTO_INCREMENT,
	content varchar(4000) DEFAULT NULL,
	uid int(11) DEFAULT NULL, -- 用户表的id字段
	pid int(11) DEFAULT NULL, -- 产品表的id字段
	creaeteDate datetime DEFAULT NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_review_product FOREIGN KEY (pid) REFERENCES product(id),
	CONSTRAINT fk_review_user FOREIGN KEY(uid) REFERENCES `user`(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 订单表
CREATE TABLE `order`(
	id int(11) NOT NULL AUTO_INCREMENT,
	orderCode varchar(255) DEFAULT NULL, -- 订单号
	address varchar(255) DEFAULT NULL,	-- 收货地址
	post varchar(255) DEFAULT NULL,	-- 邮编
	receiver varchar(255) DEFAULT NULL,	-- 收货人信息
	mobile varchar(255),	-- 手机号码
	userMessage varchar(255) DEFAULT NULL, -- 用户备注信息
	createDate datetime DEFAULT NULL, -- 订单创建日期
	payDate datetime DEFAULT NULL, -- 支付日期
	deliveryDate datetime DEFAULT NULL, -- 发货日期
	confirmDate datetime DEFAULT NULL,	-- 确认收货日期
	uid int(11) DEFAULT NULL,	-- 指向用户表id字段
	`status` varchar(255) DEFAULT NULL,	-- 订单状态
	PRIMARY KEY(id),
	CONSTRAINT fk_order_user FOREIGN KEY(uid) REFERENCES `user`(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 订单项表
CREATE TABLE orderitem (
	id int(11) NOT NULL AUTO_INCREMENT,
	pid int(11) DEFAULT NULL,	-- 指向产品表id
	oid int(11) DEFAULT NULL,	-- 指向订单表id
	uid int(11) DEFAULT NULL,	-- 指向用户表id
	number int(11) DEFAULT NULL, -- 购买数量
	PRIMARY KEY(id)
)ENGINE= InnoDB DEFAULT CHARSET=utf8;
