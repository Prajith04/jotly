package com.arcmind.jotly.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class JotlyModel {
    @ManyToOne
    @Setter
    private UserModel userModel;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime updateTime;
    @Setter
    private String title;
    @Lob
    @Setter
    private String content;



    public JotlyModel(String title, String content, UserModel userModel) {
        this.title = title;
        this.content = content;
        this.userModel = userModel;
    }

}
