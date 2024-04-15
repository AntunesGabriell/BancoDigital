package br.com.cdb.bancodigitaljpa.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cdb.bancodigitaljpa.entity.Apolice;
import br.com.cdb.bancodigitaljpa.entity.Cartao;
import br.com.cdb.bancodigitaljpa.entity.CartaoCredito;
import br.com.cdb.bancodigitaljpa.entity.CartaoDebito;
import br.com.cdb.bancodigitaljpa.entity.Cliente;
import br.com.cdb.bancodigitaljpa.entity.Conta;
import br.com.cdb.bancodigitaljpa.entity.ContaCorrente;
import br.com.cdb.bancodigitaljpa.entity.ContaPoupanca;
import br.com.cdb.bancodigitaljpa.entity.Endereco;
import br.com.cdb.bancodigitaljpa.entity.SeguroFraude;
import br.com.cdb.bancodigitaljpa.entity.SeguroViagem;
import br.com.cdb.bancodigitaljpa.entity.TipoCartao;
import br.com.cdb.bancodigitaljpa.entity.TipoCliente;
import br.com.cdb.bancodigitaljpa.entity.TipoConta;
import br.com.cdb.bancodigitaljpa.repository.ApoliceRepository;
import br.com.cdb.bancodigitaljpa.repository.CartoesRepository;
import br.com.cdb.bancodigitaljpa.repository.ClienteRepository;
import br.com.cdb.bancodigitaljpa.repository.ContaRepository;

@Service
public class CartoesService {

	@Autowired
	private CartoesRepository cartoesRepository;
	@Autowired
	private ContaRepository contaRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ApoliceRepository apoliceRepository;

	public Cartao addCartao(String numeroConta, long cpf, String senha, TipoCartao tipoCartao) {
		// validar os campos
		Cliente cliente = clienteRepository.findByCpf(cpf);
		Conta conta = contaRepository.findBynumero(numeroConta);

		SeguroFraude seguroFraude = new SeguroFraude("05" + cpf);
		apoliceRepository.save(seguroFraude);
		seguroFraude = (SeguroFraude) apoliceRepository.findBynumeroApolice("05" + cpf);

		if (TipoCartao.CREDITO == tipoCartao) {
			CartaoCredito cartaoCredito = new CartaoCredito();
			cartaoCredito.setAtivo(true);
			cartaoCredito.setNumero("04" + numeroConta);
			cartaoCredito.alterarSenha(senha);
			cartaoCredito.setLimiteCreditoAprovado(cliente.getTipo().getLimiteMensalCredito());
			cartaoCredito.getApolices().add(seguroFraude);
			cartoesRepository.save(cartaoCredito);

			

			seguroFraude.setCartao(cartaoCredito);
			seguroFraude.setDetalhesCartao("Numero do cartao de Credito: "+ cartaoCredito.getNumero() );
			apoliceRepository.save(seguroFraude);

			cartaoCredito.setConta(conta);

			return cartoesRepository.save(cartaoCredito);

		} else if (TipoCartao.DEBITO == tipoCartao) {
			CartaoDebito cartaoDebito = new CartaoDebito();
			cartaoDebito.setAtivo(true);
			cartaoDebito.setNumero("03" + numeroConta);
			cartaoDebito.alterarSenha(senha);
			cartaoDebito.setLimiteDiario(cliente.getTipo().getLimiteDiarioDebito());
			cartaoDebito.getApolices().add(seguroFraude);
			cartoesRepository.save(cartaoDebito);
			
			
			seguroFraude.setCartao(cartaoDebito);
			seguroFraude.setDetalhesCartao("Numero do cartao de debito: "+ cartaoDebito.getNumero() );
			
			apoliceRepository.save(seguroFraude);
			

			cartaoDebito.setConta(conta);
			return cartoesRepository.save(cartaoDebito);

		}

		return null;

	}

	public String pagamento(String numero, String senha, double valor) {

		Cartao cartao = cartoesRepository.findByNumeroAndSenha(numero, senha);
		String status = cartao.realizarPagamento(valor);
		cartoesRepository.save(cartao);
		return status;
	}

	public String alterarStatus(String numero, String senha) {

		Cartao cartao = cartoesRepository.findByNumeroAndSenha(numero, senha);
		cartao.setAtivo(!cartao.isAtivo());
		cartoesRepository.save(cartao);
		return "Status trocado para " + cartao.isAtivo();
	}

	public String alterarSenha(String numero, String novaSenha) {

		Cartao cartao = cartoesRepository.findByNumero(numero);
		cartao.alterarSenha(novaSenha);
		cartoesRepository.save(cartao);
		return "Senha alterada!";

	}

	public CartaoDebito alterarLimite(String numero, double novoLimite) {
		CartaoDebito cartaoDebito = (CartaoDebito) cartoesRepository.findByNumero(numero);
		cartaoDebito.alterarLimiteDiario(novoLimite);

		return cartoesRepository.save(cartaoDebito);
	}
	public SeguroViagem contratarSeguroViagem(String numero, double valor) {
		CartaoCredito cartaoCredito= (CartaoCredito) cartoesRepository.findByNumero(numero);
		
		if (cartaoCredito!=null) {
			SeguroViagem seguroViagem= new SeguroViagem( "06"+cartaoCredito.getNumero(), valor);
			seguroViagem.setCartao(cartaoCredito);
			return apoliceRepository.save(seguroViagem);
		}

		return null;
	}
}
