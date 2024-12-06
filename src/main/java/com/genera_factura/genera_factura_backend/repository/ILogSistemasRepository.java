package com.genera_factura.genera_factura_backend.repository;

import com.genera_factura.genera_factura_backend.entity.LogSistemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILogSistemasRepository extends JpaRepository<LogSistemaEntity, Long> {

}