package com.dio_class.devweek.controllers;

import com.dio_class.devweek.Entity.Regiao;
import com.dio_class.devweek.repo.RegiaoRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ControllerRegiao {
    private final RegiaoRepo repository;

    public ControllerRegiao(RegiaoRepo repository) {
        this.repository = repository;
    }

    @GetMapping("/regiao")
    public List<Regiao> getRegiao(){
        return repository.findAll();
    }

    @GetMapping("/regiao/{id}")
    public ResponseEntity<Regiao> getRegiaobyId(@PathVariable Long id){
        Optional<Regiao> regiaoEscolhida = repository.findById(id);
        return regiaoEscolhida.map(regiao -> new ResponseEntity<>(regiao, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>((Regiao) null, HttpStatus.NOT_FOUND));

    }

    @PostMapping("/regiao/novo")
    public ResponseEntity<?> putRegiao(@RequestBody Regiao regiao){
        try{
            repository.save(regiao);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/regiao/delete/{id}")
    public ResponseEntity<Regiao> deleteRegiao(@PathVariable Long id){
        try{
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
