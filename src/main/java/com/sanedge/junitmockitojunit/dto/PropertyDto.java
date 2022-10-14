package com.sanedge.junitmockitojunit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {
    private Long id;
    private String title;
    private String description;
    private String ownerName;
    private String ownerEmail;
    private Double price;
    private String address;
}
