package com.genera_factura.genera_factura_backend.service;

import com.genera_factura.genera_factura_backend.entity.LogSistemaEntity;
import org.springframework.stereotype.Service;

@Service
public interface ILogSistemaService {
    public LogSistemaEntity save(LogSistemaEntity entity);
}

