package com.example.service.services;

import com.example.service.dto.Note;
import com.example.service.exception.NoteNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteService {
     List<Note> listAll();

     Note add(Note note) throws NoteNotFoundException;

     void deleteById(UUID id) throws NoteNotFoundException;

     void update(Note note) throws NoteNotFoundException;

     Optional<Note> getById(UUID id) throws NoteNotFoundException;

}
