DROP TABLE IF EXISTS weight_record_image;
CREATE TABLE weight_record_image(
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20) NOT NULL,
  weight_record_id bigint(20) NOT NULL,
  image_url varchar(128) NOT NULL,
  create_time datetime NOT NULL,
  modify_time datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;