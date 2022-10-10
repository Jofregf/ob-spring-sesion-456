package com.example.obspringsesion456.controllers;

import com.example.obspringsesion456.entities.Laptop;
import com.example.obspringsesion456.repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    @ApiOperation("Buscar todas las laptops almacenadas en la base de datos")
    public List<Laptop> findAll() {
        return laptopRepository.findAll();
    }

    @PostMapping("/api/laptops")
    @ApiOperation("Almacenar una laptop con los atributos establecidos")
    public Laptop create(@RequestBody Laptop laptop){
        return laptopRepository.save(laptop);
    }

    @GetMapping("api/laptops/{id}")
    @ApiOperation("Buscar una laptop a traves de clave primaria id")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        Optional<Laptop> laptopOption = laptopRepository.findById(id);
        if(laptopOption.isPresent()){
            return ResponseEntity.ok(laptopOption.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    @PutMapping("/api/laptops")
    @ApiOperation("Modificar los atributos de una laptop almacenada a traves de su clave primaria id")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if(laptop.getId() == null){
            log.warn("Intenta actualizar una laptop no existente");
            return ResponseEntity.badRequest().build();
        }
        if (!laptopRepository.existsById(laptop.getId())){
            log.warn("Intenta actualizar una laptop no existente");
            return ResponseEntity.notFound().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);

    }

    @DeleteMapping("/api/laptops/{id}")
    @ApiOperation("Eliminar una laptop almacenada en base de datos a traves de su clave primaria id")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        if(!laptopRepository.existsById(id)){
            log.warn("Intenta borrar una laptop no existente");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/laptops")
    @ApiOperation("Eliminar todas las laptops almacenadas en base de datos")
    public ResponseEntity<Laptop> deleteAll(){
        if(laptopRepository.count() == 0){
            log.warn("Intenta borrar una base de datos vacia");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
