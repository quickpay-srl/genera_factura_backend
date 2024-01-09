package com.genera_factura.genera_factura_backend.controller;

import com.genera_factura.genera_factura_backend.dto.ResponseFacturacionDto;
import com.genera_factura.genera_factura_backend.dto.requestGenerarFactura.RequestGeneraFacturaDto;
import com.genera_factura.genera_factura_backend.service.FacturacionElectronicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/facturacion-electronica")
public class FacturacionElectronicaController {

    @Autowired
    private FacturacionElectronicaService facturacionElectronicaService;

    @PostMapping("/v1/crearFactura")
    public ResponseEntity<?> crearFactura(@RequestBody RequestGeneraFacturaDto requestGeneraFacturaDto) {
        Map<String, Object> response = new HashMap<>();
        ResponseFacturacionDto res =  facturacionElectronicaService.creaFactura(requestGeneraFacturaDto);
        response.put("status", res.status);
        response.put("message", res.message);
        response.put("result", res.result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/v1/anularFactura/{pEntidadId}/{pCuf}/{pCodigoMotivo}")
    public ResponseEntity<?> anularFactura(@PathVariable Long pEntidadId, @PathVariable String pCuf, @PathVariable Integer pCodigoMotivo ){

        Map<String,Object> response = new HashMap<>();
        ResponseFacturacionDto res =  facturacionElectronicaService.anularFactura(pEntidadId,pCuf,pCodigoMotivo);
        response.put("status",res.status);
        response.put("message",res.message);
        response.put("result",res.result);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
