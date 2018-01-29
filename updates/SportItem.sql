DROP TABLE IF EXISTS sport_item;
CREATE TABLE sport_item(
id bigint(20) NOT NULL AUTO_INCREMENT,
user_id bigint(20) NOT NULL,
name varchar(128) NOT NULL,
create_time datetime NOT NULL,
modify_time datetime NOT NULL,
PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;