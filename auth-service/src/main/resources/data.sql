insert into users(name, email, password, username) values ('Veljko', 'veljko@gmail.com', '$2a$10$91fwnNofHMjvBLsegKMmC.Elrc8mVam7zznBfF2L5gox2qA3kBkbe', 'veljko');

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_SYSTEMADMIN');

INSERT INTO user_roles(user_id, role_id) VALUES(1,2);