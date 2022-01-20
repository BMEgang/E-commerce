-- 创建数据库
drop database if exists `store_40`;
create database `store_40`;
-- 使用数据库
use store_40;

-- 1.1 创建用户表
CREATE TABLE `user` (
  `uid` varchar(32) NOT NULL,  #用户编号
  `username` varchar(20) DEFAULT NULL,		#用户名
  `password` varchar(20) DEFAULT NULL,		#密码
  `name` varchar(20) DEFAULT NULL,			#昵称
  `email` varchar(30) DEFAULT NULL,			#电子邮箱
  `telephone` varchar(20) DEFAULT NULL,		#电话
  `birthday` date DEFAULT NULL,				#生日
  `sex` varchar(10) DEFAULT NULL,			#性别
  `state` int(11) DEFAULT 0,				#状态：0=未激活，1=已激活
  `code` varchar(64) DEFAULT NULL,			#激活码
  PRIMARY KEY (`uid`)
) ;


-- 2.1 创建分类表
CREATE TABLE `category` (
  `cid` varchar(32) NOT NULL,
  `cname` varchar(20) DEFAULT NULL,	#分类名称
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB;
-- 2.2 初始化分类默认数据
INSERT INTO `category` VALUES ('1','手机数码'),('172934bd636d485c98fd2d3d9cccd409','运动户外'),('2','电脑办公'),('3','家具家居'),('4','鞋靴箱包'),('5','图书音像'),('59f56ba6ccb84cb591c66298766b83b5','aaaa'),('6','母婴孕婴'),('afdba41a139b4320a74904485bdb7719','汽车用品');


-- 3.1 创建商品表
CREATE TABLE `product` (
  `pid` varchar(32) NOT NULL,
  `pname` varchar(50) DEFAULT NULL,		#商品名称
  `market_price` double DEFAULT NULL,	#市场价
  `shop_price` double DEFAULT NULL,		#商城价
  `pimage` varchar(200) DEFAULT NULL,	#商品图片路径
  `pdate` date DEFAULT NULL,			#上架时间
  `is_hot` int(11) DEFAULT NULL,		#是否热门：0=不热门,1=热门
  `pdesc` varchar(255) DEFAULT NULL,	#商品描述
  `pflag` int(11) DEFAULT 0,			#商品标记：0=未下架(默认值),1=已经下架
  `cid` varchar(32) DEFAULT NULL,		#分类id
  PRIMARY KEY (`pid`),
  KEY `product_fk_0001` (`cid`),
  CONSTRAINT `product_fk_0001` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB;
-- 3.2 初始化商品默认数据
INSERT INTO `product` VALUES ('1','适用小米note m4小米4c小米3手机屏幕总成寄修维修单独换外屏触摸',1399,1299,'products/1/c_1001.jpg','2015-11-02',1,'小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待',0,'1');
-- 4 创建订单表
CREATE TABLE `orders` (
  `oid` varchar(32) NOT NULL,
  `ordertime` datetime DEFAULT NULL,		#下单时间
  `total` double DEFAULT NULL,				#总价
  `state` int(11) DEFAULT NULL,				#订单状态：1=未付款;2=已付款,未发货;3=已发货,没收货;4=收货,订单结束
  `address` varchar(30) DEFAULT NULL,		#收获地址
  `name` varchar(20) DEFAULT NULL,			#收获人
  `telephone` varchar(20) DEFAULT NULL,		#收货人电话
  `uid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `order_fk_0001` (`uid`),
  CONSTRAINT `order_fk_0001` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ;

-- 5 创建订单项表
CREATE TABLE `orderitem` (
  `itemid` varchar(32) NOT NULL,
  `quantity` int(11) DEFAULT NULL,			#购买数量
  `total` double DEFAULT NULL,			#小计
  `pid` varchar(32) DEFAULT NULL,		#购买商品的id
  `oid` varchar(32) DEFAULT NULL,		#订单项所在订单id
  PRIMARY KEY (`itemid`),
  KEY `order_item_fk_0001` (`pid`),
  KEY `order_item_fk_0002` (`oid`),
  CONSTRAINT `order_item_fk_0001` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `order_item_fk_0002` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
) ;


