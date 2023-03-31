package chan.carlos.springbootwebflux.models.repository;

import chan.carlos.springbootwebflux.models.documents.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoRepository extends ReactiveMongoRepository<Producto, String> {
}
