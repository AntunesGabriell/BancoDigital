package br.com.cdb.bancodigitaljpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cdb.bancodigitaljpa.entity.Cliente;
import br.com.cdb.bancodigitaljpa.entity.Conta;
import br.com.cdb.bancodigitaljpa.entity.ContaCorrente;
import br.com.cdb.bancodigitaljpa.entity.ContaPoupanca;
import br.com.cdb.bancodigitaljpa.entity.TipoConta;
import br.com.cdb.bancodigitaljpa.repository.ClienteRepository;
import br.com.cdb.bancodigitaljpa.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente findByCpfAndSenha(long cpf, String senha) {
		return clienteRepository.findByCpfAndSenha(cpf, senha);
	}

	public Conta abrirConta(long cpf, TipoConta tipoConta) {
		Cliente cliente = clienteRepository.findByCpf(cpf);
		boolean possuiContaCorrente = false;
		boolean possuiContaPoupanca = false;

		for (Conta conta : cliente.getContas()) {
			if (conta instanceof ContaCorrente) {
				possuiContaCorrente = true;
			}
			if (conta instanceof ContaPoupanca) {
				possuiContaPoupanca = true;
			}
		}
		if (tipoConta== TipoConta.POUPANCA && !possuiContaPoupanca) {
			ContaPoupanca contaPoupanca = new ContaPoupanca();
			contaPoupanca.setNumero("02" + cliente.getCpf());
			contaPoupanca.setClient(cliente);
			return contaRepository.save(contaPoupanca);
		}
		if ((tipoConta== TipoConta.CORRENTE && !possuiContaCorrente)) {
			ContaCorrente contaCorrente = new ContaCorrente();
			contaCorrente.setNumero("01" + cliente.getCpf());
			contaCorrente.setClient(cliente);
			return contaRepository.save(contaCorrente);
		}				
		return null;

	}

	public Conta depositar(String numeroConta, double valor) {
		Conta conta = contaRepository.findBynumero(numeroConta);
		conta.setSaldo(conta.getSaldo() + valor);
		return contaRepository.save(conta);

	}

	public Conta exibirSaldo(String numeroConta) {
		Conta conta = contaRepository.findBynumero(numeroConta);
		return conta;

	}

}
