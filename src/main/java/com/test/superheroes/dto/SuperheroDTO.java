package com.test.superheroes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperheroDTO {
    private Long id;
    private String name;
    private String civilName;
    private String comic;
    private List<String> powers;
    private String origin;
    private String description;
}
