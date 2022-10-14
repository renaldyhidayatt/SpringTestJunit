package com.sanedge.junitmockitojunit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.junitmockitojunit.converter.PropertyConvert;
import com.sanedge.junitmockitojunit.dto.PropertyDto;
import com.sanedge.junitmockitojunit.entity.PropertyEntity;
import com.sanedge.junitmockitojunit.exception.BusinessException;
import com.sanedge.junitmockitojunit.repository.PropertyRepository;
import com.sanedge.junitmockitojunit.service.PropertyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConvert propertyConvert;

    @Override
    public PropertyDto saveProperty(PropertyDto propertyDto) {
        PropertyEntity pe = propertyConvert.convertDtoToEntity(propertyDto);
        pe = propertyRepository.save(pe);

        propertyDto = propertyConvert.convertEntityToDto(pe);

        return propertyDto;
    }

    @Override
    public List<PropertyDto> getAllProperties() {
        List<PropertyEntity> listOfProps = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDto> propList = new ArrayList<>();

        for (PropertyEntity pe : listOfProps) {
            PropertyDto dto = propertyConvert.convertEntityToDto(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public PropertyDto updateProperty(PropertyDto propertyDto, Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
        PropertyDto dto = null;

        if (optEn.isPresent()) {
            PropertyEntity pe = optEn.get();
            pe.setTitle(propertyDto.getTitle());
            pe.setAddress(propertyDto.getAddress());
            pe.setOwnerEmail(propertyDto.getOwnerEmail());
            pe.setOwnerName(propertyDto.getOwnerName());
            pe.setPrice(propertyDto.getPrice());
            pe.setDescription(propertyDto.getDescription());

            dto = propertyConvert.convertEntityToDto(pe);

            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public PropertyDto updatePropetyDescription(PropertyDto propertyDto, Long propertyId) throws BusinessException {
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
        PropertyDto dto = null;

        if (optEn.isPresent()) {
            PropertyEntity pe = optEn.get();
            pe.setDescription(propertyDto.getDescription());
            dto = propertyConvert.convertEntityToDto(pe);
            propertyRepository.save(pe);
        } else {
            throw new BusinessException("No Property Found for Update");
        }

        return dto;
    }

    @Override
    public PropertyDto updatePropertyPrice(PropertyDto propertyDto, Long properyId) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(properyId);
        PropertyDto dto = null;

        if (optEn.isPresent()) {
            PropertyEntity pe = optEn.get();
            pe.setPrice(propertyDto.getPrice());
            dto = propertyConvert.convertEntityToDto(pe);
            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}
