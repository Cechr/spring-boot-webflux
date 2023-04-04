package chan.carlos.springbootwebflux.models.services;

import chan.carlos.springbootwebflux.controller.ProductoController;
import chan.carlos.springbootwebflux.models.documents.Producto;
import chan.carlos.springbootwebflux.models.repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoServiceImpl implements ProductoService{
    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    ProductoRepository productoRepository;
    @Override
    public Flux<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Flux<Producto> findAllWithNamesToUpperCase() {
        return findAll()
                .map(producto -> {
                    producto.setNombre(producto.getNombre().toUpperCase());
                    return producto;
                });
    }

    @Override
    public Flux<Producto> findAllWithNamesToUpperCaseAndRepeat() {
        return findAllWithNamesToUpperCase().repeat(5000);
    }

    @Override
    public Mono<Producto> findById(String id) {
        return productoRepository.findById(id);
    }

    @Override
    public Mono<Producto> findByIdManual(String id) {
        return findAll()
                .filter(p -> p.getId().equals(id))
                .next()
                .map(p -> {
                    p.setNombre(p.getNombre().toUpperCase());
                    return p;
                });
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Mono<Void> delete(Producto producto) {
        return productoRepository.delete(producto);
    }
}
