package ru.otus.homework.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class InputOutputServiceImpl implements InputOutputService {
    private final PrintStream out;
    private final Scanner scanner;

    public InputOutputServiceImpl(InputStream in, PrintStream out) {
        this.out = out;
        this.scanner = new Scanner(in);
    }

    public InputOutputServiceImpl() {
        this.out = System.out;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void printLine(String s) {
        this.out.println(s);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
