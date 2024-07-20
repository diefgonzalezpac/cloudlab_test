package com.test.superheroes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "superheroes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Superhero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String civilName;

    @ManyToOne
    @JoinColumn(name="comic_id")
    private Comic comic;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "superheroes_powers",
            joinColumns = @JoinColumn(name = "superhero_id"),
            inverseJoinColumns = @JoinColumn(name = "power_id"))
    private List<Power> powers = new ArrayList<>();

    private String origin;
    @Size(max = 1000)
    private String description;
}
