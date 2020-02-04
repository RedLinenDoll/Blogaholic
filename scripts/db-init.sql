DROP TABLE IF EXISTS comment_db;
DROP TABLE IF EXISTS article_db;
DROP TABLE IF EXISTS subscription_db;

DROP TABLE IF EXISTS users_db;
DROP TABLE IF EXISTS layout_db;

CREATE TABLE IF NOT EXISTS layout_db
(
    layout_id          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    layout_name        VARCHAR(32),
    layout_description TEXT,
    preview_image_path TEXT,
    PRIMARY KEY (layout_id)
);

CREATE TABLE IF NOT EXISTS users_db
(
    user_id           INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username          VARCHAR(16)  NOT NULL UNIQUE,
    hashed_password   VARCHAR(128),
    hashed_salt       VARCHAR(128),
    salt_length       INT NOT NULL DEFAULT -1,
    iteration_number  INT NOT NULL DEFAULT -1,
    blog_name         VARCHAR(64),
    blog_description  VARCHAR(256),
    layout_id         INT UNSIGNED DEFAULT 1,
    theme_color       CHAR(7),
    avatar_path       VARCHAR(64),
    first_name        VARCHAR(64),
    last_name         VARCHAR(64),
    date_of_birth     DATE,
    self_introduction VARCHAR(256),
    share_real_name_info BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (user_id),
    FOREIGN KEY (layout_id) REFERENCES layout_db (layout_id)
);

CREATE TABLE IF NOT EXISTS article_db
(
    article_id         INT UNSIGNED NOT NULL AUTO_INCREMENT,
    title              VARCHAR(64),
    content            TEXT,
    brief              VARCHAR(128)          DEFAULT LEFT(content, 96),
    created_time       TIMESTAMP    NOT NULL DEFAULT NOW(),
    edit_time          TIMESTAMP    NULL     DEFAULT NULL,
    number_of_likes    INT UNSIGNED NOT NULL DEFAULT 0,
    number_of_dislikes INT UNSIGNED NOT NULL DEFAULT 0,
    author_id          INT UNSIGNED NOT NULL,
    PRIMARY KEY (article_id),
    FOREIGN KEY (author_id) REFERENCES users_db (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comment_db
(
    comment_id         INT UNSIGNED NOT NULL AUTO_INCREMENT,
    body               TEXT,
    created_time       TIMESTAMP    NOT NULL DEFAULT NOW(),
    edit_time          TIMESTAMP    NULL     DEFAULT NULL,
    number_of_likes    INT UNSIGNED NOT NULL DEFAULT 0,
    number_of_dislikes INT UNSIGNED NOT NULL DEFAULT 0,
    target_article_id  INT UNSIGNED,
    target_comment_id  INT UNSIGNED,
    commenter_id       INT UNSIGNED NOT NULL,
    PRIMARY KEY (comment_id),
    CONSTRAINT comment_on_article FOREIGN KEY (target_article_id) REFERENCES article_db (article_id) ON DELETE CASCADE,
    CONSTRAINT comment_on_comment FOREIGN KEY (target_comment_id) REFERENCES comment_db (comment_id) ON DELETE CASCADE,
    FOREIGN KEY (commenter_id) REFERENCES users_db (user_id) ON DELETE CASCADE,
    CONSTRAINT only_one_target CHECK ((target_article_id IS NULL) OR (target_comment_id IS NULL))
);

ALTER TABLE comment_db
    AUTO_INCREMENT = 3000;

ALTER TABLE users_db MODIFY avatar_path VARCHAR (500);


CREATE TABLE IF NOT EXISTS subscription_db (
    follower_id INT UNSIGNED NOT NULL,
    publisher_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (follower_id, publisher_id),
    FOREIGN KEY (follower_id) REFERENCES users_db (user_id) ON DELETE CASCADE,
    FOREIGN KEY (publisher_id) REFERENCES users_db (user_id) ON DELETE CASCADE
);

