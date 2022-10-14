package com.sanedge.junitmockitojunit.services;


import com.sanedge.junitmockitojunit.converter.PropertyConvert;
import com.sanedge.junitmockitojunit.dto.PropertyDto;
import com.sanedge.junitmockitojunit.entity.PropertyEntity;
import com.sanedge.junitmockitojunit.exception.BusinessException;
import com.sanedge.junitmockitojunit.repository.PropertyRepository;
import com.sanedge.junitmockitojunit.service.impl.PropertyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceImplTest {
    @InjectMocks
    private PropertyServiceImpl propertyServiceImpl;

    @Mock
    private PropertyConvert propertyConvert;

    @Mock
    PropertyRepository propertyRepository;

    @Test
    void testSaveProperty_Success(){
        PropertyDto dto = new PropertyDto();
        dto.setTitle("Dummy");

        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setTitle("Dummy");

        PropertyEntity savedEntity = new PropertyEntity();
        savedEntity.setTitle("Dummy");
        savedEntity.setId(1L);

        PropertyDto savedDTO = new PropertyDto();
        savedDTO.setTitle("Dummy");
        savedDTO.setId(1L);


        when(propertyConvert.convertDtoToEntity(Mockito.any())).thenReturn(propertyEntity);
        when(propertyRepository.save(Mockito.any())).thenReturn(savedEntity);
        when(propertyConvert.convertEntityToDto(Mockito.any())).thenReturn(savedDTO);

        PropertyDto result = propertyServiceImpl.saveProperty(dto);
        Assertions.assertEquals(savedDTO.getId(), result.getId());
    }

    @Test
    void testGetAllProperties_Success() {
        List<PropertyEntity> propertyEntities = new ArrayList<>();
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setId(1L);
        propertyEntity.setTitle("Dummy");
        propertyEntities.add(propertyEntity);

        PropertyDto savedDto = new PropertyDto();
        savedDto.setTitle("Dummy");
        savedDto.setId(1L);

        when(propertyRepository.findAll()).thenReturn(propertyEntities);
        when(propertyConvert.convertEntityToDto(Mockito.any())).thenReturn(savedDto);

        List<PropertyDto> listPropDto = propertyServiceImpl.getAllProperties();

        Assertions.assertEquals(1, listPropDto.size());
    }

    @Test
    void testUpdateProperty_Success(){
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setTitle("Dummy");
        propertyDto.setId(1L);
        propertyDto.setPrice(233.2);
        propertyDto.setAddress("xyz");
        propertyDto.setDescription("Absc");
        propertyDto.setOwnerEmail("d@gmail.com");
        propertyDto.setOwnerName("AD");

        PropertyEntity pe = new PropertyEntity();
        pe.setId(1L);
        pe.setTitle("Dummy");

        propertyDto.setId(1L);

        pe.setPrice(22.3);
        pe.setAddress("xyx");
        pe.setDescription("Abc");
        pe.setOwnerEmail("Adb@gmail.com");
        pe.setOwnerName("AD Xy");

        when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(pe));
        when(propertyConvert.convertEntityToDto(Mockito.any())).thenReturn(propertyDto);

        PropertyDto updatedProperty = propertyServiceImpl.updateProperty(propertyDto, 1L);
        Assertions.assertEquals(propertyDto.getTitle(), updatedProperty.getTitle());
        Assertions.assertEquals(propertyDto.getPrice(), updatedProperty.getPrice());
    }

    @Test
    void testUpdateProperyDescription_Success(){
        PropertyDto savedDto = new PropertyDto();
        savedDto.setTitle("Dummy");
        savedDto.setId(1L);
        savedDto.setPrice(23.3);
        savedDto.setOwnerEmail("ad@gmail.com");
        savedDto.setOwnerName("Ad Xy");

        savedDto.setDescription("Updated Abc");

        PropertyEntity pe = new PropertyEntity();
        pe.setId(1L);
        pe.setTitle("Dummy");

        savedDto.setId(1L);
        pe.setTitle("Dummy");
        pe.setId(1L);
        pe.setPrice(23.3);
        pe.setOwnerEmail("ad@gmail.com");
        pe.setOwnerName("Ad Xy");

        pe.setDescription("Updated Abc");

        when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(pe));
        when(propertyConvert.convertEntityToDto(Mockito.any())).thenReturn(savedDto);

        PropertyDto updatedProperty = propertyServiceImpl.updatePropetyDescription(savedDto, 1L);
        Assertions.assertEquals(savedDto.getDescription(), updatedProperty.getDescription());

    }

    @Test
    void testUpdatePropertyDescription_Failure(){

        PropertyDto savedDTO = new PropertyDto();

        when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            PropertyDto updatedProperty = propertyServiceImpl.updatePropetyDescription(savedDTO, 1L);
        });

        Assertions.assertEquals("No Property Found for Update", exception.getMessage());
    }
}
