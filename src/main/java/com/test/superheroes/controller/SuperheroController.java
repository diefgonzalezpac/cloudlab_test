package com.test.superheroes.controller;

import com.test.superheroes.config.LogExecutionTime;
import com.test.superheroes.dto.SuperheroDTO;
import com.test.superheroes.service.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/superheroes")
public class SuperheroController {

    @Autowired
    SuperheroService superheroService;

    @LogExecutionTime
    @GetMapping
    public ResponseEntity<List<SuperheroDTO>> getSuperheroes(@RequestParam(required = false) String name) {
        if (Objects.nonNull(name)) return ResponseEntity.ok(superheroService.getSuperheroByName(name));
        return ResponseEntity.ok(superheroService.getSuperheroes());
    }

    @LogExecutionTime
    @GetMapping("/{id}")
    public ResponseEntity<SuperheroDTO> getSuperhero(@PathVariable Long id) {
        return ResponseEntity.ok(superheroService.getSuperheroById(id));
    }

    @LogExecutionTime
    @PostMapping
    public ResponseEntity<SuperheroDTO> createSuperhero(@RequestBody SuperheroDTO superheroDTO) {
        return ResponseEntity.ok(superheroService.createSuperhero(superheroDTO));
    }

    @LogExecutionTime
    @PutMapping("/{id}")
    public ResponseEntity<SuperheroDTO> updateSuperhero(@PathVariable Long id,
                                                        @RequestBody SuperheroDTO superheroDTO) {
        return ResponseEntity.ok(superheroService.updateSuperhero(id, superheroDTO));
    }

    @LogExecutionTime
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSuperhero(@PathVariable Long id) {
        return ResponseEntity.ok(superheroService.deleteSuperhero(id));
    }
}
