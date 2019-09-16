package zti.api.springboot.jpa;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import zti.api.springboot.service.AnswerService;
import zti.api.springboot.service.GenreService;
import zti.api.springboot.service.ModeService;
import zti.api.springboot.service.QuestionService;

@Component
public class QuestionCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory
			.getLogger(QuestionCommandLineRunner.class);

	@Autowired
	private QuestionService questionService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private ModeService modeService;

	@Override
	public void run(String... args) throws Exception {
		
		Mode mode = new Mode();
		mode.setName("Zgadnij autora!");
		Mode mode1 = modeService.createMode(mode);
		
		Genre genre = new Genre();
		genre.setName("Rock");
		Genre genre1 = genreService.createGenre(mode.getId(), genre);
		
		Question question = new Question();
		question.setText("question1");
		Question question1 = questionService.createQuestion(genre1.getId(), question);
		
		Answer answer1 = new Answer();
		answer1.setText("answer1");
		answer1.setIsCorrect(true);
		answerService.createAnswer(question1.getId(), answer1);
		
		Answer answer2 = new Answer();
		answer2.setText("answer2");
		answer2.setIsCorrect(false);
		answerService.createAnswer(question1.getId(), answer2);
		
		Answer answer3 = new Answer();
		answer3.setText("answer3");
		answer3.setIsCorrect(false);
		answerService.createAnswer(question1.getId(), answer3);
		
		Answer answer4 = new Answer();
		answer4.setText("answer4");
		answer4.setIsCorrect(false);
		answerService.createAnswer(question1.getId(), answer4);
		
		question1 = questionService.getQuestionById(question1.getId()).get();
		
		List<Answer> answersToQuestion1 = question1.getAnswers();
		Answer correct = questionService.getCorrectAnswer(question1.getId());
		
		for (Answer answer : answersToQuestion1) {
			log.info(answer.getText());
		}
		log.info("correct: " + correct.getText());

	}
}
