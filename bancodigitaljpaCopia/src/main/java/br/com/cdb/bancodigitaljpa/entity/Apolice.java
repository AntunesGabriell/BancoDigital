package br.com.cdb.bancodigitaljpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.Date;

@Entity
public abstract class Apolice {

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	protected String tipo;
	private String numeroApolice;
	private LocalDate dataContratacao= LocalDate.now();
	private String detalhesCartao;
	protected double valorApolice;
	protected String condicoesAcionamento;

	@ManyToOne
	@JoinColumn(name = "cartao_id")
	private Cartao cartao;

	// Getters e Setters
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumeroApolice() {
		return numeroApolice;
	}

	public void setNumeroApolice(String numeroApolice) {
		this.numeroApolice = numeroApolice;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public String getDetalhesCartao() {
		return detalhesCartao;
	}

	public void setDetalhesCartao(String detalhesCartao) {
		this.detalhesCartao = detalhesCartao;
	}

	public double getValorApolice() {
		return valorApolice;
	}

	public void setValorApolice(double valorApolice) {
		this.valorApolice = valorApolice;
	}

	public String getCondicoesAcionamento() {
		return condicoesAcionamento;
	}

	public void setCondicoesAcionamento(String condicoesAcionamento) {
		this.condicoesAcionamento = condicoesAcionamento;
	}

	// toString para facilitar a visualização dos objetos
	@Override
	public String toString() {
		return "Apolice{" + "tipo='" + tipo + '\'' + ", numeroApolice='" + numeroApolice + '\'' + ", dataContratacao="
				+ dataContratacao + ", detalhesCartao='" + detalhesCartao + '\'' + ", valorApolice=" + valorApolice
				+ ", condicoesAcionamento='" + condicoesAcionamento + '\'' + '}';
	}
}