package chan.carlos.springbootwebflux.controller;

import chan.carlos.springbootwebflux.models.documents.Producto;
import chan.carlos.springbootwebflux.models.services.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {
    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    ProductoService productoService;

    @GetMapping
    public Flux<Producto> index() {
        Flux<Producto> productos = productoService.findAllWithNamesToUpperCase().doOnNext(producto -> log.info(producto.toString()));
        return productos;
    }

    @GetMapping("/{id}")
    public Mono<Producto> show(@PathVariable String id) {
//        Mono<Producto> producto = productoService.findById(id);
        Mono<Producto> producto = productoService.findByIdManual(id).doOnNext(p -> log.info(p.toString()));
        return producto;
    }
}
