package com.bst.mms.qms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import javax.swing.text.html.Option;

@Data
@Entity
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String option;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "question_id", referencedColumnName="id", foreignKey = @ForeignKey(name="id"))
    private Question question;

    public AnswerOption() {
    }

    public AnswerOption(Question question, String option) {
        setQuestion(question);;
        setOption(option);
    }
}
