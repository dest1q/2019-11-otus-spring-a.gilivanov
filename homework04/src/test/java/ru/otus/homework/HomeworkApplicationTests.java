package ru.otus.homework;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.otus.homework.config.LocalizationConfig;
import ru.otus.homework.service.LocalizationService;
import ru.otus.homework.service.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HomeworkApplicationTests {

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@Autowired
	private LocalizationConfig localizationConfig;

	@Test
	void returningCorrectMessage() {
		messageSource.setUseCodeAsDefaultMessage(true);
		LocalizationService localizationService = new LocalizationServiceImpl(messageSource, localizationConfig);
		assertEquals("тест", localizationService.getMessage("тест"));
	}

}
