package com.arcmind.jotly.controller;
import com.arcmind.jotly.model.JotlyModel;
import com.arcmind.jotly.service.JotlyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public List<JotlyModel> getAllNotes(
            @RequestParam(defaultValue = "createTime") String sort,
            @RequestParam(defaultValue = "desc") String direction) {

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Sort sortBy = Sort.by(sortDirection, sort);

        // 👇 Get current username from Spring Security
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // 👇 Fetch only notes of the logged-in user
        return jotlyService.getNotesForCurrentUser(username, sortBy);
    }

    @GetMapping("/search")
    public List<JotlyModel> searchNotesByTitle(@RequestParam String title) {
        return jotlyService.findByTitle(title);
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

    @PostMapping("/summarize")
    public ResponseEntity<String> summarizeNote(@RequestBody String content) {
        String summary = generateSummary(content);
        return ResponseEntity.ok(summary);
    }

    private String generateSummary(String content) {
        if (content == null || content.trim().isEmpty()) {
            return "No content to summarize.";
        }
        int firstPeriod = content.indexOf('.');
        int firstExclamation = content.indexOf('!');
        int firstQuestion = content.indexOf('?');

        int endOfFirstSentence = Math.min(
            firstPeriod == -1 ? Integer.MAX_VALUE : firstPeriod,
            Math.min(
                firstExclamation == -1 ? Integer.MAX_VALUE : firstExclamation,
                firstQuestion == -1 ? Integer.MAX_VALUE : firstQuestion
            )
        );

        if (endOfFirstSentence != Integer.MAX_VALUE && endOfFirstSentence < 200) {
            return content.substring(0, endOfFirstSentence + 1).trim();
        }

        // If no sentence ending found or sentence is too long, truncate to 100 chars
        if (content.length() <= 100) {
            return content.trim();
        }

        return content.substring(0, 100).trim() + "...";
    }
}
