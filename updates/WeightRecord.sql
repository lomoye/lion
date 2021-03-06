DROP TABLE IF EXISTS weight_record;
CREATE TABLE weight_record(
id bigint(20) NOT NULL AUTO_INCREMENT,
user_id bigint(20) NOT NULL,
day datetime NOT NULL,
weight bigint(20) NOT NULL,
is_sport int(20) NOT NULL,
is_breakfast int(20) NOT NULL,
is_lunch int(20) NOT NULL,
is_dinner int(20) NOT NULL,
`desc` varchar(128),
create_time datetime NOT NULL,
modify_time datetime NOT NULL,
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE weight_record ADD COLUMN bust BIGINT(20) AFTER weight;
ALTER TABLE weight_record ADD COLUMN waist BIGINT(20) AFTER bust;
ALTER TABLE weight_record ADD COLUMN hip BIGINT(20) AFTER waist;
ALTER TABLE weight_record ADD COLUMN arm BIGINT(20) AFTER hip;
ALTER TABLE weight_record ADD COLUMN thigh BIGINT(20) AFTER arm;
ALTER TABLE weight_record ADD COLUMN calf BIGINT(20) AFTER thigh;