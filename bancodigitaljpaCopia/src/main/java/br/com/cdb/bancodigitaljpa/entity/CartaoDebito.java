package br.com.cdb.bancodigitaljpa.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public class CartaoDebito extends Cartao {
    private double limiteDiario;
    private double limiteUtilizadoDiario;

    // Getters e setters
    // Construtores

    @Override
    public String realizarPagamento(double valor) {
        if (isAtivo() && (limiteUtilizadoDiario + valor) <= limiteDiario) {
            limiteUtilizadoDiario += valor;
            return "Pagamento realizado com Sucesso!";
        } else {
        	return "Excedeu o limite disponível ou cartão desativado";
        }
    }

    public void alterarLimiteDiario(double novoLimite) {
        this.limiteDiario = novoLimite;
    }

	public double getLimiteDiario() {
		return limiteDiario;
	}

	public void setLimiteDiario(double limiteDiario) {
		this.limiteDiario = limiteDiario;
	}

	public double getLimiteUtilizadoDiario() {
		return limiteUtilizadoDiario;
	}

	public void setLimiteUtilizadoDiario(double limiteUtilizadoDiario) {
		this.limiteUtilizadoDiario = limiteUtilizadoDiario;
	}
}