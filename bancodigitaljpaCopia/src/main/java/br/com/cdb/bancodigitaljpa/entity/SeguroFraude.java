package br.com.cdb.bancodigitaljpa.entity;

import jakarta.persistence.Entity;

@Entity
public class SeguroFraude  extends Apolice{
	{
	tipo = "SeguroFraude";
	condicoesAcionamento = "Em caso de golpe , entre em contato conosco.";
	valorApolice= 5000;
	}
	
	public SeguroFraude()
	{
		
	}
	
	public SeguroFraude(String numeroApolice)
	{
		this.setNumeroApolice(numeroApolice);
	}
}
