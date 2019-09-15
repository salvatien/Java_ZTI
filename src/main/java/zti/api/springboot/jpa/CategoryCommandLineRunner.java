//package zti.api.springboot.jpa;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CategoryCommandLineRunner implements CommandLineRunner {
//
//	private static final Logger log = LoggerFactory
//			.getLogger(CategoryCommandLineRunner.class);
//
//	@Autowired
//	private CategoryRepository repository;
//	@Autowired
//	private QuestionRepository questionRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		Question q = questionRepository.findById(new Long(483)).get();
//		Category cat = new Category("A", "B");
//		cat.addQuestion(q);
//		repository.save(cat);
//		repository.save(new Category("Rap", "Dziwki koks firefox"));
//		repository.save(new Category("Muzyka klasyczna", "Idzie Szopen no i Bach"));
//		repository.save(new Category("Rege", "Kolege"));
//
//		
//		
//		for (Category category : repository.findAll()) {
//			log.info(category.toString());
//		}
//
//	}
//
//}
