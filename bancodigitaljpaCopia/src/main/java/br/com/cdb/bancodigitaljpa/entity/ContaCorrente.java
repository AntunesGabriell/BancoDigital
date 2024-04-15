package br.com.cdb.bancodigitaljpa.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CORRENTE")
public class ContaCorrente extends Conta {
    private static final double TAXA_MANUTENCAO_COMUM = 10.0;
    private static final double TAXA_MANUTENCAO_PREMIUM = 5.0;
    private static final double TAXA_MANUTENCAO_SUPER = 0.0;
    private static final TipoConta tipo = TipoConta.CORRENTE;
   

    @Override
    public void sacar(double valor) {
        // Lógica para sacar dinheiro da conta corrente
    }

    public double calcularTaxaManutencao(Cliente cliente) {
        switch (cliente.getTipo()) {
            case COMUM:
                return TAXA_MANUTENCAO_COMUM;
            case PREMIUM:
                return TAXA_MANUTENCAO_PREMIUM;
            case SUPER:
                return TAXA_MANUTENCAO_SUPER;
            default:
                return 0.0; // Tipo de cliente inválido
        }
    }
}
