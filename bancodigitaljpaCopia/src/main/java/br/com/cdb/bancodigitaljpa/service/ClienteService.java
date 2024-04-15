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
import br.com.cdb.bancodigitaljpa.entity.Transferencia;
import br.com.cdb.bancodigitaljpa.repository.ClienteRepository;
import br.com.cdb.bancodigitaljpa.repository.ContaRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ContaRepository contaRepository;

	public Cliente salvarCliente(String nome, long cpf, String senha, LocalDate date, Endereco endereco,
			TipoCliente tipoCliente, TipoConta tipoConta) {
		// validar os campos
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setDataNascimento(date);
		cliente.setEndereco(endereco);
		cliente.setTipo(tipoCliente);
		cliente.setSenha(senha);
		
		clienteRepository.save(cliente);
		cliente =clienteRepository.findByCpf(cpf);
		
		if (tipoConta == TipoConta.CORRENTE) {			
			ContaCorrente contaCorrente = new ContaCorrente();
			contaCorrente.setNumero("01" + cpf);
			contaCorrente.setClient(cliente);
			contaRepository.save(contaCorrente);
			cliente.adicionarConta( contaCorrente);
			
		} else {
			ContaPoupanca contaPoupanca = new ContaPoupanca();			
			contaPoupanca.setNumero("02" + cliente.getCpf());
			contaPoupanca.setClient(cliente);
			contaRepository.save(contaPoupanca);
			cliente.adicionarConta(contaPoupanca);

			
		}

		return clienteRepository.save(cliente);

	}

	public List<Cliente> getClientes() {
		return clienteRepository.findAll();

	}

	
}
