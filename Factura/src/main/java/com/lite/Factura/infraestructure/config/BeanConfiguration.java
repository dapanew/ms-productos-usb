package com.lite.Factura.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lite.Factura.application.service.FacturaRepository;
import com.lite.Factura.application.service.FacturaService;
import com.lite.Factura.application.service.FacturaUseCase;

@Configuration
public class BeanConfiguration {

    @Bean
    public FacturaUseCase facturaUseCase(FacturaRepository facturaRepository) {
        return new FacturaService(facturaRepository);
    }
}
