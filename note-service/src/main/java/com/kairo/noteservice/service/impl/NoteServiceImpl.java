package com.kairo.noteservice.service.impl;

import com.kairo.noteservice.entity.Note;
import com.kairo.noteservice.repository.NoteRepository;
import com.kairo.noteservice.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note updateNote(Long id, Note updated) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        note.setContent(updated.getContent());
        note.setTaskId(updated.getTaskId());
        note.setPinned(updated.isPinned());
        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
