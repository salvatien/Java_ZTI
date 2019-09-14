package zti.api.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class QuizApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(
				QuizApplication.class, args);
	}
}