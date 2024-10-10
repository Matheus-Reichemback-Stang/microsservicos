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
import matheus.reichemback.stang.domain.Produto;
import matheus.reichemback.stang.usecase.BuscaProduto;
import matheus.reichemback.stang.usecase.GerenciaProduto;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {

	private BuscaProduto buscaProduto;
	
	private GerenciaProduto gerenciaProduto;
	
	public ProdutoResource(BuscaProduto buscaProduto, GerenciaProduto gerenciaProduto) {
		this.buscaProduto = buscaProduto;
		this.gerenciaProduto = gerenciaProduto;
	}
	
	@GetMapping
	public ResponseEntity<Page<Produto>> buscar(Pageable pageable){
		return ResponseEntity.ok(this.buscaProduto.buscar(pageable));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable(name = "id", required = true) String id){
		return ResponseEntity.ok(this.buscaProduto.buscarPorId(id));
	}
	
	@GetMapping(value = "isCadastrado/{id}")
	public ResponseEntity<Boolean> isCadastrado(@PathVariable(name = "id", required = true) String id){
		return ResponseEntity.ok(this.buscaProduto.isCadastrado(id));
	}
	
	@GetMapping(value = "codigo/{codigo}")
	public ResponseEntity<Produto> buscarPorCodigo(@PathVariable(name = "codigo", required = true)Long codigo){
		return ResponseEntity.ok(this.buscaProduto.buscarPorCodigo(codigo));
	}
	
	@PostMapping
	public ResponseEntity<Produto> cadastrar(@RequestBody @Valid Produto produto){
		return ResponseEntity.ok(this.gerenciaProduto.cadastrar(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> atualizar(@RequestBody @Valid Produto produto){
		return ResponseEntity.ok(this.gerenciaProduto.atualizar(produto));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> remover(@PathVariable(name = "id", required = true)String id) {
		this.gerenciaProduto.remover(id);
		return ResponseEntity.ok("Removido com sucesso!");
	}
}
