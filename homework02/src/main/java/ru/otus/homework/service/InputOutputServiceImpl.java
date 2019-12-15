package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

@Service
public class InputOutputServiceImpl implements InputOutputService {
    private final PrintStream out;
    private final Scanner scanner;
    private final LocalizationService localizationService;

    @Autowired
    public InputOutputServiceImpl(LocalizationService localizationService) {
        this.out = System.out;
        this.scanner = new Scanner(System.in);
        this.localizationService = localizationService;
    }

    @Override
    public  void printLine(String s){
        this.out.println(s);
    }

    @Override
    public void printMessage(String message, String...params) {
        this.out.println(localizationService.getMessage(message, params));
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

}
