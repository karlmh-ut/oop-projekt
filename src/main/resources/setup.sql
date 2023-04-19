-- noinspection SqlNoDataSourceInspectionForFile

------------------------------------------------------------------------------------------------------------------------
CREATE TABLE subforums (
    name varchar UNIQUE
);

CREATE TABLE threads (
    id integer PRIMARY KEY AUTO_INCREMENT, -- AUTOINCREMENT --> AUTO_INCREMENT
    subforum varchar,
    title varchar,
    initial_post_time datetime,
    edited_post_time datetime,
    author varchar,
    content varchar
);

CREATE TABLE likes (
    post_or_thread_id integer,
    liked_by varchar
);

CREATE TABLE comments (
    id integer PRIMARY KEY AUTO_INCREMENT,
    thread integer,
    initial_post_time datetime,
    edited_post_time datetime,
    author_username varchar,
    content varchar
);

CREATE TABLE users (
    username varchar UNIQUE,
    session varchar UNIQUE,
    is_admin boolean
);

INSERT INTO subforums(name) VALUES ('Java');
INSERT INTO subforums(name) VALUES ('Python');
INSERT INTO subforums(name) VALUES ('C');
INSERT INTO subforums(name) VALUES ('testimine');
------------------------------------------------------------------------------------------------------------------------


-- debug and testing etc pls ignore
INSERT INTO users(username, is_admin) VALUES ('esimenekasutaja', false);
INSERT INTO users(username, is_admin) VALUES ('teinekasutaja', false);
INSERT INTO users(username, is_admin) VALUES ('kolmaskasutaja', false);
INSERT INTO users(username, is_admin) VALUES ('admin', true);

INSERT INTO threads(subforum, title, initial_post_time, edited_post_time, author, content) VALUES ('Python', 'veider süntaks', '2058-08-10 10:15:00', NULL, 'esimenekasutaja', 'miks püütonis semikooloneid pole?? imelik keel');
INSERT INTO comments(thread, initial_post_time, edited_post_time, author_username, content) VALUES (1, '2058-08-10 10:18:00', NULL, 'teinekasutaja', 'ok lol');
INSERT INTO comments(thread, initial_post_time, edited_post_time, author_username, content) VALUES (1, '2058-08-10 10:20:00', NULL, 'kolmaskasutaja', 'xd');

