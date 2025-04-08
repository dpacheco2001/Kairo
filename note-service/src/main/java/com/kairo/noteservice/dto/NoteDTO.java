package com.kairo.noteservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteDTO {

    @NotBlank(message = "El contenido no puede estar vacío")
    @Size(min = 5, max = 500, message = "El contenido debe tener entre 5 y 500 caracteres")
    private String content;

    private Long taskId;

    private boolean pinned;
}
