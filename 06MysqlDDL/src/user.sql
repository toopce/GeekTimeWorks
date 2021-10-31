create table user(
    id varchar (52) NOT NULL PRIMARY KEY,
#     用户名
    user_name varchar (52),
    sex int (2),
#     地址
    address varchar (52),
    created_date datetime,
    created_user varchar (52),
    modified_date datetime,
    modified_user varchar (52),
#     逻辑删除标识
    is_delete int (2) default '0'
    ) default charset utf8 collate utf8_general_ci;

create table product(
    id varchar(52) not null primary key ,
#     产品名称
    product_name varchar(52),
#     价格
    product_price varchar(52),
#     详细信息
    product_detail varchar(255),
    create_date datetime,
    create_user varchar(52),
    modified_date datetime,
    modified_user varchar(52),
    is_delete int(1) default '0'
) default charset utf8 collate utf8_general_ci;

create table order(
    id varchar(52) not null primary key ,
#     关联到用户表的id
    user_id varchar(52),
#     关联到产品表的id
    product_id varchar(52),
#     订单总价
    amount_price Bigdecimal(10,2),
#     邮费
    the_postage BigDecimal(10,2),
#     备注
    remark varchar(255),
    create_date datetime,
    create_user varchar(52),
    modified_date datetime,
    modified_user varchar(52),
    is_delete int(1) default '0'
) default charset utf8 collate utf8_general_ci;