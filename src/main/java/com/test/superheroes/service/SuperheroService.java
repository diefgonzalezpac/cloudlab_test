package com.test.superheroes.service;

import com.test.superheroes.dto.SuperheroDTO;
import com.test.superheroes.entity.Comic;
import com.test.superheroes.entity.Power;
import com.test.superheroes.entity.Superhero;
import com.test.superheroes.exception.ApiException;
import com.test.superheroes.exception.model.ExceptionModels;
import com.test.superheroes.repository.ComicRepository;
import com.test.superheroes.repository.PowerRepository;
import com.test.superheroes.repository.SuperheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperheroService {

    @Autowired
    SuperheroRepository superheroRepository;
    @Autowired
    PowerRepository powerRepository;
    @Autowired
    ComicRepository comicRepository;

    @Cacheable(value = "superheroes")
    public List<SuperheroDTO> getSuperheroes() {
        return superheroRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    @CacheEvict(value = "superheroes", allEntries = true)
    public SuperheroDTO createSuperhero(SuperheroDTO superheroDTO) {
        return entityToDto(superheroRepository.save(dtoToEntity(superheroDTO)));
    }

    @Cacheable(value = "superheroes")
    public SuperheroDTO getSuperheroById(Long id) {
        return entityToDto(superheroRepository.findById(id)
                .orElseThrow(() -> new ApiException(ExceptionModels.SUPERHERO_NOT_FOUND, 404)));
    }

    @Cacheable(value = "superheroes")
    public List<SuperheroDTO> getSuperheroByName(String name) {
        return superheroRepository.findByNameContainsIgnoreCase(name)
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    @CacheEvict(value = "superheroes", allEntries = true)
    public SuperheroDTO updateSuperhero(Long id, SuperheroDTO updateSuperheroDTO) {
        return entityToDto(superheroRepository.save(superheroRepository.findById(id)
                .map(s -> {
                    s.setName(updateSuperheroDTO.getName());
                    s.setCivilName(updateSuperheroDTO.getCivilName());
                    s.getPowers().clear();
                    s.getPowers().addAll(verifySetPowers(updateSuperheroDTO.getPowers()));
                    return s;
                })
                .orElseThrow(() -> new ApiException(ExceptionModels.SUPERHERO_NOT_FOUND, 404))));
    }

    @CacheEvict(value = "superheroes", allEntries = true)
    public boolean deleteSuperhero(Long id) {
        superheroRepository.delete(
                superheroRepository.findById(id)
                .orElseThrow(() -> new ApiException(ExceptionModels.SUPERHERO_NOT_FOUND, 404)));
        return !superheroRepository.existsById(id);
    }

    // Mappers
    public SuperheroDTO entityToDto(Superhero superhero) {
        return new SuperheroDTO(
                superhero.getId(),
                superhero.getName(),
                superhero.getCivilName(),
                superhero.getComic().getName(),
                superhero.getPowers().stream().map(Power::getName).toList(),
                superhero.getOrigin(),
                superhero.getDescription()
        );
    }

    public Superhero dtoToEntity(SuperheroDTO superheroDTO) {
        return new Superhero(
                superheroDTO.getId(),
                superheroDTO.getName(),
                superheroDTO.getCivilName(),
                verifySetComic(superheroDTO.getComic()),
                verifySetPowers(superheroDTO.getPowers()),
                superheroDTO.getOrigin(),
                superheroDTO.getDescription()
        );
    }

    public List<Power> verifySetPowers(List<String> powers) {
        return powers
                .stream()
                .map(p -> powerRepository.findByName(p).orElse(new Power(p)))
                .toList();
    }

    public Comic verifySetComic(String comic) {
        return comicRepository.findByNameContainsIgnoreCase(comic)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ApiException(ExceptionModels.COMIC_NOT_FOUND, 404));
    }
}
