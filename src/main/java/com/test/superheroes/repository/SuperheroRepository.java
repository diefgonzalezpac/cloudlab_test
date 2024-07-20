package com.test.superheroes.repository;

import com.test.superheroes.entity.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long> {
    List<Superhero> findByNameContainsIgnoreCase(String name);
}
