-- noinspection SqlNoDataSourceInspectionForFile

INSERT INTO subforums(name) VALUES ('Java');
INSERT INTO subforums(name) VALUES ('Python');
INSERT INTO subforums(name) VALUES ('C');
INSERT INTO subforums(name) VALUES ('testimine');
------------------------------------------------------------------------------------------------------------------------


-- debug and testing etc pls ignore
INSERT INTO users(username) VALUES ('esimenekasutaja');
INSERT INTO users(username) VALUES ('teinekasutaja');
INSERT INTO users(username) VALUES ('kolmaskasutaja');
INSERT INTO users(username) VALUES ('admin');

INSERT INTO threads(subforums_id, title, initial_post_time, edited_post_time, author_id, content) VALUES (2, 'veider süntaks', '2058-08-10 10:15:00', NULL, 1, 'miks püütonis semikooloneid pole?? imelik keel');
INSERT INTO posts(threads_id, initial_post_time, edited_post_time, author_id, content) VALUES (1, '2058-08-10 10:18:00', NULL, 2, 'ok lol');
INSERT INTO posts(threads_id, initial_post_time, edited_post_time, author_id, content) VALUES (1, '2058-08-10 10:20:00', NULL, 3, 'xd');

