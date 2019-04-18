package com.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.rest.modelos.Clientes;

public interface ClientRepository extends JpaRepository <Clientes, Long>{

	List<Clientes> findById(int id);
}
