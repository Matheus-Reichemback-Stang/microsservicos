package matheus.reichemback.stang.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import matheus.reichemback.stang.domain.Cliente;

@Repository
public interface IClienteRepository extends MongoRepository<Cliente, String> {

	Optional<Cliente> findByCpf(Long cpf);
}
