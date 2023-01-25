package org.example.controller;

import org.example.model.Cat;
import org.example.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatController {

    @Autowired
    private CatService catService;
    @GetMapping("/cats")
    public List<Cat> getAll(){
        return catService.getCats();
    }
    @GetMapping("/cats/{id}")
    public ResponseEntity<Cat> getOne(@PathVariable Long id){
        return ResponseEntity.ok(catService.readCat(id));
    }

    @PostMapping("/cat")
    public ResponseEntity<Cat> add(@RequestBody Cat cat){
        catService.saveCat(cat);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/cats/{id}")
    public void delete(@PathVariable Long id){
        catService.deleteCat(id);
    }
    @PutMapping("/cat/{id}")
    public ResponseEntity<Cat> update(@PathVariable Long id, @RequestBody Cat cat){
        boolean updated = catService.update(id,cat);
        return updated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);


    }
}
