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

import com.api.rest.modelos.Clientes;
import com.api.rest.repository.ClientRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST para manutenção de Cadastros")
@CrossOrigin(origins="*")
public class RecursosClientes {
	@Autowired
	ClientRepository clientRepository;
	
	//Methods Get
	@GetMapping("/cadastros/clientes")
	@ApiOperation(value="Retorna lista de clientes")
	public List<Clientes> listaclientes(){
		return clientRepository.findAll();
	}
	@GetMapping("/cadastros/clientes/{id}")
	@ApiOperation(value="Retorna dados de um único cadastro")
	public List<Clientes> listaumcliente(@PathVariable(value="id")int id){
		return clientRepository.findById(id);
	}
	
	//Method POST
	@PostMapping("/cadastros/cliente")
	@ApiOperation(value="Salva o cadastro de um cliente")
	public Clientes salvaClient(@RequestBody Clientes client) {
		return clientRepository.save(client);
	}
	
	//Method DELETE
	@DeleteMapping("cadastros/cliente")
	@ApiOperation(value="Deleta um cadastro de cliente")
	public void deletaClient(@RequestBody Clientes client) {
		clientRepository.delete(client);
	}
	
	//Method PUT
	@PutMapping("cadastros/client")
	@ApiOperation(value="Atualiza um cliente")
	public Clientes atualizaClient(@RequestBody Clientes client) {
		return clientRepository.save(client);
	}			
}
