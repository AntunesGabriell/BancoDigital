package br.com.cdb.bancodigitaljpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdb.bancodigitaljpa.entity.Cliente;
import br.com.cdb.bancodigitaljpa.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;

	@PostMapping("/add")
	public ResponseEntity<String> addCliente(@RequestBody Cliente cliente) {

		Cliente clienteAdicionado = clienteService.salvarCliente(cliente.getNome(), cliente.getCpf(),
				cliente.getDataNascimento(), cliente.getEndereco(), cliente.getTipo() , cliente.getContaInicial());

		if (clienteAdicionado != null) {
			return new ResponseEntity<>("Cliente " + cliente.getNome() + " adicionado  com sucesso!",
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Nome ou cpf invalidos!", HttpStatus.NOT_ACCEPTABLE);
		}

	}
	
	@GetMapping("/listALL")
	public ResponseEntity<List<Cliente>> getAllClientes(){
		
		List<Cliente> clientes  = clienteService.getClientes();
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}
}
