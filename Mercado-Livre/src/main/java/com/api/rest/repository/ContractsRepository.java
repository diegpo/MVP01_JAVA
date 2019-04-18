package com.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.rest.modelos.Contratos;

public interface ContractsRepository extends JpaRepository <Contratos, Long>{

	List<Contratos> findById(int id);
}
