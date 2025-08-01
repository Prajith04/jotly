package com.arcmind.jotly.service;

import com.arcmind.jotly.model.JotlyModel;
import com.arcmind.jotly.repository.JotlyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class JotlyService {
    private final JotlyRepository jotlyRepository;
    public JotlyModel saveJotly(JotlyModel jotly) {
        return jotlyRepository.save(jotly);
    }

    public List<JotlyModel> getAllJotlys(Sort sort) {
        return jotlyRepository.findAll(sort);
    }
    public List<JotlyModel> findByTitle(String title) {
        return jotlyRepository.findByTitle(title);
    }
    public Optional<JotlyModel> updateJotly(Long id, JotlyModel updatedJotlyData) {
        return jotlyRepository.findById(id).map(existingJotly -> {
            existingJotly.setTitle(updatedJotlyData.getTitle());
            existingJotly.setContent(updatedJotlyData.getContent());
            return jotlyRepository.save(existingJotly);
        });
    }


    public Optional<JotlyModel> getJotlyById(Long id) {
        return jotlyRepository.findById(id);
    }

    public void deleteJotly(Long id) {
        jotlyRepository.deleteById(id);
    }

}
