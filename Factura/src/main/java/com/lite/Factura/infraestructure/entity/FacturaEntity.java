package com.lite.Factura.infraestructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "facturas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacturaEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String numero;
    
    @Column(name="fecha_emision", nullable = false)
    private String fechaEmision;
    
    @Column(nullable = false)
    private String total;
    
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
    private String descripcion;

}
