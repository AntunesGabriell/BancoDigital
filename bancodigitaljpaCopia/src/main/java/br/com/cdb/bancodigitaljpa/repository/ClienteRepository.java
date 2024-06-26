package br.com.cdb.bancodigitaljpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 
import br.com.cdb.bancodigitaljpa.entity.Cliente;

@Repository
public interface  ClienteRepository extends JpaRepository<Cliente, Long>{
	Cliente findByCpfAndSenha(long cpf, String senha);
	Cliente findByCpf(long cpf);
}
