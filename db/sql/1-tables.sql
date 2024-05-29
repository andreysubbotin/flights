create table airdatas_airport (
	id serial,
	name varchar(255) not null,
	code varchar(31) not null unique,
	lat double precision,
	lon double precision,
	time_zone varchar(63),
	primary key (id)
);

create table airdatas_airline (
	id serial,
	name varchar(255) not null,
	code varchar(31) not null unique,
	country varchar(255),
	archived smallint,
	logo oid,
	primary key (id)
);

create table airdatas_flight (
	id bigserial,
	number integer not null unique,
	airline_id integer not null,
	from_airport_id integer not null,
	to_airport_id integer not null,
	takeoff_date timestamp with time zone not null,
	landing_date timestamp with time zone not null,

	primary key (id),
	constraint fk_flight_airline_id foreign key (airline_id) references airdatas_airline(id),
    constraint fk_flight_from_id foreign key (from_airport_id) references airdatas_airport(id),
    constraint fk_flight_to_id foreign key (to_airport_id) references airdatas_airport(id)
);

create index idx_flight_from_airport_id on airdatas_flight (from_airport_id);
create index idx_flight_to_airport_id on airdatas_flight (to_airport_id);
create index idx_flight_takeoff_date on airdatas_flight (takeoff_date);
create index idx_flight_landing_date on airdatas_flight (landing_date);
