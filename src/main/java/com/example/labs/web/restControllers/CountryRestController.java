package com.example.labs.web.restControllers;

import com.example.labs.model.Country;
import com.example.labs.model.dtos.CountryDto;
import com.example.labs.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> listCountries(){
        return countryService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable  Long id){
        return countryService.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Country> save(CountryDto countryDto){
        return countryService.save(countryDto).map(country -> ResponseEntity.ok().body(country))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        if(countryService.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        countryService.deleteById(id);
            if(countryService.findById(id).isPresent())
                return ResponseEntity.badRequest().build();
            return ResponseEntity.ok().build();
    }
}
