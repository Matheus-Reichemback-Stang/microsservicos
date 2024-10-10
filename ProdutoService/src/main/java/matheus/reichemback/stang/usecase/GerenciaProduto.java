package matheus.reichemback.stang.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import matheus.reichemback.stang.domain.Produto;
import matheus.reichemback.stang.repository.IProdutoRepository;

@Service
public class GerenciaProduto {

	private IProdutoRepository produtoRepository;
	
	@Autowired
	public GerenciaProduto(IProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public Produto cadastrar(@Valid Produto produto) {
		return produtoRepository.insert(produto);
	}
	
	public Produto atualizar(@Valid Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void remover(String id) {
		produtoRepository.deleteById(id);
	}
}
