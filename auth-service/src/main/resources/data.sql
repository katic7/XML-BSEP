-- permissions
insert into permission (id, name) values (1, 'CREATE');
insert into permission (id, name) values (2, 'READ');
insert into permission (id, name) values (3, 'LockUser');
insert into permission (id, name) values (4, 'UnlockUser');
insert into permission (id, name) values (5, 'GetAgents');
insert into permission (id, name) values (6, 'BlockComment');
insert into permission (id, name) values (7, 'ApproveComment');
insert into permission (id, name) values (8, 'GetComments');
insert into permission (id, name) values (9, 'ModifyContent');
insert into permission (id, name) values (10, 'AddContent');
insert into permission (id, name) values (11, 'GetClients');


-- roles
insert into roles (id, name) values (1, 'ROLE_ADMIN');
insert into roles (id, name) values (2, 'ROLE_AGENT');
insert into roles (id, name) values (3, 'ROLE_USER');

-- users
insert into users (id, name, surname, address_id, email, enabled, password, non_locked, date_of_birth, telephone) 
values (1, 'Nemanja', 'Dime', 4,'nemanja@gmail.com', 1, '$2a$10$tlHY4ACO1oM5KR5eFRNdaOcIfOR0ZQXEnCB0TjnBgHPIOWHIfZN7K', 1, '1996-10-07', '060696969');
insert into users (id, name, surname, address_id, email, enabled, password, non_locked, date_of_birth, telephone) 
values (2, 'Velja', 'Veljkovic', 3,'dimsic@gmail.com', 0, '$2a$10$tlHY4ACO1oM5KR5eFRNdaOcIfOR0ZQXEnCB0TjnBgHPIOWHIfZN7K', 0, '1996-08-09', '0214750252');

-- role-permissions
insert into role_permissions (permission_id, role_id) values (2, 1);


insert into role_permissions (permission_id, role_id) values (1, 3);
insert into role_permissions (permission_id, role_id) values (2, 3);
insert into role_permissions (permission_id, role_id) values (3, 3);

-- user-roles
insert into user_roles (user_id, role_id) 
values (1, 1);
insert into user_roles (user_id, role_id) 
values (2, 1);