package chan.carlos.springbootwebflux.models.services;

import chan.carlos.springbootwebflux.models.documents.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {
    public Flux<Producto> findAll();
    public Flux<Producto> findAllWithNamesToUpperCase();
    public Flux<Producto> findAllWithNamesToUpperCaseAndRepeat();
    public Mono<Producto> findById(String id);
    public Mono<Producto> findByIdManual(String id);
    public Mono<Producto> save(Producto producto);
    public Mono<Void> delete(Producto producto);
}
