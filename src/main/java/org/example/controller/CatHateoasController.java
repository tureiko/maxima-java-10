package org.example.controller;

import org.example.model.Cat;
import org.example.model.CatModelAssembler;
import org.example.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2")
public class CatHateoasController {

    @Autowired private CatService catService;
    @Autowired CatModelAssembler assembler;

    @GetMapping("/cats")
    public CollectionModel<EntityModel<Cat>> getAll(){
        return assembler.toCollectionModel(catService.getCats());
    }

    @GetMapping("/cat/{id}")
    public EntityModel<Cat> getOne(@PathVariable Long id){
        return assembler.toModel(catService.readCat(id));
    }


}
