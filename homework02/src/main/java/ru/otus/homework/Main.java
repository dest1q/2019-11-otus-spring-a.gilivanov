package ru.otus.homework;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.homework.service.TestService;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan
public class Main {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TestService testService = context.getBean(TestService.class);
        testService.run();
    }
}
