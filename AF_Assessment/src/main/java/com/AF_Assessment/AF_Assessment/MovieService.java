package com.AF_Assessment.AF_Assessment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repository;
    public List<Movie> findAllMovies() {
        return repository.findAll();
    }
}