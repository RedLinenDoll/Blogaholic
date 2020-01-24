DROP TABLE IF EXISTS comment_db;
DROP TABLE IF EXISTS article_db;
DROP TABLE IF EXISTS users_db;
DROP TABLE IF EXISTS layout_db;


CREATE TABLE IF NOT EXISTS layout_db
(
  layout_id          INT NOT NULL AUTO_INCREMENT,
  layout_name        VARCHAR(32),
  layout_description TEXT,
  preview_image_path TEXT,
  PRIMARY KEY (layout_id)
);

CREATE TABLE IF NOT EXISTS users_db
(
  user_id          INT         NOT NULL AUTO_INCREMENT,
  username         VARCHAR(16) NOT NULL,
  hashed_password  VARCHAR(128),
  hashed_salt      VARCHAR(128),
  salt_length      INT         NOT NULL,
  iteration_number INT         NOT NULL,
  blog_name        VARCHAR(64),
  blog_description TEXT,
  layout_id        INT,
  theme_color      CHAR(7),
  avatar_path      TEXT,
  PRIMARY KEY (user_id),
  FOREIGN KEY (layout_id) REFERENCES layout_db (layout_id)
);

CREATE TABLE IF NOT EXISTS article_db
(
  article_id         INT NOT NULL AUTO_INCREMENT,
  title              VARCHAR(64),
  content            TEXT,
  created_time       TIMESTAMP NOT NULL,
  edit_time          TIMESTAMP,
  number_of_likes    INT NOT NULL DEFAULT 0,
  number_of_dislikes INT NOT NULL DEFAULT 0,
  author_id          INT NOT NULL,
  PRIMARY KEY (article_id),
  FOREIGN KEY (author_id) REFERENCES users_db (user_id)
);

CREATE TABLE IF NOT EXISTS comment_db
(
  comment_id         INT NOT NULL AUTO_INCREMENT,
  body               TEXT,
  created_time       TIMESTAMP NOT NULL,
  edit_time          TIMESTAMP,
  number_of_likes    INT NOT NULL DEFAULT 0,
  number_of_dislikes INT NOT NULL DEFAULT 0,
  article_id         INT NOT NULL,
  commenter_id       INT NOT NULL,
  PRIMARY KEY (comment_id),
  FOREIGN KEY (article_id) REFERENCES article_db (article_id),
  FOREIGN KEY (commenter_id) REFERENCES users_db (user_id)
);



