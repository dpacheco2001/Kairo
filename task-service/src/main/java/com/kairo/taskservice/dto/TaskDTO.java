package com.kairo.taskservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres")
    private String title;

    @Size(min = 10, max = 300, message = "La descripción debe tener entre 10 y 300 caracteres")
    private String description;

    private boolean completed;
}