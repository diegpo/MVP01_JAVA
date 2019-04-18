package com.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.rest.modelos.Planos;

public interface PlanRepository extends JpaRepository <Planos, Long>{
	
	List<Planos> findById(int id);

}
