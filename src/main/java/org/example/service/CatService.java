package org.example.service;


import jakarta.annotation.PostConstruct;
import org.example.exceptions.CatNotFoundException;
import org.example.model.Cat;
import org.example.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {
    @Autowired
    private CatRepository repo;

    @PostConstruct
    public void init() {
        repo.save(new Cat("Мурзик", 10, true));
        repo.save(new Cat("Барсик", 5, false));
        repo.save(new Cat("Мурка", 3, false));
    }

    public List<Cat> getCats() {
        return repo.findAll();
    }

    public void saveCat(Cat newCat) {
        repo.save(newCat);
    }

    public Cat readCat(Long id) {
        return repo.findById(id).orElseThrow(() -> new CatNotFoundException(id.toString()));
    }

    public void deleteCat(Long id) {
        repo.deleteById(id);
    }

    public boolean update(Long id, Cat newCat) {
        if (repo.existsById(id)) {
            newCat.setId(id);
            repo.save(newCat);
            return true;
        }
        return false;

    }
}
