package matheus.reichemback.stang.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import matheus.reichemback.stang.domain.Cliente;
import matheus.reichemback.stang.exception.EntityNotFoundException;
import matheus.reichemback.stang.repository.IClienteRepository;

@Service
public class BuscaCliente {

	private IClienteRepository clienteRepository;
	
	@Autowired
	public BuscaCliente(IClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	
	public Page<Cliente> buscar(Pageable pageable){
		return clienteRepository.findAll(pageable);
	}
	
	public Cliente buscarPorId(String id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(getClass(), "was not found!"));
	}
	
	public Boolean isCadastrado(String id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.isPresent() ? true : false;
	}
	
	public Cliente buscarPorCpf(Long cpf) {
		return clienteRepository.findByCpf(cpf)
					.orElseThrow(() -> new EntityNotFoundException(getClass(), "was not found!"));
	}
}
