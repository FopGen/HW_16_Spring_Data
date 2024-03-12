package com.example.service.services;

import com.example.data.NoteRepository;
import com.example.service.dto.Note;
import com.example.service.exception.NoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    @Autowired
    private final NoteRepository noteRepository;

    @Override
    public List<Note> listAll(){
        return noteRepository.findAll();
    }

    @Override
    public Note add(Note note){
        return noteRepository.save(note);
    }

    @Override
    public void deleteById(UUID id){
        noteRepository.deleteById(id);
    }

    @Override
    public void update(Note note) throws NoteNotFoundException {
        if(Objects.isNull(note.getId())){
            throw new NoteNotFoundException(note.getId());
        }
        noteRepository.save(note);
    }

    @Override
    public Optional<Note> getById(UUID id){
        return noteRepository.findById(id);
    }

}
