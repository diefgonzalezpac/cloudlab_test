package com.test.superheroes.exception.model;

public class ExceptionModels {
    public static final Problem SUPERHERO_NOT_FOUND =
            new Problem("SUPERHERO_NOT_FOUND", "Superhero could not be found.", "The id does not correspond to any superhero.");
    public static final Problem COMIC_NOT_FOUND =
            new Problem("COMIC_NOT_FOUND", "Comic could not be found.", "The comic does not exist.");
}
