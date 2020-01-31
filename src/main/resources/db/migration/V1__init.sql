CREATE TABLE user (
  id bigint unsigned  NOT NULL AUTO_INCREMENT,
  created_at datetime(6) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,

  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE article (
  id bigint unsigned NOT NULL AUTO_INCREMENT,
  content varchar(255) DEFAULT NULL,
  created_at datetime(6) DEFAULT NULL,
  title varchar(255) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  user_id bigint unsigned DEFAULT NULL,

  PRIMARY KEY (id),
  KEY fk_article_user_id (user_id),
  CONSTRAINT fk_article_user_id FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE authority (
    id bigint unsigned NOT NULL AUTO_INCREMENT,
    user_id bigint unsigned NOT NULL,
    authority varchar(255) NOT NULL,

    PRIMARY KEY (id),
    KEY fk_authority_user_id (user_id),
    CONSTRAINT fk_authority_user_id FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE hibernate_sequence (
  next_val bigint unsigned DEFAULT NULL,
  sequence_name VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

