package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.homework.exceptions.HomeworkException;
import ru.otus.homework.service.TestService;

@SpringBootApplication
public class HomeworkApplication {

	public static void main(String[] args) throws HomeworkException {
		ApplicationContext ctx = SpringApplication.run(HomeworkApplication.class, args);
	}

}
