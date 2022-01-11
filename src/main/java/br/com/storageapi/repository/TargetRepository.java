package br.com.storageapi.repository;

import br.com.storageapi.model.Target;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetRepository extends JpaRepository<Target, Long> {
    
}