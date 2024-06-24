package com.bst.mms.qms.entity;

import com.bst.mms.qms.data.DifficultyLevel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer topicId;

    private String question;

    private Boolean hasMultipleAnswers;

    @Enumerated (EnumType.ORDINAL)
    private DifficultyLevel difficultyLevel;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AnswerOption> answerOptions = new ArrayList<AnswerOption>();
}
