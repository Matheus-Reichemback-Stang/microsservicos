package matheus.reichemback.stang.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import matheus.reichemback.stang.domain.Produto;

@Repository
public interface IProdutoRepository extends MongoRepository<Produto, String> {

	Optional<Produto> findByCodigo(Long codigo);
}
