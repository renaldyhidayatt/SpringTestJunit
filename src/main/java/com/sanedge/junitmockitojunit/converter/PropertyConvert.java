package com.sanedge.junitmockitojunit.converter;

import org.springframework.stereotype.Component;

import com.sanedge.junitmockitojunit.dto.PropertyDto;
import com.sanedge.junitmockitojunit.entity.PropertyEntity;

@Component
public class PropertyConvert {
    public PropertyEntity convertDtoToEntity(PropertyDto propertyDto) {
        PropertyEntity pe = new PropertyEntity();
        pe.setTitle((propertyDto.getTitle()));
        pe.setAddress(propertyDto.getAddress());
        pe.setOwnerEmail(propertyDto.getOwnerEmail());
        pe.setOwnerName(propertyDto.getOwnerName());
        pe.setPrice(propertyDto.getPrice());
        pe.setDescription(propertyDto.getDescription());
        return pe;
    }

    public PropertyDto convertEntityToDto(PropertyEntity propertyEntity) {
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setId(propertyEntity.getId());
        propertyDto.setTitle(propertyEntity.getTitle());
        propertyDto.setAddress(propertyEntity.getAddress());
        propertyDto.setOwnerEmail(propertyEntity.getOwnerEmail());
        propertyDto.setOwnerName(propertyEntity.getOwnerName());
        propertyDto.setPrice(propertyEntity.getPrice());
        propertyDto.setDescription(propertyEntity.getDescription());

        return propertyDto;

    }
}
