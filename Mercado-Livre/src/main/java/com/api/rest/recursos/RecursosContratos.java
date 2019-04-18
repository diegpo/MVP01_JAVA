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

import com.api.rest.modelos.Contratos;
import com.api.rest.repository.ContractsRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST para manutenção de contratos")
@CrossOrigin(origins="*")
public class RecursosContratos {
	@Autowired
	ContractsRepository contractsRepository;
	
	@GetMapping("/cadastros/contratos")
	@ApiOperation(value="Retorna uma lista de contratos")
	public List<Contratos> listacontratos(){
		return contractsRepository.findAll();
	}
	@GetMapping("/cadastros/contratos/{id}")
	@ApiOperation(value="Retorna dados de um único contrato")
	public List<Contratos> listaumcontrato(@PathVariable(value="id")int id){
		return contractsRepository.findAll();
	}
	
	//Method POST
	@PostMapping("/cadastros/contratos")
	@ApiOperation(value="Salva um contrato")
	public Contratos salvaContrato(@RequestBody Contratos contract) {
		return contractsRepository.save(contract);
	}
	
	//Method DELETE
	@DeleteMapping("cadastros/contratos")
	@ApiOperation(value="Deleta um contrato")
	public void deletaContrato(@RequestBody Contratos contract) {
		contractsRepository.delete(contract);
	}
	
	//Method PUT
	@PutMapping("cadastros/contrato")
	@ApiOperation(value="Atualiza um contrato")
	public Contratos atualizaContrato(@RequestBody Contratos contract) {
		return contractsRepository.save(contract);
	}
}
