package com.lite.Factura.application.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.lite.Factura.domain.dto.FacturaDTO;
import com.lite.Factura.domain.dto.model.Factura;
import com.lite.Factura.infraestructure.entity.FacturaEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacturaMapper {
    
    FacturaMapper INSTANCE = Mappers.getMapper(FacturaMapper.class);
    
    Factura toModel(FacturaDTO dto);
    
    FacturaDTO toDto(Factura model);
    
    List<FacturaDTO> toDtoList(List<Factura> modelList);
    
    Factura  toModel(FacturaEntity entity);
    
    FacturaEntity toEntity(Factura model);
    
    List<Factura> toModelList(List<FacturaEntity> entityList);
}

