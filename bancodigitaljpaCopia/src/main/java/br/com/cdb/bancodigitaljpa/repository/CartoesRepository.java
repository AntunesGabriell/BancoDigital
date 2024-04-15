package br.com.cdb.bancodigitaljpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigitaljpa.entity.Cartao;
import br.com.cdb.bancodigitaljpa.entity.Cliente;


@Repository
public interface CartoesRepository extends JpaRepository<Cartao, Long>{
	Cartao findByNumeroAndSenha(String numero, String senha);

	Cartao findByNumero(String numero);
}
