package com.genera_factura.genera_factura_backend.dto.requestGenerarFactura;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class InputDto {
    @Getter @Setter
    private DatosClienteDto cliente;
    @Getter @Setter
    private String actividadEconomica;
    @Getter @Setter
    private Integer codigoMetodoPago;
    @Getter @Setter
    private Float descuentoAdicional;
    @Getter @Setter
    private Integer codigoMoneda;
    @Getter @Setter
    private Float tipoCambio;
    @Getter @Setter
    private Float montoGiftCard;
    @Getter @Setter
    private String numeroTarjeta;
    @Getter @Setter
    private List<DetalleFacturaDto> detalle;
}
