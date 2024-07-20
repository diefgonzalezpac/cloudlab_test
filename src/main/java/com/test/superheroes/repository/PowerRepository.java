package com.test.superheroes.repository;

import com.test.superheroes.entity.Power;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PowerRepository extends JpaRepository<Power, Long> {
    Optional<Power> findByName(String name);
}
