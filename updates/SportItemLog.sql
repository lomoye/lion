DROP TABLE IF EXISTS sport_item_log;
CREATE TABLE sport_item_log(
id bigint(20) NOT NULL AUTO_INCREMENT,
user_id bigint(20) NOT NULL,
sport_item_id bigint(20) NOT NULL,
sport_item_name VARCHAR(255) NOT NULL,
weight_record_id bigint(20) NOT NULL,
sport_plan_id bigint(20) NOT NULL,
day datetime NOT NULL,
create_time datetime NOT NULL,
modify_time datetime NOT NULL,
PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;