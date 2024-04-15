package br.com.cdb.bancodigitaljpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdb.bancodigitaljpa.entity.Cliente;
import br.com.cdb.bancodigitaljpa.entity.Transferencia;
import br.com.cdb.bancodigitaljpa.service.ClienteService;
import br.com.cdb.bancodigitaljpa.service.ContaService;
import br.com.cdb.bancodigitaljpa.service.TransferenciaService;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {
	
	@Autowired
	private TransferenciaService transferenciaService;
	
	@Autowired
	private ContaService contaService;
	@PostMapping("/")
	public ResponseEntity<String> transferencia(@RequestBody Transferencia transferencia)
	{
		Transferencia transferenciaRealizada = transferenciaService.transferenciaPix(transferencia.getNumeroContaOrigem(),
				transferencia.getNumeroContaDestino(), transferencia.getValor());
		
		if (transferenciaRealizada != null) {
			return new ResponseEntity<>(" Transferencia realizada com sucesso!",
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Saldo insuficiente!", HttpStatus.NOT_ACCEPTABLE);
		}
	
	}

}
