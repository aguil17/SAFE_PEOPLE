package com.unir.buscador.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@Builder
@Table(name = "incident")
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "incident_type")
    @NotNull(message = "El tipo incidente no puede ser nulo")
    private IncidentType incident_type;

    @Column(name = "description_incident")
    @NotNull(message = "descripción no puede ser nula")
    private String description_incident;

    @Column(name = "date")
    @NotNull(message = "fecha no puede ser nula")
    private LocalDate date;

    @Column(name = "time")
    @NotNull(message = "hora no puede ser nula")
    private LocalTime time;

    @Column(name = "photo")
    private String photo;

    @Column(name = "deleteAt")
    private Timestamp deleteAt;

    @Column(name = "creation_date")
    @NotNull(message = "fechaCreacion no puede ser nula")
    private Timestamp creation_date;

    @Column(name = "id_location")
    private int id_location;

    @Column(name = "city_name")
    @NotNull(message = "nombre de ciudad no puede ser nula")
    private String city_name;

    @Column(name = "district_name")
    @NotNull(message = "nombre de distrito no puede ser nulo")
    private  String district_name;

    @Column(name = "description_location")
    @NotNull(message = "descripcion no puede ser nulo")
    private String description_location;

    @Column(name = "reference")
    @NotNull(message = "referencia no puede ser nula")
    private String reference;


    @Column(name = "latitude",precision = 9, scale = 6)
    @NotNull(message = "latitude no puede ser nula")
    private BigDecimal latitude;

    @Column(name = "longitude",precision = 9, scale = 6)
    @NotNull(message = "longitude no puede ser nula")
    private BigDecimal longitude;

    @Column(name = "id_user")
    private int id_user;

    @Column(name = "username")
    private String username;

    @Column(name = "password_user")
    private String password_user;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleType role;

    @Column(name = "id_person")
    private int id_person;

    @Column(name = "person_name")
    private String person_name;

    @Column(name = "person_last_name")
    private String person_last_name;

    @Column(name = "dni")
    private String dni;

    @Column(name = "email")
    private String email;

    @Column(name = "cellphone")
    private String cellphone;

    @Column(name = "password_person")
    private String password_person;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderType gender;

    public Incidente() {

    }
}
