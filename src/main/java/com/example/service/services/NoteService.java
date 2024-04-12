package com.example.service.services;

import com.example.data.NoteRepository;
import com.example.notes.dto.Note;
import com.example.notes.dto.create.CreateNoteRequest;
import com.example.notes.dto.create.CreateNoteResponse;
import com.example.notes.dto.delete.DeleteNoteResponse;
import com.example.notes.dto.get.GetNotesResponse;
import com.example.notes.dto.update.UpdateNoteRequest;
import com.example.notes.dto.update.UpdateNoteResponse;
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
public class NoteService {

    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MAX_CONTENT_LENGTH = 1000;
    @Autowired
    private final NoteRepository noteRepository;

    public GetNotesResponse getNotes(){
        List<Note> notes = noteRepository.findAll();
        return GetNotesResponse.success(notes);
    }

    public CreateNoteResponse create(CreateNoteRequest request){
        Optional<CreateNoteResponse.Error> validationError = validateCreateFields(request);

        if (validationError.isPresent()) {
            return CreateNoteResponse.failed(validationError.get());
        }

        Note createdNote = noteRepository.save(Note.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build());

        return CreateNoteResponse.success(createdNote.getId());
    }

    public DeleteNoteResponse delete(UUID id){

        Optional<Note> optionalNote = noteRepository.findById(id);

        if (optionalNote.isEmpty()) {
            return DeleteNoteResponse.failed(DeleteNoteResponse.Error.invalidNoteId);
        }

        Note note = optionalNote.get();

        noteRepository.delete(note);

        return DeleteNoteResponse.success();
    }

    public UpdateNoteResponse update(UpdateNoteRequest request) throws NoteNotFoundException {

        Optional<Note> optionalNote = noteRepository.findById(request.getId());

        if (optionalNote.isEmpty()) {
            return UpdateNoteResponse.failed(UpdateNoteResponse.Error.invalidNoteId);
        }

        Note note = optionalNote.get();

        Optional<UpdateNoteResponse.Error> validationError = validateUpdateFields(request);

        if (validationError.isPresent()) {
            return UpdateNoteResponse.failed(validationError.get());
        }

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());

        noteRepository.save(note);

        return UpdateNoteResponse.success(note);
    }

    private Optional<CreateNoteResponse.Error> validateCreateFields(CreateNoteRequest request) {
        if (Objects.isNull(request.getTitle()) || request.getTitle().length() > MAX_TITLE_LENGTH) {
            return Optional.of(CreateNoteResponse.Error.invalidTitle);
        }

        if (Objects.isNull(request.getContent()) || request.getContent().length() > MAX_CONTENT_LENGTH) {
            return Optional.of(CreateNoteResponse.Error.invalidTitle);
        }

        return Optional.empty();
    }
    private Optional<UpdateNoteResponse.Error> validateUpdateFields(UpdateNoteRequest request) {
        if (Objects.nonNull(request.getTitle()) && request.getTitle().length() > MAX_TITLE_LENGTH) {
            return Optional.of(UpdateNoteResponse.Error.invalidTitleLength);
        }

        if (Objects.nonNull(request.getContent()) && request.getContent().length() > MAX_CONTENT_LENGTH) {
            return Optional.of(UpdateNoteResponse.Error.invalidTitleLength);
        }

        return Optional.empty();
    }
}
