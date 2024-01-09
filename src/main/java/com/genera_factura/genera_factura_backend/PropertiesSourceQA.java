package com.genera_factura.genera_factura_backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:aplication-qa.properties")
@Profile("qa")
public class PropertiesSourceQA {

}
