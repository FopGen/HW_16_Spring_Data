package com.example.notes.dto.get;
import com.example.notes.dto.Note;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetNotesResponse {
    private Error error;

    private List<Note> userNotes;

    public enum Error {
        ok
    }

    public static GetNotesResponse success(List<Note> notes) {
        return builder().error(Error.ok).userNotes(notes).build();
    }

    public static GetNotesResponse failed(Error error) {

        return builder().error(error).userNotes(null).build();
    }
}