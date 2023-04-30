package com.example.labs.service;

import com.example.labs.model.Country;
import com.example.labs.model.dtos.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> listAll();

    Optional<Country> findById(Long id);

    Optional<Country> save(CountryDto countryDto);

    void deleteById(Long id);
}
