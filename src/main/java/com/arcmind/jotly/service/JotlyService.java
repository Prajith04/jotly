package com.arcmind.jotly.service;

import com.arcmind.jotly.model.JotlyModel;
import com.arcmind.jotly.model.UserModel;
import com.arcmind.jotly.repository.JotlyRepository;
import com.arcmind.jotly.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class JotlyService {
    private final JotlyRepository jotlyRepository;
    private final UserRepository userRepository;
    public JotlyModel saveJotly(JotlyModel jotly) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        jotly.setUserModel(user);
        return jotlyRepository.save(jotly);
    }

    public List<JotlyModel> getNotesForCurrentUser(String username, Sort sort) {
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return jotlyRepository.findByUserModel(user,sort);
    }

    public List<JotlyModel> findByTitle(String title) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return jotlyRepository.findByUserModelAndTitle(user,title);
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
