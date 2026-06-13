package com.lite.Factura.infraestructure.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lite.Factura.infraestructure.entity.FacturaEntity;
@Repository
public interface FacturaJpaRepository extends JpaRepository<FacturaEntity, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar facturas por número o cliente
    List<FacturaEntity> findByClienteId(Long clienteId);
}
