CREATE TABLE addition
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255)
);
CREATE TABLE address
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  city VARCHAR(255) NOT NULL,
  street VARCHAR(255) NOT NULL,
  building_no INT NOT NULL,
  apartment_no INT,
  postcode VARCHAR(8) NOT NULL,
  country VARCHAR(50) NOT NULL
);
CREATE TABLE client
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  pesel BIGINT NOT NULL,
  nip BIGINT,
  email VARCHAR(255) NOT NULL,
  phone_number VARCHAR(25) NOT NULL,
  pass VARCHAR(255) NOT NULL,
  address_id BIGINT NOT NULL,
  register_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_date TIMESTAMP
);
CREATE TABLE hotel
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  phone_number VARCHAR(25) NOT NULL,
  email VARCHAR(255) NOT NULL,
  address_id BIGINT NOT NULL
);
CREATE TABLE hotel_client
(
  client_id BIGINT NOT NULL,
  hotel_id BIGINT NOT NULL,
  PRIMARY KEY ( client_id, hotel_id )
);
CREATE TABLE hotel_user
(
  hotel_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  PRIMARY KEY ( hotel_id, user_id )
);
CREATE TABLE price
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  room_type VARCHAR(10),
  person_type VARCHAR(10) NOT NULL,
  value INT NOT NULL
);
CREATE TABLE reservation
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  date_from DATE NOT NULL,
  date_to DATE NOT NULL,
  person_count INT NOT NULL,
  date_edit DATE,
  client_id BIGINT NOT NULL,
  status_id BIGINT NOT NULL,
  entry_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_date TIMESTAMP
);
CREATE TABLE room
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  room_no INT NOT NULL,
  name VARCHAR(255),
  description VARCHAR(255),
  bed VARCHAR(5),
  capacity INT,
  hotel_id BIGINT
);
CREATE TABLE room_addition
(
  room_id BIGINT NOT NULL,
  addition_id BIGINT NOT NULL
);
CREATE TABLE room_price
(
  room_id BIGINT NOT NULL,
  price_id BIGINT NOT NULL,
  PRIMARY KEY ( room_id, price_id )
);
CREATE TABLE room_reservation
(
  room_id BIGINT NOT NULL,
  reservation_id BIGINT NOT NULL,
  PRIMARY KEY ( room_id, reservation_id )
);
CREATE TABLE status
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  status_type VARCHAR(255),
  description VARCHAR(255)
);
CREATE TABLE user
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  pesel BIGINT NOT NULL,
  nip BIGINT,
  email VARCHAR(255) NOT NULL,
  address_id BIGINT NOT NULL,
  phone_number VARCHAR(25) NOT NULL,
  pass VARCHAR(255) NOT NULL,
  user_type CHAR(8) DEFAULT 'EMPLOYEE' NOT NULL,
  register_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_date TIMESTAMP
);



INSERT INTO bookingsystem.user (id, first_name, last_name, pesel, nip, email, address_id, phone_number, pass, user_type, register_date, update_date) VALUES (1, 'Zenon', 'Breszka', 90030801234, null, 'z@gmail.pl', 2, '792011166', 'admin', 'ADMIN', '2013-12-16 19:21:07', null);
INSERT INTO bookingsystem.user (id, first_name, last_name, pesel, nip, email, address_id, phone_number, pass, user_type, register_date, update_date) VALUES (2, 'Rysiu', 'Hebel', 80030801234, null, 'r@gmail.pl', 3, '888011166', 'user', 'EMPLOYEE', '2013-12-16 19:21:07', null);