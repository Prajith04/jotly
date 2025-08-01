package com.arcmind.jotly.controller;
import com.arcmind.jotly.model.JotlyModel;
import com.arcmind.jotly.service.JotlyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class JotlyController {
    private final JotlyService  jotlyService;
    @PostMapping
    public JotlyModel createNote(@RequestBody JotlyModel note) {
        return jotlyService.saveJotly(note);
    }

    @GetMapping
    public List<JotlyModel> getAllNotes() {
        return jotlyService.getAllJotlys();
    }
    @GetMapping("/{id}")
    public ResponseEntity<JotlyModel> getNoteById(@PathVariable Long id) {
        Optional<JotlyModel> note = jotlyService.getJotlyById(id);
        return note.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<JotlyModel> updateNote(@PathVariable Long id, @RequestBody JotlyModel jotly) {
        return jotlyService.updateJotly(id, jotly)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        jotlyService.deleteJotly(id);
        return ResponseEntity.noContent().build();
    }
}
