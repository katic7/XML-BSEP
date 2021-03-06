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
insert into permission (id, name) values (12, 'ModifyUserContent');
insert into permission (id, name) values (13, 'AddAccUnit');
insert into permission (id, name) values (14, 'DeleteAccUnit');
insert into permission (id, name) values (15, 'ModifyAccUnit');
insert into permission (id, name) values (16, 'ApproveReservations');
insert into permission (id, name) values (17, 'AddAgents');
insert into permission (id, name) values (18, 'AddUsers');
insert into permission (id, name) values (19, 'DeleteUsers');
insert into permission (id, name) values (20, 'BlockUsers');
insert into permission (id, name) values (21, 'DeleteAgents');
insert into permission (id, name) values (22, 'IssueCert');
insert into permission (id, name) values (23, 'RevokeCert');
insert into permission (id, name) values (24, 'AddPrice');
insert into permission (id, name) values (25, 'PublishComment');
insert into permission (id, name) values (26, 'GetAccUnit');
insert into permission (id, name) values (27, 'MakeReservation');
insert into permission (id, name) values (28, 'GetAdditionalServices');
insert into permission (id, name) values (29, 'DeleteCodebook');
insert into permission (id, name) values (30, 'EditCodebook');
insert into permission (id, name) values (31, 'UpdateUser');
insert into permission (id, name) values (32, 'CompleteReservation');
insert into permission (id, name) values (33, 'GetCertificates');
insert into permission (id, name) values (34, 'IssueCert');
insert into permission (id, name) values (35, 'GetSoftwares');
insert into permission (id, name) values (36, 'ExchangeCertificate');
insert into permission (id, name) values (37, 'GetRevokedCert');

-- roles
insert into roles (id, name) values (1, 'ROLE_ADMIN');
insert into roles (id, name) values (2, 'ROLE_AGENT');
insert into roles (id, name) values (3, 'ROLE_USER');
insert into roles (id, name) values (4, 'ROLE_SYSTEM_ADMIN');

-- users
insert into users (id, name, surname, address_id, email, enabled, password, non_locked, date_of_birth, telephone) 
values (1, 'Nemanja', 'Dime', 4,'nemanja@gmail.com', 1, '$2a$10$tlHY4ACO1oM5KR5eFRNdaOcIfOR0ZQXEnCB0TjnBgHPIOWHIfZN7K', 1, '1996-10-07', '060696969');
insert into users (id, name, surname, address_id, email, enabled, password, non_locked, date_of_birth, telephone) 
values (2, 'Velja', 'Veljkovic', 3,'dimsic@gmail.com', 1, '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, '1996-08-09', '0214750252');
insert into users (id, name, surname, address_id, email, enabled, password, non_locked, date_of_birth, telephone) 
values (3, 'User', 'Useric', 3,'user@gmail.com', 1, '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, '1996-08-09', '03343425152');
insert into users (id, name, surname, address_id, email, enabled, password, non_locked, date_of_birth, telephone) 
values (4, 'Agent', 'Tajni', 3,'agent@gmail.com', 1, '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, '1997-08-09', '0313355052');
insert into users (id, name, surname, address_id, email, enabled, password, non_locked, date_of_birth, telephone) 
values (5, 'User2', 'Useric2', 3,'user2@gmail.com', 1, '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, '1996-08-09', '03343425152');
insert into users (id, name, surname, address_id, email, enabled, password, non_locked, date_of_birth, telephone) 
values (6, 'User3', 'Useric3', 3,'user3@gmail.com', 0, '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, '1996-08-09', '03743325552');
insert into users (id, name, surname, address_id, email, enabled, password, non_locked, date_of_birth, telephone) 
values (7, 'Agent2', 'Dimsic', 3,'agent2@gmail.com', 1, '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, '1996-10-07', '02543825121');
insert into users (id, name, surname, address_id, email, enabled, password, non_locked, date_of_birth, telephone) 
values (8, 'Agent3', 'Katic', 3,'agent3@gmail.com', 1, '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, '1996-12-03', '02643825122');
insert into users (id, name, surname, address_id, email, enabled, password, non_locked, date_of_birth, telephone) 
values (9, 'Agent4', 'Topolic', 3,'agent4@gmail.com', 1, '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, '1996-08-09', '02643825123');

-- role-permissions
insert into role_permissions (permission_id, role_id) values (1, 4);
insert into role_permissions (permission_id, role_id) values (2, 4);
insert into role_permissions (permission_id, role_id) values (3, 4);
insert into role_permissions (permission_id, role_id) values (4, 4);
insert into role_permissions (permission_id, role_id) values (5, 4);
insert into role_permissions (permission_id, role_id) values (6, 4);
insert into role_permissions (permission_id, role_id) values (7, 4);
insert into role_permissions (permission_id, role_id) values (8, 4);
insert into role_permissions (permission_id, role_id) values (9, 4);
insert into role_permissions (permission_id, role_id) values (10, 4);
insert into role_permissions (permission_id, role_id) values (11, 4);
insert into role_permissions (permission_id, role_id) values (12, 4);
insert into role_permissions (permission_id, role_id) values (13, 4);
insert into role_permissions (permission_id, role_id) values (14, 4);
insert into role_permissions (permission_id, role_id) values (15, 4);
insert into role_permissions (permission_id, role_id) values (16, 4);
insert into role_permissions (permission_id, role_id) values (17, 4);
insert into role_permissions (permission_id, role_id) values (18, 4);
insert into role_permissions (permission_id, role_id) values (19, 4);
insert into role_permissions (permission_id, role_id) values (20, 4);
insert into role_permissions (permission_id, role_id) values (21, 4);
insert into role_permissions (permission_id, role_id) values (22, 4);
insert into role_permissions (permission_id, role_id) values (23, 4);
insert into role_permissions (permission_id, role_id) values (24, 4);
insert into role_permissions (permission_id, role_id) values (25, 4);
insert into role_permissions (permission_id, role_id) values (26, 4);
insert into role_permissions (permission_id, role_id) values (27, 4);
insert into role_permissions (permission_id, role_id) values (28, 4);
insert into role_permissions (permission_id, role_id) values (29, 4);
insert into role_permissions (permission_id, role_id) values (30, 4);
insert into role_permissions (permission_id, role_id) values (31, 4);
insert into role_permissions (permission_id, role_id) values (32, 4);
insert into role_permissions (permission_id, role_id) values (33, 4);
insert into role_permissions (permission_id, role_id) values (34, 4);
insert into role_permissions (permission_id, role_id) values (35, 4);
insert into role_permissions (permission_id, role_id) values (36, 4);
insert into role_permissions (permission_id, role_id) values (37, 4);

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
insert into role_permissions (permission_id, role_id) values (12, 1);
insert into role_permissions (permission_id, role_id) values (13, 1);
insert into role_permissions (permission_id, role_id) values (14, 1);
insert into role_permissions (permission_id, role_id) values (15, 1);
insert into role_permissions (permission_id, role_id) values (16, 1);
insert into role_permissions (permission_id, role_id) values (17, 1);
insert into role_permissions (permission_id, role_id) values (18, 1);
insert into role_permissions (permission_id, role_id) values (19, 1);
insert into role_permissions (permission_id, role_id) values (20, 1);
insert into role_permissions (permission_id, role_id) values (21, 1);
insert into role_permissions (permission_id, role_id) values (24, 1);
insert into role_permissions (permission_id, role_id) values (25, 1);
insert into role_permissions (permission_id, role_id) values (26, 1);
insert into role_permissions (permission_id, role_id) values (27, 1);
insert into role_permissions (permission_id, role_id) values (28, 1);
insert into role_permissions (permission_id, role_id) values (29, 1);
insert into role_permissions (permission_id, role_id) values (30, 1);
insert into role_permissions (permission_id, role_id) values (31, 1);

insert into role_permissions (permission_id, role_id) values (24, 2);
insert into role_permissions (permission_id, role_id) values (13, 2);
insert into role_permissions (permission_id, role_id) values (9, 2);
insert into role_permissions (permission_id, role_id) values (25, 2);
insert into role_permissions (permission_id, role_id) values (26, 2);
insert into role_permissions (permission_id, role_id) values (27, 2);
insert into role_permissions (permission_id, role_id) values (31, 2);
insert into role_permissions (permission_id, role_id) values (32, 2);

insert into role_permissions (permission_id, role_id) values (25, 3);
insert into role_permissions (permission_id, role_id) values (27, 3);
insert into role_permissions (permission_id, role_id) values (31, 3);

-- user-roles
insert into user_roles (user_id, role_id) 
values (1, 1);
insert into user_roles (user_id, role_id) 
values (2, 4);
insert into user_roles (user_id, role_id) 
values (3, 3);
insert into user_roles (user_id, role_id) 
values (4, 2);
insert into user_roles (user_id, role_id) 
values (5, 3);
insert into user_roles (user_id, role_id) 
values (6, 3);
insert into user_roles (user_id, role_id) 
values (7, 2);
insert into user_roles (user_id, role_id) 
values (8, 2);
insert into user_roles (user_id, role_id) 
values (9, 2);

--address
insert into address (id, longitude, latitude, state, town, street, street_number, postal_code) values (1, 19.8505121, 45.2500775, 'Srbija', 'Novi Sad', 'Radnicka', 41, 21000);
insert into address (id, longitude, latitude, state, town, street, street_number, postal_code) values (2, 19.8519715, 45.2500097, 'Srbija', 'Novi Sad', 'Stevana Musica', 11, 21000);
insert into address (id, longitude, latitude, state, town, street, street_number, postal_code) values (3, 19.8540348, 45.253186, 'Srbija', 'Novi Sad', 'Vojvode Misica', 24, 21000);
insert into address (id, longitude, latitude, state, town, street, street_number, postal_code) values (4, 19.7165794, 45.2076382, 'Srbija', 'Beocin', 'Desanke Maksimovic', 20, 21000);

--category
insert into category (id, name) values (1, '1*');
insert into category (id, name) values (2, '2*');
insert into category (id, name) values (3, '3*');
insert into category (id, name) values (4, '4*');
insert into category (id, name) values (5, '5*');

--type
insert into type (id, name) values (1, 'hotel');
insert into type (id, name) values (2, 'motel');
insert into type (id, name) values (3, 'hostel');

--accommodation_object
insert into accommodation_object (id, address_id, category_id, days_to_cancel, description, free_cancelation, name, type_id) values (1, 1, 5, 3, 'Description of hotel 1', 0, 'Hotel 1', 1);
insert into accommodation_object (id, address_id, category_id, days_to_cancel, description, free_cancelation, name, type_id) values (2, 2, 5, 3, 'Description of hotel 2', 1, 'Hotel 2', 1);
insert into accommodation_object (id, address_id, category_id, days_to_cancel, description, free_cancelation, name, type_id) values (3, 3, 5, 3, 'Description of hotel 3', 1, 'Hotel 3', 1);
insert into accommodation_object (id, address_id, category_id, days_to_cancel, description, free_cancelation, name, type_id) values (4, 4, 5, 3, 'Description of hotel 4', 0, 'Vila Dimsic', 1);

--additional_service
insert into additional_service (id, included, name, price, accommodation_object) values (1, 1, 'Wifi', 19, 1);
insert into additional_service (id, included, name, price, accommodation_object) values (2, 0, 'Spa', 700, 1);
insert into additional_service (id, included, name, price, accommodation_object) values (3, 1, 'Parking', 250, 1);
insert into additional_service (id, included, name, price, accommodation_object) values (4, 1, 'Breakfast', 30, 1);
insert into additional_service (id, included, name, price, accommodation_object) values (5, 1, 'Bed and breakfast', 330, 1);
insert into additional_service (id, included, name, price, accommodation_object) values (6, 1, 'Boarding house', 400, 1);
insert into additional_service (id, included, name, price, accommodation_object) values (7, 1, 'All Inclusive', 450, 1);
insert into additional_service (id, included, name, price, accommodation_object) values (8, 1, 'Pets allowed', 120, 1);
insert into additional_service (id, included, name, price, accommodation_object) values (9, 1, 'TV', 15, 1);
insert into additional_service (id, included, name, price, accommodation_object) values (10, 1, 'Kitchen', 9, 1);
insert into additional_service (id, included, name, price, accommodation_object) values (11, 1, 'Private bath', 79, 1);

--acc_unit_price
insert into acc_unit_price (id, end_date, price, start_date) values (1, '2019-12-31', 49, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (2, '2019-12-31', 39, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (3, '2019-12-31', 29, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (4, '2019-12-31', 19, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (5, '2019-12-31', 19, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (6, '2019-12-31', 50, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (7, '2019-12-31', 139, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (8, '2019-12-31', 77, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (9, '2019-12-31', 55, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (10, '2019-12-31', 87, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (11, '2019-12-31', 187, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (12, '2019-12-31', 36, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (13, '2019-12-31', 83, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (14, '2019-12-31', 64, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (15, '2019-12-31', 38, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (16, '2019-12-31', 122, '2019-01-01');

--accommodation_unit
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (1, 1, 'Description of unit 1', 2, 4.99, 1, 1);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (2, 1, 'Description of unit 2', 3, 4.42, 2, 2);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (3, 0, 'Description of unit 3', 4, 2.29, 3, 3);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (4, 0, 'Description of unit 4', 4, 2.29, 4, 4);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (5, 0, 'Description of unit 5', 2, 4.35, 1, 5);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (6, 1, 'Description of unit 6', 4, 3.99, 1, 6);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (7, 1, 'Description of unit 7', 2, 4.25, 1, 7);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (8, 0, 'Description of unit 8', 2, 4.05, 2, 8);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (9, 1, 'Description of unit 9', 4, 4.28, 2, 9);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (10, 1, 'Description of unit 10', 3, 4.22, 2, 10);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (11, 0, 'Description of unit 11', 1, 2.29, 3, 11);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (12, 1, 'Description of unit 12', 3, 4.67, 3, 12);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (13, 0, 'Description of unit 13', 2, 2.29, 3, 13);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (14, 0, 'Description of unit 14', 4, 3.39, 4, 14);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (15, 0, 'Description of unit 15', 3, 4.19, 4, 15);
insert into accommodation_unit (id, balcony, description, number_of_beds, rating, accommodation_object, price_id) values (16, 0, 'Description of unit 16', 2, 3.23, 4, 16);

--accommodation_unit_additional_services
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (1, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (1, 2);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (1, 3);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (1, 7);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (1, 10);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (1, 9);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (1, 11);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (2, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (2, 9);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (3, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (3, 9);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (4, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (4, 9);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (5, 9);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (5, 3);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (5, 7);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (6, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (6, 6);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (7, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (7, 3);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (7, 6);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (7, 9);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (8, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (8, 3);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (8, 5);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (9, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (9, 3);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (9, 6);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (9, 9);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (10, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (10, 2);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (10, 3);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (10, 7);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (11, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (11, 5);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (12, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (12, 2);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (12, 3);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (12, 7);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (12, 9);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (13, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (13, 5);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (14, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (14, 3);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (14, 6);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (15, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (15, 2);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (15, 3);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (16, 1);
insert into accommodation_unit_additional_services (accommodation_unit_id, additional_services_id) values (15, 9);

--reservation
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (1, 0, '2019-05-01', 0, '2019-05-10', 10, '2019-04-01', 1, 1);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (2, 0, '2019-05-01', 0, '2019-05-10', 20, '2019-04-01', 2, 2);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (3, 0, '2019-05-01', 0, '2019-05-10', 30, '2019-04-01', 3, 2);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (4, 0, '2019-10-01', 0, '2019-10-10', 40, '2019-04-01', 2, 1);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (5, 0, '2019-09-01', 0, '2019-09-10', 50, '2019-04-01', 2, 1);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (6, 0, '2019-10-17', 0, '2019-10-23', 60, '2019-04-01', 1, 1);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (7, 0, '2019-10-17', 0, '2019-10-23', 323, '2019-04-01', 2, 1);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (8, 0, '2019-10-17', 0, '2019-10-23', 123, '2019-04-01', 2, 1);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (9, 0, '2019-10-17', 0, '2019-10-23', 123, '2019-04-01', 11, 3);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (10, 0, '2019-03-17', 0, '2019-03-23', 123, '2019-04-01', 10, 3);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (11, 0, '2019-07-02', 0, '2019-07-04', 123, '2019-04-01', 9, 3);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (12, 0, '2019-07-03', 0, '2019-07-07', 123, '2019-04-01', 14, 3);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (13, 0, '2019-07-01', 0, '2019-07-04', 123, '2019-04-01', 12, 3);

-- agents
insert into agents (pib, id, acc_obj_id) values ("123456", 4, 3);
insert into agents (pib, id, acc_obj_id) values ("654321", 7, 4);
insert into agents (pib, id, acc_obj_id) values ("678910", 8, 2);
insert into agents (pib, id, acc_obj_id) values ("109876", 9, 1);
