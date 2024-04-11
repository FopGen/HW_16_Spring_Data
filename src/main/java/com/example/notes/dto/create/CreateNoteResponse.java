package com.example.notes.dto.create;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class CreateNoteResponse {
    private Error error;

    private UUID createdNoteId;

    public enum Error {
        ok,
        invalidTitle,
        invalidContent
    }

    public static CreateNoteResponse success(UUID createdNoteId) {
        return builder().error(Error.ok).createdNoteId(createdNoteId).build();
    }

    public static CreateNoteResponse failed(Error error) {
        return builder().error(error).createdNoteId(UUID.fromString("")).build();
    }
}