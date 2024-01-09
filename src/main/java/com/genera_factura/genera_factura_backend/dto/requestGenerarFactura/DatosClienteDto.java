package com.genera_factura.genera_factura_backend.dto.requestGenerarFactura;


import lombok.Getter;
import lombok.Setter;

public class DatosClienteDto {
    @Getter @Setter
    private String codigoCliente;
    @Getter @Setter
    private String numeroDocumento;
    @Getter @Setter
    private String razonSocial;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private Integer   codigoTipoDocumentoIdentidad;
}
