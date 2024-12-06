package com.genera_factura.genera_factura_backend.service;

import com.genera_factura.genera_factura_backend.entity.LogSistemaEntity;
import com.genera_factura.genera_factura_backend.repository.ILogSistemasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogSistemaService implements ILogSistemaService {

    @Autowired
    private ILogSistemasRepository logSistemasDao;

    @Override
    public LogSistemaEntity save(LogSistemaEntity entity) {
        return logSistemasDao.save(entity);
    }

}

