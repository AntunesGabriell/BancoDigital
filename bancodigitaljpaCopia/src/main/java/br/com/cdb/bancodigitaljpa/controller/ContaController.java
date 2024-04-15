package br.com.cdb.bancodigitaljpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdb.bancodigitaljpa.entity.Cliente;
import br.com.cdb.bancodigitaljpa.entity.Conta;
import br.com.cdb.bancodigitaljpa.entity.TipoConta;
import br.com.cdb.bancodigitaljpa.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {
	@Autowired
	ContaService contaService;
	
	@PostMapping("/add/{cpf}/{tipo}")
	public ResponseEntity<String> adicionarConta(@PathVariable long cpf, @PathVariable TipoConta tipo) {
		
		Conta conta= contaService.abrirConta(cpf, tipo);
		if (conta != null) {
			 return new ResponseEntity<>("Conta adicionada com sucesso!", HttpStatus.OK);	
		}
		else {
			return new ResponseEntity<>("Erro!", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@PostMapping("/deposito/{numeroConta}/{valor}")
	public ResponseEntity<String> deposito_Corrente(@PathVariable String numeroConta, 
			@PathVariable double valor) {
		
		Conta conta = contaService.depositar(numeroConta, valor);
		if (conta != null) {
			 return new ResponseEntity<>("Deposito Realizado com Sucesso!", HttpStatus.OK);	
		}
		else {
			return new ResponseEntity<>("Erro!", HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@GetMapping("/saldo/{numeroConta}")
	public ResponseEntity<String> exibirSaldo(@PathVariable String numeroConta) {
		
		Conta conta = contaService.exibirSaldo(numeroConta);
		if (conta != null) {
			 return new ResponseEntity<>("Saldo: "+ conta.getSaldo(), HttpStatus.OK);	
		}
		else {
			return new ResponseEntity<>("Erro!", HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
