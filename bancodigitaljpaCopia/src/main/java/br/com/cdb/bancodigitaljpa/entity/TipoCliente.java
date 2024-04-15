package br.com.cdb.bancodigitaljpa.entity;

public enum TipoCliente {
    COMUM(0.05, 0.03, 1000.0, 500.0, 0.02), // Taxa de manutenção, Rendimento, Limite Mensal Crédito, Limite Diário Débito, Taxa de uso do cartão
    PREMIUM(0.03, 0.05, 5000.0, 2000.0, 0.01),
    SUPER(0.02, 0.07, 10000.0, 3000.0, 0.005);

    private double taxaManutencao;
    private double rendimento;
    private double limiteMensalCredito;
    private double limiteDiarioDebito;
    private double taxaUsoCartao;

    TipoCliente(double taxaManutencao, double rendimento, double limiteMensalCredito, double limiteDiarioDebito, double taxaUsoCartao) {
        this.taxaManutencao = taxaManutencao;
        this.rendimento = rendimento;
        this.limiteMensalCredito = limiteMensalCredito;
        this.limiteDiarioDebito = limiteDiarioDebito;
        this.taxaUsoCartao = taxaUsoCartao;
    }

    public double getTaxaManutencao() {
        return taxaManutencao;
    }

    public double getRendimento() {
        return rendimento;
    }

    public double getLimiteMensalCredito() {
        return limiteMensalCredito;
    }

    public double getLimiteDiarioDebito() {
        return limiteDiarioDebito;
    }

    public double getTaxaUsoCartao() {
        return taxaUsoCartao;
    }
}
