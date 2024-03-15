package com.example.controller;

import com.example.service.dto.Note;
import com.example.service.exception.NoteNotFoundException;
import com.example.service.services.NoteService;
import com.example.service.services.NoteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;
@Validated
//@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/list")
    public ModelAndView getListNote(){
        ModelAndView result = new ModelAndView("note/list");
        result.addObject("notes", noteService.listAll());
        return result;
    }

    @GetMapping("/delete")
    public ModelAndView deleteNote(@RequestParam(value = "id") UUID id) throws NoteNotFoundException {
        ModelAndView result = new ModelAndView();
        noteService.deleteById(id);
        return getListNote();
    }
    @GetMapping("/edit")
    public ModelAndView editNote(@NotNull @RequestParam(value = "id") UUID id) throws NoteNotFoundException {
        ModelAndView result = new ModelAndView("note/update");
        result.addObject("note", noteService.getById(id).get());
        return result;
    }
    @PostMapping("/update")
    public ModelAndView updateNote(Note dto) throws NoteNotFoundException {
        noteService.update(dto);
        return getListNote();
    }
}
