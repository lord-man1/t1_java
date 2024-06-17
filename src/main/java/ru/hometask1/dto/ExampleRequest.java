package ru.hometask1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ExampleRequest(@NotBlank String phrase) {
}
