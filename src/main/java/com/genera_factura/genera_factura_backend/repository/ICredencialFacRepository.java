package com.genera_factura.genera_factura_backend.repository;


import com.genera_factura.genera_factura_backend.entity.CredencialesFacEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICredencialFacRepository extends JpaRepository<CredencialesFacEntity,Long> {
    @Query("SELECT c " +
            "FROM CredencialesFacEntity c  " +
            "WHERE c.entidadId = :entidadId and c.estado = 'ACTIVO'")
    Optional<CredencialesFacEntity> findByEntidadId(@Param("entidadId") Long entidadId);
}
