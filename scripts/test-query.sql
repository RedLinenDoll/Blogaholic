SELECT article_id, title, brief, created_time, edit_time, number_of_likes, number_of_dislikes
FROM article_db WHERE author_id = 1;

SELECT article_id, title, content, brief, created_time, edit_time, number_of_likes, number_of_dislikes, author_id
FROM article_db WHERE article_id = 1;

SELECT user.user_id, username, avatar_path, blog_name, blog_description, theme_color, layout_id
FROM article_db as article, users_db as user
WHERE article.author_id = user.user_id AND article.article_id = 6;


UPDATE users_db SET
                    avatar_path = 'avatar5.jpg',
                    layout_id = 1,
                    theme_color = '#3f99ae'
WHERE username = 'anran';

SELECT article_id, title, brief, created_time, edit_time, number_of_likes, number_of_dislikes
FROM article_db
ORDER BY created_time DESC
LIMIT 10;


UPDATE users_db
SET first_name = 'new first name',
    last_name ='new last name',
    date_of_birth = '1994-09-01',
    self_introduction = 'Hi this is A'
WHERE user_id = 2;


DELETE FROM users_db
WHERE user_id > 6;

SELECT username, avatar_path, self_introduction, blog_name, layout_id, theme_color FROM users_db WHERE user_id = 1;
SELECT username, avatar_path, self_introduction, first_name, last_name, date_of_birth, blog_name, layout_id, theme_color FROM users_db WHERE user_id = 1;

UPDATE users_db SET first_name = 'A', last_name ='B', date_of_birth = '1990-01-02', self_introduction = 'ih', share_real_name_info = false WHERE user_id = 12;
