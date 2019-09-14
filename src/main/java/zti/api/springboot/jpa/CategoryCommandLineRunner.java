package zti.api.springboot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory
			.getLogger(CategoryCommandLineRunner.class);

	@Autowired
	private CategoryRepository repository;

	@Override
	public void run(String... args) throws Exception {

		repository.save(new Category("Rock", "Brzdęk brzdęk"));
		repository.save(new Category("Rap", "Dziwki koks firefox"));
		repository.save(new Category("Muzyka klasyczna", "Idzie Szopen no i Bach"));
		repository.save(new Category("Rege", "Kolege"));

		for (Category category : repository.findAll()) {
			log.info(category.toString());
		}

	}

}
