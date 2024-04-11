package com.example.notes.dto.update;

import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateNoteRequest {
    private UUID id;
    private String title;
    private String content;
}
