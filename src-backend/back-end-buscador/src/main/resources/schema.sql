CREATE DATABASE IF NOT EXISTS safepeople_buscador;


/* TABLA INCIDENT */
CREATE TABLE IF NOT EXISTS incident (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incident_type ENUM('fire','accident','robbery') NOT NULL,
    description_incident VARCHAR(600) NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    photo VARCHAR(255) NOT NULL,
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
