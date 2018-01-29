DROP TABLE IF EXISTS sport_item_log;
CREATE TABLE sport_item_log(
id bigint(20) NOT NULL AUTO_INCREMENT,
user_id bigint(20),
sport_item_id bigint(20),
weight_record_id bigint(20),
sport_plan_id bigint(20),
day datetime,
is_done int(20),
create_time datetime NOT NULL,
modify_time datetime NOT NULL,
PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;