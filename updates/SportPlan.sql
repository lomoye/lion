DROP TABLE IF EXISTS sport_plan;
CREATE TABLE sport_plan(
id bigint(20) NOT NULL AUTO_INCREMENT,
user_id bigint(20) NOT NULL,
target_weight bigint(20) NOT NULL,
start_time datetime NOT NULL,
end_time datetime NOT NULL,
sport_item_ids varchar(128) NOT NULL,
create_time datetime NOT NULL,
modify_time datetime NOT NULL,
PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;