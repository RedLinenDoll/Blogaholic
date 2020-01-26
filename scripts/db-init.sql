DROP TABLE IF EXISTS comment_db;
DROP TABLE IF EXISTS article_db;
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
    user_id          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username         VARCHAR(16)  NOT NULL UNIQUE,
    hashed_password  VARCHAR(128),
    hashed_salt      VARCHAR(128),
    salt_length      INT UNSIGNED NOT NULL,
    iteration_number INT UNSIGNED NOT NULL,
    blog_name        VARCHAR(64),
    blog_description TEXT,
    layout_id        INT UNSIGNED DEFAULT 1,
    theme_color      CHAR(7),
    avatar_path      TEXT,
    PRIMARY KEY (user_id),
    FOREIGN KEY (layout_id) REFERENCES layout_db (layout_id)
);

CREATE TABLE IF NOT EXISTS article_db
(
    article_id         INT UNSIGNED NOT NULL AUTO_INCREMENT,
    title              VARCHAR(64),
    content            TEXT,
    brief              VARCHAR(128)          DEFAULT LEFT(content, 96),
    created_time       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
    created_time       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
    AUTO_INCREMENT = 30000000;



