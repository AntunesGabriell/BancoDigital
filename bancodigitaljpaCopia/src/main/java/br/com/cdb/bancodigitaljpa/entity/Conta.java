package br.com.cdb.bancodigitaljpa.entity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public abstract class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String numero;

	private double saldo;


	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToMany
    @JoinTable(name = "conta_transferencia",
               joinColumns = @JoinColumn(name = "conta_id"),
               inverseJoinColumns = @JoinColumn(name = "transferencia_id"))
	private List<Transferencia> transferencias = new ArrayList<>();

	@OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
	private List<Cartao> cartoes = new ArrayList<>();

	
	public void adiconarCartao(Cartao cartao) {
		cartoes.add(cartao);
	}
	
	public void adicionarTransferencia(Transferencia transferencia) {
		transferencias.add(transferencia);
	}
	
	public Cliente getClient() {
		return cliente;
	}

	public void setClient(Cliente client) {
		this.cliente = client;
	}

	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	

	@Enumerated(EnumType.STRING)
	private TipoConta tipo;

	public long getId() {
		return id;
	}

	public TipoConta getTipo() {
		return tipo;
	}

	public void setTipo(TipoConta tipo) {
		this.tipo = tipo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void depositar(double valor) {
		saldo += valor;
	}

	public abstract void sacar(double valor);
}
