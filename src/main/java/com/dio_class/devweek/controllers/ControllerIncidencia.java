package com.dio_class.devweek.controllers;

import com.dio_class.devweek.Entity.Incidencia;
import com.dio_class.devweek.repo.IncidendiaRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ControllerIncidencia {

    private final IncidendiaRepo irepo;

    public ControllerIncidencia(IncidendiaRepo irepo) {
        this.irepo = irepo;
    }

    @GetMapping("/incidencia")
    public ResponseEntity<?> findIncidencia(){
        try{
            List<Incidencia> lista = irepo.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/incidencia/{id}")
    public ResponseEntity<Incidencia> findbyIdIncidencia(@PathVariable Long id){
        Optional<Incidencia> inci = irepo.findById(id);
        if(inci.isPresent())
            return new ResponseEntity<>(inci.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
