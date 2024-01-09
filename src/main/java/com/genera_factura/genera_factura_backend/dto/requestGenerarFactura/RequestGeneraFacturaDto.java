package com.genera_factura.genera_factura_backend.dto.requestGenerarFactura;

import lombok.Getter;
import lombok.Setter;

public class RequestGeneraFacturaDto {
    @Getter @Setter
    private DatosEntidadDto entidad;
    @Getter @Setter
    private InputDto input;
}
