package com.kairo.noteservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El contenido no puede estar vacío")
    @Size(min = 5, max = 500, message = "El contenido debe tener entre 5 y 500 caracteres")
    private String content;

    private Long taskId;

    private boolean pinned;
}
