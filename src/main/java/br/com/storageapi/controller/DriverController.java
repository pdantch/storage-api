package br.com.storageapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.storageapi.model.Driver;
import br.com.storageapi.repository.DriverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverRepository repository;

    public List<Driver> drivers = new ArrayList<>();

    @GetMapping("/{id}")
    public Driver findById(@PathVariable("id") Long id) {

        Optional<Driver> driverFound = this.repository.findById(id);

        if (driverFound.isPresent()) {
            return driverFound.get();
        }

        return null;
    }

    @RequestMapping
    public Driver save(@RequestBody Driver driver) {
        return this.repository.save(driver);
    }

    @RequestMapping("/list")
    public List<Driver> findAll() {
        return this.repository.findAll();
    }

    @RequestMapping("/remove/{id}")
    public void deleteById(@PathVariable Long id) {
        this.repository.deleteById(id);
    }
}
