package com.lite.Factura.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lite.Factura.domain.dto.model.Factura;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacturaService implements FacturaUseCase {

    private final FacturaRepository facturaRepository;

    @Override
    public Factura createFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Optional<Factura> getFacturaById(Long id) {
        return facturaRepository.findById(id);
    }

    @Override
    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura updateFactura(Long id, Factura facturaDetails) {
        Optional<Factura> optionalFactura = facturaRepository.findById(id);
        if (optionalFactura.isPresent()) {
            Factura existingFactura = optionalFactura.get();
            existingFactura.setNumero(facturaDetails.getNumero());
            existingFactura.setFechaEmision(facturaDetails.getFechaEmision());
            existingFactura.setTotal(facturaDetails.getTotal());
            existingFactura.setClienteId(facturaDetails.getClienteId());
            existingFactura.setDescripcion(facturaDetails.getDescripcion());
            return facturaRepository.save(existingFactura);
        }
        return null;
    }

    @Override
    public void deleteFactura(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    public List<Factura> getFacturasByClienteId(Long clienteId) {
        return facturaRepository.findByClienteId(clienteId);
    }
}
