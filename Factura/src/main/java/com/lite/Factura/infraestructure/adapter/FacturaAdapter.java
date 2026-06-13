package com.lite.Factura.infraestructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.lite.Factura.application.mapper.FacturaMapper;
import com.lite.Factura.application.service.FacturaRepository;
import com.lite.Factura.domain.dto.model.Factura;
import com.lite.Factura.infraestructure.entity.FacturaEntity;
import com.lite.Factura.infraestructure.entity.repository.FacturaJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FacturaAdapter implements FacturaRepository {
    private final FacturaJpaRepository facturaJpaRepository;
    private final FacturaMapper facturaMapper;
    @Override
    public Factura save(Factura factura) {
        // TODO Auto-generated method stub
        FacturaEntity facturaEntity = facturaMapper.toEntity(factura);
        FacturaEntity savedEntity = facturaJpaRepository.save(facturaEntity);   
        return facturaMapper.toModel(savedEntity);
    }
    @Override
    public Optional<Factura> findById(Long id) {
        return facturaJpaRepository.findById(id)
                .map(facturaMapper::toModel);
    }
    
    @Override
    public List<Factura> findAll() {
        return facturaJpaRepository.findAll().stream()
                .map(facturaMapper::toModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        facturaJpaRepository.deleteById(id);
    }
    
    @Override
    public List<Factura> findByClienteId(Long clienteId) {
        return facturaJpaRepository.findByClienteId(clienteId).stream()
                .map(facturaMapper::toModel)
                .collect(Collectors.toList());
    }

    
   
}