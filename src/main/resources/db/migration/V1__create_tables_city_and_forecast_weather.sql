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

insert into city
    values (4310, 'Araraquara', 'SP', 'BR');

insert into forecast_weather (forecast_date, rain_probability, rain_precipitation, min_temp, max_temp, city_id_city)
    values ('2020-12-22', 83, 30, 23, 30, 4310);