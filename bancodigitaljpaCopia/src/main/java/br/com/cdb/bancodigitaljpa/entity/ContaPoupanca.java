package br.com.cdb.bancodigitaljpa.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("POUPANCA")
public class ContaPoupanca extends Conta {
    private static final double RENDIMENTO_COMUM = 0.02;
    private static final double RENDIMENTO_PREMIUM = 0.03;
    private static final double RENDIMENTO_SUPER = 0.05;

    

    @Override
    public void sacar(double valor) {
        // Lógica para sacar dinheiro da conta poupança
    }

    public double calcularRendimento(Cliente cliente) {
        switch (cliente.getTipo()) {
            case COMUM:
                return RENDIMENTO_COMUM;
            case PREMIUM:
                return RENDIMENTO_PREMIUM;
            case SUPER:
                return RENDIMENTO_SUPER;
            default:
                return 0.0; // Tipo de cliente inválido
        }
    }
}
