DROP TABLE IF EXISTS weight_record;
CREATE TABLE weight_record(
id bigint(20) NOT NULL AUTO_INCREMENT,
user_id bigint(20),
day datetime,
weight bigint(20),
is_sport int(20),
is_breakfast int(20),
is_lunch int(20),
is_dinner int(20),
desc varchar(128),
create_time datetime NOT NULL,
modify_time datetime NOT NULL,
PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;