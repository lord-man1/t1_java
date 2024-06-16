package ru.hometask1.controller;

import ru.hometask1.annotation.Logged;
import ru.hometask1.dto.ExampleResponse;

public interface Controller {
    @Logged
    ExampleResponse hello();
}
