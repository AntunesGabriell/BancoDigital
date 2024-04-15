package br.com.cdb.bancodigitaljpa.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private boolean ativo;
    private String senha;
    
    
   
	@OneToMany(mappedBy = "cartao")
	@JsonIgnoreProperties("cartao")
    private List<Apolice> apolices = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;


    
    
    public List<Apolice> getApolices() {
		return apolices;
	}

	public void setApolices(List<Apolice> apolices) {
		this.apolices = apolices;
	}
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public abstract String realizarPagamento(double valor);

    public void mudarStatus(boolean ativo) {
        this.ativo = ativo;
    }

    public void alterarSenha(String novaSenha) {
        this.senha = novaSenha;
    }
    public boolean isAtivo() {
        return ativo;
    }
}