package br.com.cdb.bancodigitaljpa.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdb.bancodigitaljpa.entity.Cartao;
import br.com.cdb.bancodigitaljpa.entity.CartaoDebito;
import br.com.cdb.bancodigitaljpa.entity.Cliente;
import br.com.cdb.bancodigitaljpa.entity.SeguroViagem;
import br.com.cdb.bancodigitaljpa.entity.TipoCartao;
import br.com.cdb.bancodigitaljpa.service.CartoesService;

@RestController
@RequestMapping("/cartao")
public class CartoesController {

	@Autowired
	private CartoesService cartoesService;

	@PostMapping("/add/{numeroConta}/{cpf}/{senha}/{tipoCartao}")
	public ResponseEntity<String> adicionarCartao(@PathVariable String numeroConta, @PathVariable long cpf,
			@PathVariable String senha, @PathVariable TipoCartao tipoCartao) {

		Cartao cartaoAdicionado = cartoesService.addCartao(numeroConta, cpf, senha, tipoCartao);

		if (cartaoAdicionado != null) {
			return new ResponseEntity<>("Cartao adicionado com Sucesso!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Erro!", HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@PostMapping("/pagamentos/{numeroCartao}/{senha}/{valor}")
	public ResponseEntity<String> pagamentos(@PathVariable String numeroCartao, @PathVariable String senha,
			@PathVariable double valor) {

		String statusPagamento = cartoesService.pagamento(numeroCartao, senha, valor);
		return new ResponseEntity<>(statusPagamento, HttpStatus.CREATED);

	}
	
	@PostMapping("/status/{numeroCartao}/{senha}/")
	public ResponseEntity<String>  status(@PathVariable String numeroCartao, @PathVariable String senha){
		
		String status = cartoesService.alterarStatus(numeroCartao, senha);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	@PostMapping("/status/alterarSenha/{numero}/{novaSenha}")
	public  ResponseEntity<String>  alterarSenha(@PathVariable String numero, @PathVariable String novaSenha){
		
		String status = cartoesService.alterarSenha(numero, novaSenha);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
		
	}
	@PostMapping("/limite/alterarLimite/{numero}/{novoLimite}")
	public  ResponseEntity<String>  alterarLmite(@PathVariable String numero, @PathVariable double novoLimite){
			
			CartaoDebito cartaoDebito = cartoesService.alterarLimite(numero, novoLimite);
			if (cartaoDebito != null) {
				return new ResponseEntity<>("Limite alterado para: " + cartaoDebito.getLimiteDiario(), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Erro!", HttpStatus.NOT_ACCEPTABLE);
			}
		
	}
	@PostMapping("/contratarSeguroViagem/{numero}/{valor}")
	public ResponseEntity<String>  contratarSeguroViagem(@PathVariable String numero, @PathVariable double valor){
		
		SeguroViagem seguroViagem = cartoesService.contratarSeguroViagem(numero, valor);
		if (seguroViagem != null) {
			return new ResponseEntity<>("Seguro com o valor: " + seguroViagem.getValorApolice()+ 
					" contratado com Sucesso!"
			, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Erro!", HttpStatus.NOT_ACCEPTABLE);
		}
	} 

}
