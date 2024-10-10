package matheus.reichemback.stang.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import matheus.reichemback.stang.domain.Cliente;
import matheus.reichemback.stang.repository.IClienteRepository;

@Service
public class GerenciaCliente {

	private IClienteRepository clienteRepository;
	
	@Autowired
	public GerenciaCliente(IClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Cliente cadastrar(@Valid Cliente cliente) {
		return clienteRepository.insert(cliente);
	}
	
	public Cliente atualizar(@Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public void remover(String id) {
		clienteRepository.deleteById(id);
	}
}
