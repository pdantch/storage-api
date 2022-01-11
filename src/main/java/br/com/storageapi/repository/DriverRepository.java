package br.com.storageapi.repository;

import br.com.storageapi.model.Driver;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {

}