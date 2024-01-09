package com.genera_factura.genera_factura_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credenciales_fac", schema = "tesla")
public class CredencialesFacEntity {
    @Id
    @Column(name = "credencial_id")
    private Long credencialId;

    @Column(name = "entidad_id")
    private Long entidadId;

    @Column(name = "urlcomercio")
    private String urlcomercio;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "password")
    private String password;

    @Column(name = "token_delegado")
    private String tokenDelegado;

    @Column(name = "estado")
    private String estado;


}



