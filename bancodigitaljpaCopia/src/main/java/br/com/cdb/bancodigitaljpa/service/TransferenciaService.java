package br.com.cdb.bancodigitaljpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cdb.bancodigitaljpa.entity.Conta;
import br.com.cdb.bancodigitaljpa.entity.Transferencia;
import br.com.cdb.bancodigitaljpa.repository.ContaRepository;
import br.com.cdb.bancodigitaljpa.repository.TransferenciaRepository;

@Service
public class TransferenciaService {
	@Autowired
	private TransferenciaRepository transferenciaRepository;
	@Autowired
	private ContaRepository contaRepository;

	public Transferencia transferenciaPix(String numeroContaOrigem, String numeroContaDestino, double valor) {

		Conta contaOrigem = contaRepository.findBynumero(numeroContaOrigem);
		Conta contaDestino = contaRepository.findBynumero(numeroContaDestino);

		Transferencia transferencia = new Transferencia();

		if (contaOrigem.getSaldo() > valor) {
			contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
			contaDestino.depositar(valor);
			
			transferencia.setValor(valor);
			transferencia.adicionarConta(contaDestino);
			transferencia.adicionarConta(contaOrigem);			
			transferenciaRepository.save(transferencia);
			
			contaOrigem.adicionarTransferencia(transferencia);
			contaDestino.adicionarTransferencia(transferencia);
			
			contaRepository.save(contaOrigem);
			contaRepository.save(contaDestino);
			
			return transferencia;
		}

		return null;
	}

}
