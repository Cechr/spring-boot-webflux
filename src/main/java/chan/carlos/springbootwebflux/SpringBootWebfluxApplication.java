package chan.carlos.springbootwebflux;

import chan.carlos.springbootwebflux.models.documents.Producto;
import chan.carlos.springbootwebflux.models.repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

@SpringBootApplication
public class SpringBootWebfluxApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);

	@Autowired
	ReactiveMongoTemplate reactiveMongoTemplate;

	@Autowired
	ProductoRepository productoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		reactiveMongoTemplate.dropCollection("productos").subscribe();

		Flux.just(
				new Producto("ProductoRepository A", 998.89),
				new Producto("ProductoRepository B", 129.99),
				new Producto("ProductoRepository C", 350.00),
				new Producto("ProductoRepository D", 489.98),
				new Producto("ProductoRepository E", 100.50)
				)
				.flatMap(producto -> {
					producto.setCreateAt(new Date());
					return productoRepository.save(producto);
				})
				.subscribe(producto -> log.info("Insert: " + producto.toString()));
	}
}
