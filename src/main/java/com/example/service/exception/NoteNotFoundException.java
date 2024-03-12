package com.example.service.exception;

import java.util.UUID;

public class NoteNotFoundException extends Exception{

    private static final String NOTE_NOT_FOUND_TEXT = "Note with id = %s not found!";
//    public NoteNotFoundException() {
//        super(NOTE_NOT_FOUND_TEXT);
//    }
    public NoteNotFoundException(UUID noteId) {
        super(String.format(NOTE_NOT_FOUND_TEXT, noteId));
    }

}
