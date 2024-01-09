package com.genera_factura.genera_factura_backend.dto.requestGenerarFactura;


import lombok.Getter;
import lombok.Setter;

public class DetalleFacturaDto {
    @Getter @Setter
    private String codigoActividad;
    @Getter @Setter
    private String codigoProductoSin;
    @Getter @Setter
    private String codigoProducto;
    @Getter @Setter
    private String descripcion;
    @Getter @Setter
    private Integer cantidad;
    @Getter @Setter
    private Integer unidadMedida;
    @Getter @Setter
    private Float  precioUnitario;
    @Getter @Setter
    private Float  montoDescuento;
}
