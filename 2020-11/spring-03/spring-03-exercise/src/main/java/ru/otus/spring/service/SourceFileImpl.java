package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.ApplicationProperties;

@RequiredArgsConstructor
@Service
public class SourceFileImpl implements SourceFile {
    private final ApplicationProperties props;
    private final MessageSource messageSource;

    @Override
    public String getFileName() {
        return messageSource.getMessage("question.filename", null, props.getLocale());
    }

}
