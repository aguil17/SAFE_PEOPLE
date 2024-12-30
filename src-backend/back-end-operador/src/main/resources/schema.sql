
CREATE DATABASE IF NOT EXISTS safepeople;

-- Usar la base de datos safepeople
USE safepeople;

/* TABLA PERSON */
CREATE TABLE IF NOT EXISTS person (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(50) NOT NULL,
                                      last_name VARCHAR(50) NOT NULL,
                                      dni char(8) NOT NULL,
                                      email VARCHAR(100) UNIQUE NOT NULL,
                                      cellphone VARCHAR(15) NOT NULL,
                                      password VARCHAR(255) NOT NULL,
                                      birthdate DATE NOT NULL,
                                      gender ENUM('male','female','undefined') NOT NULL,
                                      creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

/* TABLA USER */
CREATE TABLE IF NOT EXISTS user (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(100) NOT NULL,
                                    password VARCHAR(255) NOT NULL,
                                    role ENUM('user','admin','informant') NOT NULL,
                                    id_person INT,
                                    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                    FOREIGN KEY (id_person) REFERENCES person(id)
);

/* TABLA INFORMANT */
CREATE TABLE IF NOT EXISTS informant (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         name VARCHAR(50) NOT NULL,
                                         last_name VARCHAR(50) NOT NULL,
                                         cellphone VARCHAR(15) NOT NULL,
                                         email VARCHAR(100) UNIQUE NOT NULL,
                                         creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

/* TABLA LOCATION */
CREATE TABLE IF NOT EXISTS location (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        city_name VARCHAR(50) NOT NULL,
                                        distric_name VARCHAR(150) NOT NULL,
                                        description VARCHAR(600) NOT NULL,
                                        reference VARCHAR(600) NOT NULL,
                                        latitude DECIMAL(9,6) NOT NULL,
                                        longitude DECIMAL(9,6) NOT NULL,
                                        creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

/* TABLA INCIDENT */
CREATE TABLE IF NOT EXISTS incident (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        incident_type ENUM('fire','accident','robbery') NOT NULL,
                                        description VARCHAR(600) NOT NULL,
                                        date DATE NOT NULL,
                                        time TIME NOT NULL,
                                        photo VARCHAR(255) NOT NULL,
                                        id_user INT,
                                        delete_at TIMESTAMP NULL DEFAULT NULL,
                                        creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        id_location INT UNIQUE,
                                        FOREIGN KEY (id_location) REFERENCES location(id),
                                        FOREIGN KEY (id_user) REFERENCES user(id)
);

/* TABLA INCIDENT_INFORMANT */
CREATE TABLE IF NOT EXISTS incident_informant (
                                                  id_informant INT,  -- ID del informante
                                                  id_incident INT,   -- ID del incidente
                                                  assignment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Fecha de asignación
                                                  PRIMARY KEY (id_informant, id_incident),  -- Clave primaria compuesta
                                                  FOREIGN KEY (id_informant) REFERENCES informant(id),
                                                  FOREIGN KEY (id_incident) REFERENCES incident(id)
);

/* TABLA MATERIAL */
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



