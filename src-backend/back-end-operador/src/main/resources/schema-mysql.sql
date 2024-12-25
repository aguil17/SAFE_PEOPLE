
drop table if exists location;

create table location
(
    id int AUTO_INCREMENT primary key,
    city_name varchar(100) not null,
    district_name varchar(100) not null,
    descripcion varchar(100) not null,
    reference varchar(300) not null,
    latitude varchar(100) not null,
    longitude varchar(100) not null,
    creation_date DATETIME not null
)


drop table if exists incident;

create table incident
(
    id int AUTO_INCREMENT primary key,
    incident_type int not null,
    descripcion   varchar(100) not null,
    date          date not null,
    time          time not null,
    photo         varchar(300),
    creation_date DATETIME not null,
    id_location   int
);

alter table incident
add constraint fk_location
foreign key (id_location)
references location(id)

