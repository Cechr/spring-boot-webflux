package chan.carlos.springbootwebflux.controller;

import chan.carlos.springbootwebflux.models.documents.Producto;
import chan.carlos.springbootwebflux.models.services.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class ProductoController {
    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    ProductoService productoService;

    @GetMapping({"/", "/listar"})
    public String listar(Model model){
        Flux<Producto> productos = productoService.findAllWithNamesToUpperCase();

        productos.subscribe(producto -> log.info(producto.toString()));

        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Listado de productos");

        return "listar";
    }

    @GetMapping("/listar-datadriver")
    public String listarDataDriver(Model model){
        Flux<Producto> productos = productoService.findAllWithNamesToUpperCase().delayElements(Duration.ofSeconds(1));

        productos.subscribe(producto -> log.info(producto.toString()));

        model.addAttribute("productos", new ReactiveDataDriverContextVariable(productos, 1));
        model.addAttribute("titulo", "Listado de productos");

        return "listar";
    }

    @GetMapping("/listar-full")
    public String listarFull(Model model){
        Flux<Producto> productos = productoService.findAllWithNamesToUpperCaseAndRepeat();

        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Listado de productos");

        return "listar";
    }

    @GetMapping("/listar-chunked")
    public String listarChunked(Model model){
        Flux<Producto> productos = productoService.findAllWithNamesToUpperCaseAndRepeat();

        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Listado de productos");

        return "listar-chunked";
    }
}
