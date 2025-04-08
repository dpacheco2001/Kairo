package com.kairo.noteservice.service;

import com.kairo.noteservice.entity.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    List<Note> getAllNotes();
    Optional<Note> getNoteById(Long id);
    Note createNote(Note note);
    Note updateNote(Long id, Note note);
    void deleteNote(Long id);
}