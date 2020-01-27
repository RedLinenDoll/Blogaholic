INSERT INTO layout_db (layout_name, layout_description, preview_image_path)
VALUES ('Clean Blog Layout', 'Tidy and clear, suitable for technology blog (and everything else)', 'layout1.png'),
       ('layout2',
        'On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish.',
        'layout2.jpg');


INSERT INTO users_db (username, hashed_password, hashed_salt, salt_length, iteration_number, blog_name,
                      blog_description, layout_id, theme_color, avatar_path)
VALUES ('jayp', 'dCCxTC0I2ZFjhKWZc', '3tHlj6E', 20, 107, 'Cooking made easy',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
        1, '#aa0000', 'avatar1.jpg'),
       ('anrann', 'IQvcIpeDhP', 'dCCxTC0I', 10, 201, 'Child care pshycology',
        'In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided.',
        1, '#00eedd', 'avatar2.jpg'),
       ('akashp', 'KSTTlCrWS1', '1uuKqI', 30, 410, 'Microbiology research',
        'Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. ',
        1, '#339933', 'avatar7.jpg'),
       ('mandys', '1uuKqIQvcI', 'KWZcKlNA51J5', 40, 110, 'Library science management',
        'Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.',
        1, '#aa33ff', 'avatar4.jpg'),
       ('vilam', 'KlNA51J5KSTTlCrWS1u', 'eDhPNnu9MbD3', 50, 222, 'Child teething',
        'In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. ',
        1, '#eeee33', 'avatar5.jpg');


INSERT INTO article_db (title, content, created_time, edit_time, number_of_likes, number_of_dislikes, author_id)
VALUES ('Cooking curry dish',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.',
        '2020-01-19 23:00:05', '2020-01-22 11:00:02', 20, 10, 3),
       ('Teething issues',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.',
        '2019-12-24 20:30:05', NULL, 0, 8, 3),
       ('Child teething issues',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.',
        '2019-12-24 20:30:05', NULL, 0, 8, 2),
       ('Treating fungal infection',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.',
        '2018-01-25 10:12:05', '2019-01-18 00:08:02', 50, 60, 4),
       ('Child eating disorder',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.',
        '2019-08-19 23:00:05', NULL, 18, 36, 1),
       ('Washing dishes',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.',
        '2020-01-01 12:25:05', NULL, 58, 6, 5);


INSERT INTO comment_db (body, created_time, edit_time, number_of_likes, number_of_dislikes, target_article_id, commenter_id)
VALUES ('On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire.',
        '2019-12-24 20:30:05', NULL, 10, 5, 1, 1),
       ('But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted.',
        '2018-11-06 19:35:18', '2020-01-10 08:20:05', 20, 3, 4, 2),
       ('Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus.',
        '2019-08-06 18:34:08', NULL, 0, 8, 1, 3),
       ('Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae.',
        '2019-08-06 18:34:08', '2020-01-08 12:20:23', 0, 8, 2, 4),
       ('Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae.',
        '2019-08-06 18:34:08', NULL, 5, 12, 3, 5);

INSERT INTO comment_db(body, created_time, edit_time, number_of_likes, number_of_dislikes, target_article_id, target_comment_id, commenter_id)
VALUES ('Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus.',
        '2019-08-06 18:34:08', NULL, 0, 8, NULL, 30000000,3),
       ('Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus.',
        '2019-08-06 18:34:08', NULL, 0, 8, NULL, 30000005,3)
       ;










