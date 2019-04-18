package com.api.rest.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.modelos.Planos;
import com.api.rest.repository.PlanRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST para manutenção de Planos")
@CrossOrigin(origins="*")

public class RecursosPlanos {
	@Autowired
	PlanRepository planRepository;
	
	@GetMapping("/cadastros/planos")
	@ApiOperation(value="Retorna lista de planos")
	public List<Planos> listaplanos(){
		return planRepository.findAll();
	}
	
	@GetMapping("/cadastros/planos/{id}")
	@ApiOperation(value="Retorna dados de um único plano")
	public List<Planos> listaumplano(@PathVariable(value="id")int id){
		return planRepository.findById(id);
	}
	
	//Method POST
	@PostMapping("/cadastros/plano")
	@ApiOperation(value="Salva o cadastro de um plano")
	public Planos salvaPlano(@RequestBody Planos plan) {
		return planRepository.save(plan);
	}
	
	//Method DELETE
	@DeleteMapping("cadastros/planos")
	@ApiOperation(value="Deleta um cadastro de plano")
	public void deletaPlano(@RequestBody Planos Plan) {
		planRepository.delete(Plan);
	}
	
	//Method PUT
	@PutMapping("cadastros/planos")
	@ApiOperation(value="Atualiza um plano")
	public Planos atualizaPlano(@RequestBody Planos plan) {
		return planRepository.save(plan);
	}

	
}