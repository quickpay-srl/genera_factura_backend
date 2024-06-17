package com.genera_factura.genera_factura_backend.service;


import com.genera_factura.genera_factura_backend.dto.ResponseFacturacionDto;
import com.genera_factura.genera_factura_backend.dto.requestGenerarFactura.RequestGeneraFacturaDto;
import com.genera_factura.genera_factura_backend.entity.CredencialesFacEntity;
import com.genera_factura.genera_factura_backend.repository.ICredencialFacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.text.html.parser.Entity;
import java.util.*;

@Service
public class FacturacionElectronicaService {

    @Autowired
    ICredencialFacRepository iCredencialFacRepository;

    public ResponseFacturacionDto creaFactura(RequestGeneraFacturaDto requestGeneraFacturaDto)
    {

        ResponseFacturacionDto response = new ResponseFacturacionDto();
        try{

            // obtener token
            Optional<CredencialesFacEntity> cred = iCredencialFacRepository.findByEntidadId(requestGeneraFacturaDto.getEntidad().getEntidadId());
            if(!cred.isPresent())
            {
                response.status = false;
                response.message = "Entidad no encontrada";
                return response;
            }

            String query = """
                            mutation FCV_REGISTRO_ONLINE ($entidad:EntidadParamsInput,$input:FacturaCompraVentaInput!) {
                            	facturaCompraVentaCreate(
                            		notificacion: true,
                            		entidad: $entidad,
                            		input: $input
                            	) {
                            		cuf
                            		state
                            		numeroFactura
                            		cliente {
                            			codigoCliente
                            			numeroDocumento
                            			razonSocial
                            			complemento
                            			email
                            		}
                            		representacionGrafica {
                            			pdf
                            			xml
                            			sin
                            			rollo
                            		}
                            		sucursal {
                            			codigo
                            		}
                            		puntoVenta {
                            			codigo
                            		}
                            		eventoSignificativo
                            	}
                            }
                    """;
            //String URL = "https://api.isipass.com.bo/api";
            String URL = "https://api.quickpay.com.bo/api";
            HttpGraphQlClient graphQlClient;
            WebClient client = WebClient.builder()
                    .baseUrl(URL).defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer "+cred.get().getTokenDelegado())
                    .build();
            graphQlClient = HttpGraphQlClient.builder(client).build();


            //+cred.get().getTokenDelegado()

            Map<String, Object> entidadMap = new HashMap<>();
            entidadMap.put("codigoSucursal", requestGeneraFacturaDto.getEntidad().getCodigoSucursal());
            entidadMap.put("codigoPuntoVenta",  requestGeneraFacturaDto.getEntidad().getCodigoPuntoVenta());


            Map facturaCompraVentaCreate = graphQlClient.document(query).
                    variable("entidad",entidadMap).
                    variable("input",requestGeneraFacturaDto.getInput()).
                    retrieve("facturaCompraVentaCreate").toEntity(Map.class).block();


            response.status = true;
            response.message = "creado correctamente";
            response.result = facturaCompraVentaCreate;

            return response;

        }catch (Exception ex){
            System.out.println(ex.toString());
            response.status = false;
            response.message = "error: "+ex.toString();
            return response;
        }
    }
    public ResponseFacturacionDto anularFactura(Long pEntidadId,String pCuf, Integer pCodigoMotivo){
        ResponseFacturacionDto response = new ResponseFacturacionDto();

        try{
            // obtener token
            Optional<CredencialesFacEntity> cred = iCredencialFacRepository.findByEntidadId(pEntidadId);
            if(!cred.isPresent())
            {
                response.status = false;
                response.message = "Entidad no encontrada";
                return response;
            }

            String query = """
                                mutation FCV_ANULAR_CUF ($cuf:String!,$codMotivo:Int!)  {
                                	facturaCompraVentaAnulacionCuf(
                                		cuf: $cuf
                                		codigoMotivo: $codMotivo
                                	) {
                                		state
                                		numeroFactura
                                		representacionGrafica {
                                			pdf
                                			xml
                                			rollo
                                			sin
                                		}
                                	}
                                }
                    """;

            String URL = "https://api.quickpay.com.bo/api";
            HttpGraphQlClient graphQlClient;
            WebClient client = WebClient.builder()
                    .baseUrl(URL).defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJVMkZzZEdWa1gxOW9ZaDNCbEU3a1lRRVJLaHo0OWxHckN4MFVmdmhVaDlVJTNEIiwiYWNjZXNzVHlwZSI6IlUyRnNkR1ZrWDElMkZITGs2RFBiaWtHVVJnaVVtM0h5bFlHa25obXBhUlFib3ZLTzljTGVoalRoTDhjNU1EbTI1NXBFMm8yQ2k0ODRwMVU5MEhnNiUyQjJlUSUzRCUzRCIsInVzZXJJZCI6IjY2NTRmYzBiNzM3NzRmMmM3ODU4NTM0ZSIsInJvbElkIjoiNjY1NGZjMGI3Mzc3NGYyYzc4NTg1MzQ5IiwiaWF0IjoxNzE2ODQ1NjAzLCJleHAiOjE3NjcyMTY4MDN9.3l3frtGPNAcB0sYSYxnXQyUuSrMU4yFibqTMihDRTn4")
                    .build();
            graphQlClient = HttpGraphQlClient.builder(client).build();
            Map resul = new HashMap();
            String mensaje=null;
            try{
                resul = graphQlClient.document(query).
                        variable("cuf",pCuf).
                        variable("codMotivo",pCodigoMotivo).
                        retrieve("facturaCompraVentaAnulacionCuf").toEntity(Map.class).block();
            }catch (Exception ex){
                mensaje  = graphQlClient.document(query).
                        variable("cuf",pCuf).
                        variable("codMotivo",pCodigoMotivo).
                        retrieve("message").toEntity(String.class).block();

            }


            response.status = true;
            response.message = (mensaje!=null)? mensaje:  "anulado correctamente";
            response.result = resul;

            return response;


        }catch (Exception ex){
            response.status = false;
            response.message = "error: "+ex.toString();
            return response;
        }
    }
}

