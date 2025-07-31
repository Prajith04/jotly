package com.arcmind.jotly.repository;

import com.arcmind.jotly.model.JotlyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JotlyRepository extends JpaRepository<JotlyModel,Long> {

}
