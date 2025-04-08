package com.kairo.noteservice.controller;

import com.kairo.noteservice.dto.NoteDTO;
import com.kairo.noteservice.entity.Note;
import com.kairo.noteservice.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAllNotes(){
        List<NoteDTO> notes = noteService.getAllNotes()
            .stream()
            .map(this::entityToDto)
            .toList();
    
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable Long id){
        return noteService.getNoteById(id)
                .map(this::entityToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@Valid @RequestBody NoteDTO dto){
        Note note = dtoToEntity(dto);
        return ResponseEntity.status(201).body(noteService.createNote(note));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @Valid @RequestBody NoteDTO dto){
        Note updated = dtoToEntity(dto);
        return ResponseEntity.ok(noteService.updateNote(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id){
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    private Note dtoToEntity(NoteDTO dto){
        return Note.builder()
                .content(dto.getContent())
                .taskId(dto.getTaskId())
                .pinned(dto.isPinned())
                .build();
    }

    private NoteDTO entityToDto(Note note){
        return NoteDTO.builder()
                .content(note.getContent())
                .taskId(note.getTaskId())
                .pinned(note.isPinned())
                .build();
    }
}
