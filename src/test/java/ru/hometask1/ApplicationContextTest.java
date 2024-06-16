package ru.hometask1;

import org.junit.jupiter.api.Test;
import ru.hometask1.controller.Controller;
import ru.hometask1.controller.EncourageController;
import ru.hometask1.dto.ExampleResponse;
import ru.hometask1.service.EncourageService;
import ru.hometask1.service.EncourageServiceImpl;

import javax.naming.ldap.Control;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationContextTest {

    @Test
    void handleGetInstance() throws InvocationTargetException, IllegalAccessException {
        ApplicationContext applicationContext = ApplicationContextHolder.getInstance();
        ApplicationContext applicationContext1 = ApplicationContextHolder.getInstance();
        EncourageService service = applicationContext.getInstance(EncourageServiceImpl.class);
        assertNotNull(service);
        System.out.println(service.getRandomPhrase());
        assertNotNull(service.getRandomPhrase());
        Controller controller = applicationContext.getInstance(Controller.class);
        ExampleResponse response = controller.hello();
        System.out.println(response);
    }
}
