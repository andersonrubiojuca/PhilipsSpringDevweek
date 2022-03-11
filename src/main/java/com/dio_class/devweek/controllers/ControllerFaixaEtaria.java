package com.dio_class.devweek.controllers;

import com.dio_class.devweek.Entity.FaixaEtaria;
import com.dio_class.devweek.repo.FaixaEtariaRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ControllerFaixaEtaria {

    private final FaixaEtariaRepo frepo;

    public ControllerFaixaEtaria(FaixaEtariaRepo frepo) {
        this.frepo = frepo;
    }

    @GetMapping("/faixaetaria")
    public ResponseEntity<List<FaixaEtaria>> findAllFaixaEtaria(){
        try{
            List<FaixaEtaria> lista = frepo.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/faixaetaria/{id}")
    public ResponseEntity<?> findbyIdFaixaEtaria(@PathVariable Long id){
        Optional<FaixaEtaria> faixaEtaria = frepo.findById(id);
        if (faixaEtaria.isPresent())
            return new ResponseEntity<>(faixaEtaria, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/faixaetaria/novo")
    public ResponseEntity<?> newFaixaEtaria(@RequestBody FaixaEtaria newFaixa){
        try{
            frepo.save(newFaixa);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/faixaetaria/remover/{id}")
    public ResponseEntity<?> deleteFaixaEtaria(@PathVariable long id){
        try{
            frepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
