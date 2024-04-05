package br.com.cdb.bancodigitaljpa.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cdb.bancodigitaljpa.entity.Cliente;
import br.com.cdb.bancodigitaljpa.entity.Conta;
import br.com.cdb.bancodigitaljpa.entity.ContaCorrente;
import br.com.cdb.bancodigitaljpa.entity.ContaPoupanca;
import br.com.cdb.bancodigitaljpa.entity.Endereco;
import br.com.cdb.bancodigitaljpa.entity.TipoCliente;
import br.com.cdb.bancodigitaljpa.entity.TipoConta;
import br.com.cdb.bancodigitaljpa.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente salvarCliente(String nome, long cpf, LocalDate date, Endereco endereco, TipoCliente tipoCliente,
			TipoConta tipoConta) {
		// validar os campos
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setDataNascimento(date);
		cliente.setEndereco(endereco);
		cliente.setTipo(tipoCliente);

		if (tipoConta == TipoConta.CORRENTE) {
			ContaCorrente conta = new ContaCorrente();
			cliente.setContaCorrente(conta);
		} else {
			ContaPoupanca conta = new ContaPoupanca();
			cliente.setContaPoupanca(conta);
		}

		return clienteRepository.save(cliente);

	}

	public List<Cliente> getClientes() {
		return clienteRepository.findAll();

	}
	public void transferenciaPix(Cliente cliente1, Cliente cliente2, float valor) {
		
	}
}
