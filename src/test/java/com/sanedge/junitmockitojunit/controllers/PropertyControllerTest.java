package com.sanedge.junitmockitojunit.controllers;

import com.sanedge.junitmockitojunit.controller.PropertyController;
import com.sanedge.junitmockitojunit.dto.PropertyDto;
import com.sanedge.junitmockitojunit.service.PropertyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {

    @InjectMocks
    private PropertyController propertyController;

    @Mock
    private PropertyService propertyService;

    @Test
    @DisplayName("Test Success Scenario for Saving Property")
    void testSaveProperty(){
        PropertyDto propertyDto = new PropertyDto();

        propertyDto.setTitle("Dummy Property");

        PropertyDto savedProperty = new PropertyDto();
        savedProperty.setId(1L);
        savedProperty.setTitle(propertyDto.getTitle());

        Mockito.when(propertyService.saveProperty(propertyDto)).thenReturn(savedProperty);

        ResponseEntity<PropertyDto> responseEntity = propertyController.saveProperty(propertyDto);

        Assertions.assertNotNull(responseEntity.getBody().getId());

        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Success Scenario for fetching all properties")
    void testGetProperties(){
        List<PropertyDto> propertyDtoList = new ArrayList<>();
        PropertyDto dto = new PropertyDto();

        dto.setId(1L);
        dto.setTitle("Dummy Property");
        propertyDtoList.add(dto);

        Mockito.when(propertyService.getAllProperties()).thenReturn(propertyDtoList);
        ResponseEntity<List<PropertyDto>> responseEntity = propertyController.getAllProperties();
        Assertions.assertEquals(1, responseEntity.getBody().size());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Success Scenario for updating only price of the property")
    void testUpdatePropertyPrice(){

        PropertyDto dto = new PropertyDto();
        dto.setPrice(67676.78);

        Mockito.when(propertyService.updatePropertyPrice(Mockito.any(), Mockito.anyLong())).thenReturn(dto);
        ResponseEntity<PropertyDto> responseEntity = propertyController.updatePropertyPrice(dto, 1L);

        Assertions.assertEquals(67676.78, responseEntity.getBody().getPrice());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }
}
