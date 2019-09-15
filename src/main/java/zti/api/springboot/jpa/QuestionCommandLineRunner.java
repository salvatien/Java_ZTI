//package zti.api.springboot.jpa;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class QuestionCommandLineRunner implements CommandLineRunner {
//
//	private static final Logger log = LoggerFactory
//			.getLogger(QuestionCommandLineRunner.class);
//
//	@Autowired
//	private QuestionRepository repository;
//	@Autowired
//	private CategoryRepository categoryRepository;
//	@Autowired
//	private AnswerRepository answerRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Category category = categoryRepository.findById(new Long(145)).get();
//		Answer correctAnswer = new Answer();
//		Optional<Answer> correctAnswerOptional = answerRepository.findById(new Long(454));
//		if(correctAnswerOptional.isPresent())
//			correctAnswer = correctAnswerOptional.get();
////		List<Answer> answers = (List<Answer>) answerRepository.findAll();
//		Question q1 = new Question(category, "Tu jest pytanko1: ");
//		repository.save(q1);
//		Question q2 = new Question(category, "Tu jest pytanko2: ");
//		q2.addAnswer(correctAnswer);
//
//		repository.save(q2);
//		//repository.save(new Question(category, "Tu jest pytanko3: ", answers, correctAnswer));
//		//repository.save(new Question(category, "Tu jest pytanko4: ", answers, correctAnswer));
//
//
//		for (Question question : repository.findAll()) {
//			log.info(question.toString());
//		}
//
//	}
//
//}
