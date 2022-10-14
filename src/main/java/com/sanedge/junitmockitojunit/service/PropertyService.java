package com.sanedge.junitmockitojunit.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.sanedge.junitmockitojunit.dto.PropertyDto;
import com.sanedge.junitmockitojunit.exception.BusinessException;

public interface PropertyService {
    PropertyDto saveProperty(PropertyDto propertyDto);

    List<PropertyDto> getAllProperties();

    PropertyDto updateProperty(PropertyDto propertyDto, Long propertyId);

    PropertyDto updatePropetyDescription(@RequestBody PropertyDto propertyDto, Long propertyId)
            throws BusinessException;

    PropertyDto updatePropertyPrice(@RequestBody PropertyDto propertyDto, Long propertyId);

    void deleteProperty(Long propertyId);

}
