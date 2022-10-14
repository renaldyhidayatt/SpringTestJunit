package com.sanedge.junitmockitojunit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.junitmockitojunit.dto.PropertyDto;
import com.sanedge.junitmockitojunit.service.PropertyService;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyDto> saveProperty(@RequestBody PropertyDto propertyDto) {
        propertyDto = propertyService.saveProperty(propertyDto);

        ResponseEntity<PropertyDto> responseEntity = new ResponseEntity<>(propertyDto, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDto>> getAllProperties() {
        List<PropertyDto> propertyList = propertyService.getAllProperties();
        ResponseEntity<List<PropertyDto>> responseEntity = new ResponseEntity<>(propertyList, HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDto> updateProperty(@RequestBody PropertyDto propertyDTO,
            @PathVariable Long propertyId) {
        propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);
        ResponseEntity<PropertyDto> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping("/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDto> updatePropertyDescription(@RequestBody PropertyDto propertyDTO,
            @PathVariable Long propertyId) {
        propertyDTO = propertyService.updatePropetyDescription(propertyDTO, propertyId);
        ResponseEntity<PropertyDto> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping("/properties/update-price/{propertyId}")
    public ResponseEntity<PropertyDto> updatePropertyPrice(@RequestBody PropertyDto propertyDTO,
            @PathVariable Long propertyId) {
        propertyDTO = propertyService.updatePropertyPrice(propertyDTO, propertyId);
        ResponseEntity<PropertyDto> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long propertyId) {
        propertyService.deleteProperty(propertyId);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return responseEntity;
    }

}
