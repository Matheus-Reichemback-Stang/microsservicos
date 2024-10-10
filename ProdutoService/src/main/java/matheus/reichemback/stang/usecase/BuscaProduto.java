package matheus.reichemback.stang.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import matheus.reichemback.stang.domain.Produto;
import matheus.reichemback.stang.exception.EntityNotFoundException;
import matheus.reichemback.stang.repository.IProdutoRepository;

@Service
public class BuscaProduto {

	private IProdutoRepository produtoRepository;
	
	@Autowired
	public BuscaProduto(IProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	
	public Page<Produto> buscar(Pageable pageable){
		return produtoRepository.findAll(pageable);
	}
	
	public Produto buscarPorId(String id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(getClass(), "was not found!"));
	}
	
	public Boolean isCadastrado(String id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.isPresent() ? true : false;
	}
	
	public Produto buscarPorCodigo(Long codigo) {
		return produtoRepository.findByCodigo(codigo)
					.orElseThrow(() -> new EntityNotFoundException(getClass(), "was not found!"));
	}
}
