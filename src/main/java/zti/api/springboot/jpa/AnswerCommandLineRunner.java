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
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public void run(String... args) throws Exception {

		repository.save(new Answer("qA", null, true));
		repository.save(new Answer("qB", null, false));
		repository.save(new Answer("qC", null, false));
		repository.save(new Answer("qD", null, false));

		for (Answer answer : repository.findAll()) {
			log.info(answer.toString());
		}

	}

}
