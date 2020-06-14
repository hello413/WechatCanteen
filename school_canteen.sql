drop database school_canteen;
CREATE SCHEMA `school_canteen` DEFAULT CHARACTER SET utf8mb4 ;
use school_canteen;

-- 窗口表
create table windows (
    `window_id` int not null auto_increment primary key,
    `window_name` varchar(64) not null comment '窗口名字',
    `window_canteen` enum('新食堂','旧食堂') not null comment '食堂选择',
    `window_tier` enum('一楼','二楼','三楼') not null comment '楼层',
    `window_num` int not null,
    `window_picture` varchar(64) not null comment '窗口封面路径'
)comment "窗口表";

-- 食物表
create table `foods` (
    `food_id` int not null auto_increment primary key,
    `food_name` varchar(32) not null,
    `food_type` varchar(32) not null comment '食物类型'
);

-- 菜单表
create table menu (
    `window_id` int not null,
    `food_id` int not null,
    `food_picture` varchar(50) not null comment '窗口食物封面',
    `food_price` decimal(10,2) not null comment '价格',
    `food_style` enum('0','1') not null default '0' comment '0:有货，1：缺货',
    foreign key(window_id) references windows(window_id),
    foreign key(food_id) references foods(food_id)
);

-- 用户表
create table user(
	`user_id` int not null auto_increment primary key,
    `user_name` varchar(64) not null,
    `user_password` varchar(20) not null,
    `user_telephone` bigint(20) not null comment '忘记密码时'
);

-- 管理员
create table admini (
    `admini_id` int not null auto_increment primary key,
    `admini_name` varchar(64) not null comment '管理员姓名',
    `admini_password` varchar(20) not null,
    `admini_telephone` varchar(20) not null comment '微信号',
    `admini_window` int not null comment '所属窗口'
);

-- 订单表
create table `orderlist` (
    `order_id` int not null auto_increment primary key,
    `user_id` int not null,
    `window_id` int not null comment '购物车',
    `order_price` decimal(10,2) not null default '0' comment '价格',
    `style` enum('0','1') not null default '0' comment '0:未完成，1：完成',
    `create_time` timestamp not null default now(),
    `finish_time` timestamp not null default '0000-00-00 00:00:00',
    `about` varchar(200) comment '备注',
    `time_id` int
) comment '订单';

create table goods(
	good_id int not null primary key auto_increment,
	good_food_id int not null comment '食物编号',
	good_type varchar(20) comment '微辣',
	good_num int comment '数量',
	good_order_id int comment '购物车'
);

-- 时间段表
create table `time` (
	`time_id` int auto_increment not null primary key,
    `canteen` enum('新食堂','旧食堂') not null comment '食堂选择',
    `tier` enum('一楼','二楼','三楼') not null comment '楼层',
    `style` varchar(5),
    `start_time` timestamp not null default now(),
    `finish_time` timestamp default '0000-00-00 00:00:00',
    `upperlimit` int not null default '500' comment '上限',
    `nownum` int not null
);

create table word(
 `word` varchar(200)
 );

-- 给windows窗口插数据
INSERT INTO `windows`(`window_id`, `window_name`, `window_canteen`, `window_tier`, `window_num`, `window_picture`) VALUES ('1', '赵氏卤肉饭', '新食堂','一楼', '1', '/Images/new/one/1.jpg');
INSERT INTO `windows`(`window_name`, `window_canteen`, `window_tier`, `window_num`, `window_picture`) VALUES ('马氏炒饭', '新食堂','一楼', '2', '/Images/new/one/2.jpg');
INSERT INTO `windows`(`window_name`, `window_canteen`, `window_tier`, `window_num`, `window_picture`) VALUES ('李氏面馆', '旧食堂', '一楼', '1', '/Images/odd/one/1.jpg');
INSERT INTO `windows`(`window_name`, `window_canteen`, `window_tier`, `window_num`, `window_picture`) VALUES ('雷氏饺子馆', '旧食堂', '一楼', '2', '/Images/odd/one/2.jpg');
INSERT INTO `windows`(`window_name`, `window_canteen`, `window_tier`, `window_num`, `window_picture`) VALUES ('宝鸭面馆', '新食堂', '二楼', '1', '/Images/new/two/1.jpg');
INSERT INTO `windows`(`window_name`, `window_canteen`, `window_tier`, `window_num`, `window_picture`) VALUES ('麻辣烫', '旧食堂', '三楼', '1', '/Images/odd/three/1.jpg');


-- 给食物插数据
INSERT INTO `foods` (`food_id`, `food_name`,`food_type`) VALUES ('1', '刀削面','面食');
INSERT INTO `foods` (`food_name`,`food_type`) VALUES ('猪肉大葱饺子','饺子');
INSERT INTO `foods` (`food_name`,`food_type`) VALUES ('土豆','素菜');
INSERT INTO `foods` (`food_name`,`food_type`) VALUES ('炒鸡蛋','荤菜');
INSERT INTO `foods` (`food_name`,`food_type`) VALUES ('油泼面','面食');
INSERT INTO `foods` (`food_name`,`food_type`) VALUES ('米饭','米类');
INSERT INTO `foods` (`food_name`,`food_type`) VALUES ('鸡腿','荤菜');
INSERT INTO `foods` (`food_name`,`food_type`) VALUES ('豆浆','饮品');
INSERT INTO `foods` (`food_name`,`food_type`) VALUES ('可口可乐','饮品');
INSERT INTO `foods` (`food_name`,`food_type`) VALUES ('炒米','米类');
INSERT INTO `foods` (`food_name`,`food_type`) VALUES ('汽水','饮品');
INSERT INTO `foods` (`food_name`,`food_type`) VALUES ('豆腐','素菜');
INSERT INTO `foods` (`food_name`,`food_type`) VALUES ('菠菜','素菜');
INSERT INTO `foods` (`food_name`,`food_type`) VALUES ('黄瓜','素菜');
INSERT INTO `foods` (`food_name`,`food_type`) VALUES ('腰子','荤菜');

-- 给菜单查数据
INSERT INTO `menu` VALUES ('1','3','/Images/new/one/one/potato.jpg','1.0','0');
INSERT INTO `menu` VALUES ('1','4','/Images/new/one/one/fryegg.jpg','1.0','0');
INSERT INTO `menu` VALUES ('1','6','/Images/new/one/one/rich.jpg','1.0','0');
INSERT INTO `menu` VALUES ('1','7','/Images/new/one/one/drumstick.jpg','3.0','0');
INSERT INTO `menu` VALUES ('1','9','/Images/new/one/one/coca.jpg','4.0','0');
INSERT INTO `menu` VALUES ('1','11','/Images/new/one/one/codewater.jpg','4.0','0');
INSERT INTO `menu` VALUES ('1','12','/Images/new/one/one/doufu.jpg','1.0','0');
INSERT INTO `menu` VALUES ('1','13','/Images/new/one/one/spinach.jpg','1.0','0');
INSERT INTO `menu` VALUES ('1','14','/Images/new/one/one/cuke.jpg','1.0','0');
INSERT INTO `menu` VALUES ('1','15','/Images/new/one/one/kidney.jpg','3.0','0');
INSERT INTO `menu` VALUES ('2','6','/Images/new/one/two/rich.jpg','1.0','0');
INSERT INTO `menu` VALUES ('2','7','/Images/new/one/two/drumstick.jpg','4.0','0');
INSERT INTO `menu` VALUES ('3','1','/Images/odd/one/one/daonoodles.jpg','6.0','0');
INSERT INTO `menu` VALUES ('3','5','/Images/odd/one/one/younoodles.jpg','7.0','0');
INSERT INTO `menu` VALUES ('4','2','/Images/odd/one/two/dumplings.jpg','10.0','0');

-- 给时间段表插数据
INSERT INTO `time` VALUES ('1','新食堂','一楼','1','2020-05-25 11:30:00','2020-05-25 12:00:00',500,498);
INSERT INTO `time` VALUES ('2','新食堂','一楼','2','2020-05-25 12:00:00','2020-05-25 12:30:00',500,200);
INSERT INTO `time` VALUES ('3','新食堂','一楼','3','2020-05-25 12:30:00','2020-05-25 13:00:00',500,498);
INSERT INTO `time` VALUES ('4','新食堂','一楼','4','2020-05-25 13:00:00','2020-05-25 13:30:00',500,499);
INSERT INTO `time` VALUES ('5','新食堂','一楼','5','2020-05-25 13:30:00','2020-05-25 14:00:00',500,300);
INSERT INTO `time` VALUES ('6','新食堂','二楼','1','2020-05-25 11:30:00','2020-05-25 12:00:00',500,497);

-- 给管理员插数据
INSERT INTO `admini` VALUES ('1', '赵总', '123456', '13045628520', '1');
INSERT INTO `admini` VALUES ('2','赵云', '123456', '13045678956', '1');
INSERT INTO `admini` VALUES ('3','马董', '123123', '13096385274', '2');
INSERT INTO `admini` VALUES ('4','马超', '123123', '13074185296', '2');
INSERT INTO `admini` VALUES ('5','李帅', '123123', '13012341234', '3');
INSERT INTO `admini` VALUES ('6','李存孝', '123123', '13067896789', '3');
INSERT INTO `admini` VALUES ('7','雷开心', '123456', '13093944163', '4');
INSERT INTO `admini` VALUES ('8','雷军', '123456', '13045674567', '4');
