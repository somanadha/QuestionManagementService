package com.bst.mms.qms.entity;

import com.bst.mms.dto.AnswerOptionDTO;
import com.bst.mms.dto.DTOExtractable;
import com.bst.mms.dto.DifficultyLevel;
import com.bst.mms.dto.QuestionDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Question implements DTOExtractable<QuestionDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    private Integer topicId;

    private String questionText;

    private Boolean hasMultipleAnswers = false;

    @Enumerated (EnumType.ORDINAL)
    private DifficultyLevel difficultyLevel;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AnswerOption> answerOptions = new ArrayList<AnswerOption>();

    private Boolean isObsolete = false;

    public QuestionDTO extractDTO() {
        List<AnswerOptionDTO> answerOptionDTOList = new ArrayList<>();
        answerOptions.forEach(answerOption -> answerOptionDTOList.add(answerOption.extractDTO()));
        return new QuestionDTO(questionId, topicId, questionText, hasMultipleAnswers, answerOptionDTOList);
    }
}
