package zti.api.springboot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AnswerCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory
			.getLogger(AnswerCommandLineRunner.class);

	@Autowired
	private AnswerRepository repository;

	@Override
	public void run(String... args) throws Exception {

		repository.save(new Answer("A", null));
		repository.save(new Answer("B", null));
		repository.save(new Answer("C", null));
		repository.save(new Answer("D", null));

		for (Answer answer : repository.findAll()) {
			log.info(answer.toString());
		}

	}

}
