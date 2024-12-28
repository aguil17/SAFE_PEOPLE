
    drop table if exists informant;

    create table informant
    (
        id int AUTO_INCREMENT primary key,
        name varchar(100) not null,
        last_name varchar(100) not null,
        cellphone varchar(60) not null,
        email varchar(60) not null,
        creation_date DATETIME not null
    );


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
    );


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
                references location(id);


    drop table if exists incident_informant;

    create table incident_informant
    (
        id_incident int,
        id_informat int
    );

    alter table incident_informant
        add primary key (id_incident,id_informat);


    alter table incident_informant
        add constraint fk_incident
            foreign key (id_incident)
                references incident(id);


    alter table incident_informant
        add constraint fk_informat
            foreign key (id_informat)
                references informant(id);



