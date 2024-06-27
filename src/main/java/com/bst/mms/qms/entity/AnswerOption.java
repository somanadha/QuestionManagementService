package com.bst.mms.qms.entity;

import com.bst.mms.dto.DTOExtractable;
import com.bst.mms.dto.AnswerOptionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AnswerOption implements DTOExtractable<AnswerOptionDTO>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;

    private String answerOptionText;

    private Boolean isAnswer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    public AnswerOptionDTO extractDTO() {
        return new AnswerOptionDTO(answerId, answerOptionText);
    }
}
