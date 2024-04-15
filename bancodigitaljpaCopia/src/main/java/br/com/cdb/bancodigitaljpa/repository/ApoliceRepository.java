package br.com.cdb.bancodigitaljpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigitaljpa.entity.Apolice;
import br.com.cdb.bancodigitaljpa.entity.Cliente;


@Repository
public interface  ApoliceRepository extends JpaRepository<Apolice, Long>{
	Apolice findBynumeroApolice(String numeroApolice);
}
