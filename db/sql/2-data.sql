insert into airdatas_airport (name, code, lat, lon, time_zone)
values ('London Heathrow Airport', 'LHR', 51.4775, -0.461389, '+02:00'),
('Amsterdam Airport Schiphol', 'AMS', 52.3081, 4.76417, '+02:00'),
('Paris Charles de Gaulle', 'CDG', 	49.0097, 2.54778, '+02:00'),
('Frankfurt International Airport', 'FRA', 	50.0331, 	8.57056, '+02:00'),
('Sheremetyevo', 'SVO', 55.972500, 37.413056, '+04:00'),
('Dubai International Airport', 'DXB', 25.252778, 55.364444, '+04:00');

insert into airdatas_airline (name, code, country, archived)
values ('FlyDubai', 'FZ', 'United Arab Emirates', 0),
('Lufthansa', 'LH', 'Germany', 0),
('Air France', 'AF', 'France', 0),
('Ryanair', 'FR', 'Ireland', 0),
('Aeroflot', 'SU', 'Russian Federation', 0),
('British Airways', 'BA', 'United Kingdom', 0),
('AirBerlin', 'AB', 'Germany', 1),
('Transaero', 'UN', 'Russian Federation', 1);

insert into airdatas_flight (number, airline_id, from_airport_id, to_airport_id, takeoff_date, landing_date)
values
(150, 1, 6, 5, '2024-05-19 18:00', '2024-05-19 23:00'),
(180, 2, 4, 3, '2024-06-01 14:25', '2024-06-01 16:15'),
(682, 5, 5, 6, '2024-06-11 22:40', '2024-06-12 02:50'),
(381, 4, 3, 1, '2024-05-23 17:45', '2024-05-23 19:20'),
(68, 4, 2, 1, '2024-05-23 17:45', '2024-05-23 19:20'),
(226, 6, 1, 6, '2024-06-05 18:55', '2024-06-05 00:20'),
(53, 3, 3, 6, '2024-06-05 08:00', '2024-06-05 13:20'),
(90, 4, 4, 1, '2024-06-01 11:45', '2024-06-01 13:05'),
(112, 1, 4, 6, '2024-05-30 03:10', '2024-05-30 08:15'),
(203, 3, 4, 3, '2024-05-28 11:40', '2024-05-28 13:05');
