package com.arcmind.jotly.service;

import com.arcmind.jotly.model.JotlyModel;
import com.arcmind.jotly.repository.JotlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class JotlyService {
    private final JotlyRepository jotlyRepository;
    public JotlyService(JotlyRepository jotlyRepository) {
        this.jotlyRepository = jotlyRepository;
    }
    public JotlyModel saveJotly(JotlyModel jotly) {
        return jotlyRepository.save(jotly);
    }

    public List<JotlyModel> getAllJotlys() {
        return jotlyRepository.findAll();
    }

    public Optional<JotlyModel> getJotlyById(Long id) {
        return jotlyRepository.findById(id);
    }

    public void deleteJotly(Long id) {
        jotlyRepository.deleteById(id);
    }

}
