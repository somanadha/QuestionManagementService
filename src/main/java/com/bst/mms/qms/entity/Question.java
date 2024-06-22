package com.bst.mms.qms.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer topicId;

    private String question;

    @Enumerated (EnumType.ORDINAL)
    private DifficultyLevel difficultyLevel;

    private ArrayList<String> answerOptions;
}
