package br.com.storageapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.storageapi.model.Target;
import br.com.storageapi.repository.TargetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/target")
public class TargetController {

    @Autowired
    private TargetRepository repository;

    public List<Target> targets = new ArrayList<>();

    @GetMapping("/{id}")
    public Target findById(@PathVariable("id") Long id) {

        Optional<Target> targetFound = this.repository.findById(id);

        if (targetFound.isPresent()) {
            return targetFound.get();
        }

        return null;
    }

    @RequestMapping
    public Target save(@RequestBody Target target) {
        return this.repository.save(target);
    }

    @RequestMapping("/list")
    public List<Target> findAll() {
        return this.repository.findAll();
    }

    @RequestMapping("/remove/{id}")
    public void deleteById(@PathVariable Long id) {
        this.repository.deleteById(id);
    }

}