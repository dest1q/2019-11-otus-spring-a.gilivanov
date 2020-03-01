package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.service.InputOutputService;
import ru.otus.homework.service.LocalizationService;
import ru.otus.homework.service.TestService;

import java.util.List;
import java.util.Arrays;

@ShellComponent
@RequiredArgsConstructor
public class ShellHandler {
    private final TestService testService;
    private final LocalizationService localizationService;
    private final InputOutputService inputOutputService;

    @ShellMethod(value = "Run test", key = "!r")
    @SneakyThrows
    public void run() {
        testService.run();
    }

    @ShellMethod(value = "Set language", key = "!l")
    public void setLanguage(@ShellOption String localeTag) {
        List<String> supportedLang = Arrays.asList(new String[]{"ru-RU", "en"});
        if (supportedLang.contains(localeTag)) {
            localizationService.setLocale(localeTag);
            inputOutputService.printLine("Language changed to " + localeTag);
        } else {
            inputOutputService.printLine("Unsupported language");
        }
    }
}
