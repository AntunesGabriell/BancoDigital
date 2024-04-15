package br.com.cdb.bancodigitaljpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class CartaoCredito extends Cartao {
	private double limiteCreditoAprovado;
	private double limiteUtilizado;
	


	public String realizarPagamento(double valor) {
		if (isAtivo() && valor <= (limiteCreditoAprovado - limiteUtilizado)) {
			limiteUtilizado += valor;
			return "Pagamento realizado com Sucesso!";
		} else {
			return "Excedeu o limite disponível ou cartão desativado";
		}
	}

	public double getLimiteCreditoAprovado() {
		return limiteCreditoAprovado;
	}

	public void setLimiteCreditoAprovado(double limiteCreditoAprovado) {
		this.limiteCreditoAprovado = limiteCreditoAprovado;
	}

	public double getLimiteUtilizado() {
		return limiteUtilizado;
	}

	public void setLimiteUtilizado(double limiteUtilizado) {
		this.limiteUtilizado = limiteUtilizado;
	}

	// Outros métodos específicos do cartão de crédito
}
