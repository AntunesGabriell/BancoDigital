package br.com.cdb.bancodigitaljpa.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class SeguroViagem extends Apolice {

	{
     tipo = "SeguroViagem";
    condicoesAcionamento = "Em caso de emergência durante a viagem, entre em contato conosco.";
	}
    // Construtor
	
	public SeguroViagem() {
		
	}
	
    public SeguroViagem(String numeroApolice, double valorApolice) {
        this.setNumeroApolice(numeroApolice);
        this.setValorApolice(valorApolice);
       
    }
    public double calcularTaxa(TipoCliente tipo) {
    	
    	if(tipo == TipoCliente.SUPER) {
    		return 0;
    	}
    	
    	return 50;
    }

	
  
}