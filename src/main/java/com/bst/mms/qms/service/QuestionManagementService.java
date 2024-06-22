package com.bst.mms.qms.service;

import com.bst.mms.qms.dao.QuestionManagementServiceDataAccessObject;
import com.bst.mms.qms.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionManagementService {

    @Autowired
    QuestionManagementServiceDataAccessObject questionManagementServiceDataAccessObject;

    public Question addQuestion(Question question) {
        return questionManagementServiceDataAccessObject.save(question);
    }

    public List<Question> findAllQuestionsByTopicId(Integer topicId) {
        return questionManagementServiceDataAccessObject.findAllByTopicId(topicId);
    }

    public Question findQuestionByTopicIdAndQuestionId(Integer topicId, Integer questionId) {
        return questionManagementServiceDataAccessObject.findByTopicIdAndId(topicId, questionId);
    }
}
