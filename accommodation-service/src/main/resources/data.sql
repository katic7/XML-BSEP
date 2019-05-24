insert into address (id, longitude, latitude, state, town, street, street_number, postal_code) values (1, 1, 1, 'Srbija', 'Novi Sad', 'Radnicka', 41, 21000);
insert into address (id, longitude, latitude, state, town, street, street_number, postal_code) values (2, 1, 1, 'Srbija', 'Novi Sad', 'Stevana Musica', 11, 21000);
insert into address (id, longitude, latitude, state, town, street, street_number, postal_code) values (3, 1, 1, 'Srbija', 'Novi Sad', 'Vojvode Misica', 24, 21000);
insert into address (id, longitude, latitude, state, town, street, street_number, postal_code) values (4, 1, 1, 'Srbija', 'Beocin', 'Desanke Maksimovic', 20, 21000);

insert into category (id, name) values (1, '1*');
insert into category (id, name) values (2, '2*');
insert into category (id, name) values (3, '3*');
insert into category (id, name) values (4, '4*');
insert into category (id, name) values (5, '5*');

insert into type (id, name) values (1, 'hotel');
insert into type (id, name) values (2, 'motel');
insert into type (id, name) values (3, 'hostel');

insert into accommodation_object (id, address_id, category_id, days_to_cancel, description, free_cancelation, name, type_id) values (1, 1, 5, 3, 'Description of hotel 1', 0, 'Hotel 1', 1);
insert into accommodation_object (id, address_id, category_id, days_to_cancel, description, free_cancelation, name, type_id) values (2, 2, 5, 3, 'Description of hotel 2', 1, 'Hotel 2', 1);
insert into accommodation_object (id, address_id, category_id, days_to_cancel, description, free_cancelation, name, type_id) values (3, 3, 5, 3, 'Description of hotel 3', 1, 'Hotel 3', 1);
insert into accommodation_object (id, address_id, category_id, days_to_cancel, description, free_cancelation, name, type_id) values (4, 4, 5, 3, 'Description of hotel 4', 0, 'Vila Dimsic', 1);

insert into additional_service (id, included, name, price, accommodation_object) values (1, 1, 'Wifi', 100, 1);
insert into additional_service (id, included, name, price, accommodation_object) values (2, 0, 'Spa', 1100, 1);
insert into additional_service (id, included, name, price, accommodation_object) values (3, 1, 'Parking', 420, 1);

insert into acc_unit_price (id, end_date, price, start_date) values (1, '2019-12-31', 49, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (2, '2019-12-31', 39, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (3, '2019-12-31', 29, '2019-01-01');
insert into acc_unit_price (id, end_date, price, start_date) values (4, '2019-12-31', 19, '2019-01-01');

insert into accommodation_unit (id, balcony, description, image, number_of_beds, rating, accommodation_object, price_id) values (1, 1, 'Description of unit 1', '', 2, 4.99, 1, 1);
insert into accommodation_unit (id, balcony, description, image, number_of_beds, rating, accommodation_object, price_id) values (2, 1, 'Description of unit 1', '', 3, 4.42, 1, 2);
insert into accommodation_unit (id, balcony, description, image, number_of_beds, rating, accommodation_object, price_id) values (3, 0, 'Description of unit 1', '', 4, 2.29, 1, 3);

insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (1, 0, '2019-05-01', 1, '2019-05-10', 0, '2019-04-01', 1, 1);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (2, 0, '2019-05-01', 1, '2019-05-10', 0, '2019-04-01', 2, 2);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (3, 0, '2019-05-01', 1, '2019-05-10', 0, '2019-04-01', 3, 2);
insert into reservation (id, active, begin_date, completed, end_date, price, reservation_date, accommodation_unit, user) values (4, 0, '2019-05-01', 1, '2019-05-10', 0, '2019-04-01', 4, 2);

