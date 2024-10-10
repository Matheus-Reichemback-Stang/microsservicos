package matheus.reichemback.stang.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import matheus.reichemback.stang.domain.Cliente;
import matheus.reichemback.stang.usecase.BuscaCliente;
import matheus.reichemback.stang.usecase.GerenciaCliente;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

	private BuscaCliente buscaCliente;
	
	private GerenciaCliente gerenciaCliente;
	
	public ClienteResource(BuscaCliente buscaCliente, GerenciaCliente gerenciaCliente) {
		this.buscaCliente = buscaCliente;
		this.gerenciaCliente = gerenciaCliente;
	}
	
	@GetMapping
	public ResponseEntity<Page<Cliente>> buscar(Pageable pageable){
		return ResponseEntity.ok(this.buscaCliente.buscar(pageable));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable(name = "id", required = true) String id){
		return ResponseEntity.ok(this.buscaCliente.buscarPorId(id));
	}
	
	@GetMapping(value = "isCadastrado/{id}")
	public ResponseEntity<Boolean> isCadastrado(@PathVariable(name = "id", required = true) String id){
		return ResponseEntity.ok(this.buscaCliente.isCadastrado(id));
	}
	
	@GetMapping(value = "cpf/{cpf}")
	public ResponseEntity<Cliente> buscarPorCpf(@PathVariable(name = "cpf", required = true)Long cpf){
		return ResponseEntity.ok(this.buscaCliente.buscarPorCpf(cpf));
	}
	
	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente){
		return ResponseEntity.ok(this.gerenciaCliente.cadastrar(cliente));
	}
	
	@PutMapping
	public ResponseEntity<Cliente> atualizar(@RequestBody @Valid Cliente cliente){
		return ResponseEntity.ok(this.gerenciaCliente.atualizar(cliente));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> remover(@PathVariable(name = "id", required = true)String id) {
		this.gerenciaCliente.remover(id);
		return ResponseEntity.ok("Removido com sucesso!");
	}
}
