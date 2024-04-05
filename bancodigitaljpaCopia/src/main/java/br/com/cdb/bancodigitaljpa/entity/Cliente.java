package br.com.cdb.bancodigitaljpa.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private TipoCliente tipo;
	private TipoConta contaInicial;

	private String nome;
	private Long cpf;
	String senha;

	private LocalDate dataNascimento;

	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	@OneToOne(cascade = CascadeType.ALL)
	private ContaCorrente contaCorrente;

	@OneToOne(cascade = CascadeType.ALL)
	private ContaPoupanca contaPoupanca;

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente conta) {
		this.contaCorrente = conta;
	}

	public ContaPoupanca getContaPoupanca() {
		return contaPoupanca;
	}

	public void setContaPoupanca(ContaPoupanca contaPoupanca) {
		this.contaPoupanca = contaPoupanca;
	}

	public TipoConta getContaInicial() {
		return contaInicial;
	}

	public void setContaInicial(TipoConta contaInicial) {
		this.contaInicial = contaInicial;
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo;
	}

	public TipoCliente getTipo() {
		return tipo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
