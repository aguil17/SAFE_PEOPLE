package com.unir.operador.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Data
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@Builder
@Table(name = "user")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    @Column(name = "username")
    @NotNull(message = "username no puede ser nulo")
    private String username;

    @Column(name = "password")
    @NotNull(message = "password no puede ser nulo")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NotNull(message = "role no puede ser nulo")
    private RoleType role;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person", referencedColumnName = "id", unique = true)
    @JsonIgnoreProperties("usuario")
    private Persona persona;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "update_date")
    private Timestamp updateDate;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Incidente> incidentes;

    @Override
    public int hashCode() {
        return Objects.hash(id);  // Solo utilizar el campo único
    }


    public Usuario() {

    }
}
