SELECT article_id, title, brief, created_time, edit_time, number_of_likes, number_of_dislikes
FROM article_db WHERE author_id = 1;

SELECT article_id, title, content, brief, created_time, edit_time, number_of_likes, number_of_dislikes, author_id
FROM article_db WHERE article_id = 1;

SELECT user.user_id, username, avatar_path, blog_name, blog_description, theme_color, layout_id
FROM article_db as article, users_db as user
WHERE article.author_id = user.user_id AND article.article_id = 6;


