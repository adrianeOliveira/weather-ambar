CREATE TABLE city(
	id_city int not null primary key,
	name varchar(50) null,
	state varchar(50) null,
	country varchar(50) null
);

CREATE TABLE forecast_weather(
	id_forecast_weather null primary key auto_increment,
	forecast_date date null,
	rain_probability int null,
	rain_precipitation int null,
	min_temp int null,
	max_temp int null,
	city_id_city int null,
	foreign key(city_id_city) references city(id_city)
);