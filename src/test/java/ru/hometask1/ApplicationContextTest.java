package ru.hometask1;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationContextTest {

    @Test
    void handleGetInstance() throws InvocationTargetException, IllegalAccessException {
        ApplicationContext applicationContext = new ApplicationContext();
        EncourageService service = applicationContext.getInstance(EncourageService.class);
        System.out.println(service.getRandomPhrase());
        assertNotNull(service.getRandomPhrase());
    }
}
