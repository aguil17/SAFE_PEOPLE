CREATE DATABASE IF NOT EXISTS safepeople_buscador;


/* TABLA INCIDENT */
CREATE TABLE IF NOT EXISTS incident (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incident_type ENUM('fire','accident','robbery') NOT NULL,
    description_incident VARCHAR(600) NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    photo VARCHAR(90000) NOT NULL,
    delete_at TIMESTAMP NULL DEFAULT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_location INT,
    city_name VARCHAR(50) NOT NULL,
    district_name VARCHAR(150) NOT NULL,
    description_location VARCHAR(600) NOT NULL,
    reference VARCHAR(600) NOT NULL,
    latitude DECIMAL(9,6) NOT NULL,
    longitude DECIMAL(9,6) NOT NULL,
    id_user INT,
    username VARCHAR(100),
    password_user VARCHAR(255),
    role ENUM('user','admin','informant'),
    id_person INT,
    person_name VARCHAR(50),
    person_last_name VARCHAR(50),
    dni char(8),
    email VARCHAR(100),
    cellphone VARCHAR(15),
    password_person VARCHAR(255),
    birthdate DATE,
    gender ENUM('male','female','undefined')
    );

CREATE TABLE IF NOT EXISTS material (
    id INT AUTO_INCREMENT PRIMARY KEY,
    material_type VARCHAR(50) NOT NULL, -- TIPOS DE MATERIAL LO DEFINE EL FRONTEND
    description VARCHAR(500) NOT NULL,
    quantity INT UNSIGNED NOT NULL DEFAULT 0,
    material_condition ENUM('new', 'used', 'damaged') NOT NULL DEFAULT 'damaged',
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_incident INT,
    FOREIGN KEY (id_incident) REFERENCES incident(id)
    );

/* TABLA WOUNDED */
CREATE TABLE IF NOT EXISTS wounded (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       quantity INT UNSIGNED NOT NULL DEFAULT 0,
                                       name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    wounded_type VARCHAR(100) NOT NULL, -- TIPO DE HERIDA
    age INT UNSIGNED NOT NULL, -- EDAD DEL HERIDO
    gender ENUM('male','female','undefined') NOT NULL,
    health_status ENUM('stable','serious_stable','serious_unstable','extremely_serious') NOT NULL, -- ESTABLE, GRAVE ESTABLE, INESTBALE, EXTREMADAMENTE GRAVE
    vital_status ENUM('alive', 'deceased') NOT NULL, -- CONDICION VIVO O MUERTO.
    type_enjury  VARCHAR(100) NOT NULL, -- TIPO DE LESION, FRACTURA, CONTUSION ETC.
    description_enjury VARCHAR(500) NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_incident INT,
    FOREIGN KEY (id_incident) REFERENCES incident(id)
    );

/* TABLA INCIDENT_INFORMANT */
CREATE TABLE IF NOT EXISTS incident_informant (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    cellphone VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    id_incident INT,   -- ID del incidente
    assignment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_incident) references incident(id)
    );


