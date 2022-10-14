package com.sanedge.junitmockitojunit.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;

import com.sanedge.junitmockitojunit.dto.PropertyDto;
import com.sanedge.junitmockitojunit.entity.PropertyEntity;

@ExtendWith(MockitoExtension.class)
public class PropertyConverterTest {

    @InjectMocks
    private PropertyConvert propertyConverter;

    @Test
    void testConvertDTOtoEntity_Success() {

        PropertyDto dto = new PropertyDto();
        dto.setTitle("Dummy");
        dto.setPrice(12345.55);

        PropertyEntity propertyEntity = propertyConverter.convertDtoToEntity(dto);

        Assertions.assertEquals(dto.getPrice(), propertyEntity.getPrice());
        Assertions.assertEquals(dto.getTitle(), propertyEntity.getTitle());

    }

    @Test
    void testConvertEntityToDTO_Success() {

        PropertyEntity entity = new PropertyEntity();
        entity.setTitle("Dummy");
        entity.setPrice(12345.55);

        PropertyDto dto = propertyConverter.convertEntityToDto(entity);

        Assertions.assertEquals(entity.getPrice(), dto.getPrice());
        Assertions.assertEquals(entity.getTitle(), dto.getTitle());

    }
}