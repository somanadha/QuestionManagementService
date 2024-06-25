package com.bst.mms.qms.service;

import com.bst.mms.qms.repository.QuestionManagementServiceRepository;
import com.bst.mms.qms.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;

@Service
public class QuestionManagementService {

    @Autowired
    QuestionManagementServiceRepository questionManagementServiceRepository;

    public SimpleEntry<String,List<String>> saveQuestion(Question question) {
        question.getAnswerOptions().forEach(option -> option.setQuestion(question));
        return convertQuestionToPrimitive(questionManagementServiceRepository.save(question));
    }

    public Map<Integer, SimpleEntry<String,List<String>>> findAllQuestionsByTopicId(Integer topicId) {
        return convertQuestionListToPrimitiveList(questionManagementServiceRepository.findAllByTopicId(topicId));
    }

    public SimpleEntry<String,List<String>> findQuestionByTopicIdAndQuestionId(Integer topicId, Integer questionId) {
        return convertQuestionToPrimitive(questionManagementServiceRepository.findByTopicIdAndId(topicId, questionId));
    }

    public Map<Integer, SimpleEntry<String,List<String>>> findRandomQuestions(Integer topicId, Integer difficulty, Integer count) {
        return convertQuestionListToPrimitiveList(questionManagementServiceRepository.findRandomQuestionsByTopicIdAndDifficultyLevel(
                topicId, difficulty, count));
    }

    private Map<Integer, SimpleEntry<String,List<String>>> convertQuestionListToPrimitiveList(
            List<Question> questions){

        HashMap<Integer, SimpleEntry<String,List<String>>> questionsAndOptionsMap = new HashMap<>();
        if (questions != null && !questions.isEmpty()){
            questions.forEach(question ->  {
                SimpleEntry<String, List<String>> questionAndOptions = convertQuestionToPrimitive(question);
                questionsAndOptionsMap.put(question.getId(), questionAndOptions);
            });
        }
        return questionsAndOptionsMap;
    }

    private SimpleEntry<String, List<String>> convertQuestionToPrimitive(Question question) {
        String questionText = "";
        List<String> answersList = new ArrayList<>();
        if (question != null) {
            questionText = question.getQuestionText();
            var answerOptions = question.getAnswerOptions();
            if (answerOptions != null && !answerOptions.isEmpty()) {
                answerOptions.forEach(answer -> answersList.add(answer.getOption()));
            }
        }
        return new SimpleEntry<String, List<String>>(questionText, answersList);
    }
}
