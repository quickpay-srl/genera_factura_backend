/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genera_factura.genera_factura_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aCallejas
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "log_sistemas",  schema = "tesla")

public class LogSistemaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "log_sistema_id", nullable = false)
    private Long logSistemaId;
    @Column(length = 100)
    private String modulo;
    @Column(length = 500)
    private String controller;
    @Column(length = 2147483647)
    private String mensaje;
    @Column(length = 2147483647)
    private String causa;
    @Column(name = "usuario_creacion")
    private Long usuarioCreacion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;


    
}
