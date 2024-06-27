package com.bst.mms.qms.service;

import com.bst.mms.dto.QuestionDTO;
import com.bst.mms.qms.repository.QuestionManagementServiceRepository;
import com.bst.mms.qms.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionManagementService {

    @Autowired
    QuestionManagementServiceRepository questionManagementServiceRepository;

    public QuestionDTO saveQuestion(Question question) {
        question.getAnswerOptions().forEach(option -> option.setQuestion(question));
        return questionManagementServiceRepository.save(question).extractDTO();
    }

    public List<QuestionDTO> findAllQuestionsByTopicId(Integer topicId) {
        return convertToDTOList(questionManagementServiceRepository.findAllByTopicId(topicId));
    }

    public QuestionDTO findQuestionByTopicIdAndQuestionId(Integer topicId, Integer questionId) {
        return questionManagementServiceRepository.findByTopicIdAndQuestionId(topicId, questionId).extractDTO();
    }

    public List<QuestionDTO> findRandomQuestions(Integer topicId, Integer difficulty, Integer count) {
        return convertToDTOList(questionManagementServiceRepository.findRandomQuestionsByTopicIdAndDifficultyLevel(
                topicId, difficulty, count));
    }

    public List<QuestionDTO> findQuestionAndAnswerOptionsDTOListByQuestionIds(List<Integer> questionIds) {
        return convertToDTOList(questionManagementServiceRepository.findAllByQuestionIdIn(questionIds));
    }

    private List<QuestionDTO> convertToDTOList(List<Question> questionsList){
        List<QuestionDTO> questionsDTOList = new ArrayList<>();
        questionsList.forEach(question -> questionsDTOList.add(question.extractDTO()));
        return questionsDTOList;
    }
}