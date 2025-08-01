package com.arcmind.jotly.repository;

import com.arcmind.jotly.model.JotlyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JotlyRepository extends JpaRepository<JotlyModel,Long> {
    List<JotlyModel> findByTitle(String title);

}
