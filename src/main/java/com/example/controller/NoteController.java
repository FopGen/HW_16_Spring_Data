package com.example.controller;

import com.example.notes.dto.create.CreateNoteRequest;
import com.example.notes.dto.create.CreateNoteResponse;
import com.example.notes.dto.delete.DeleteNoteResponse;
import com.example.notes.dto.get.GetNotesResponse;
import com.example.notes.dto.update.UpdateNoteRequest;
import com.example.notes.dto.update.UpdateNoteResponse;
import com.example.service.exception.NoteNotFoundException;
import com.example.service.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/create")
    public CreateNoteResponse createNote(@RequestBody CreateNoteRequest request){
        return noteService.create(request);
    }

    @GetMapping("/notes")
    public GetNotesResponse getNotes(){
        return noteService.getNotes();
    }

    @PatchMapping("/update")
    public UpdateNoteResponse updateNote(UpdateNoteRequest request) throws NoteNotFoundException {
        return noteService.update(request);
    }
    @DeleteMapping("/delete")
    public DeleteNoteResponse delete(@RequestParam(value = "id") UUID id){

        return noteService.delete(id);
    }

}
