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
insert into users (id, email, enabled, password, non_locked) 
values (UUID(), 'nemanja@gmail.com', 1, '$2a$10$8bH9N7jBJo6XNlfR.mKhYeD9yoWGJJXJ.rOaCgftdn5SdSwZaOlfq', 1);
insert into users (id, email, enabled, password, non_locked) 
values (UUID(), 'dimsic@gmail.com', 1, '$2a$10$uZzqU5v.G10pFySkUUIbXuNDukV//c19dBD/hiqbYSgyP8pbhTxh2', 1);

-- role-permissions
insert into role_permissions (permission_id, role_id) values (1, 1);
insert into role_permissions (permission_id, role_id) values (2, 1);
insert into role_permissions (permission_id, role_id) values (3, 1);
insert into role_permissions (permission_id, role_id) values (4, 1);
insert into role_permissions (permission_id, role_id) values (5, 1);
insert into role_permissions (permission_id, role_id) values (6, 1);
insert into role_permissions (permission_id, role_id) values (7, 1);
insert into role_permissions (permission_id, role_id) values (8, 1);
insert into role_permissions (permission_id, role_id) values (9, 1);
insert into role_permissions (permission_id, role_id) values (10, 1);
insert into role_permissions (permission_id, role_id) values (11, 1);

insert into role_permissions (permission_id, role_id) values (1, 3);
insert into role_permissions (permission_id, role_id) values (2, 3);
insert into role_permissions (permission_id, role_id) values (3, 3);

-- user-roles
insert into user_roles (user_id, role_id) 
values ((select id from users where email = 'nemanja@gmail.com'), 1);
insert into user_roles (user_id, role_id) 
values ((select id from users where email = 'dimsic@gmail.com'), 1);