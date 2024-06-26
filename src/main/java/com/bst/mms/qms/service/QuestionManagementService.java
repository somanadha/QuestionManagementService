package com.bst.mms.qms.service;

import com.bst.mms.qms.repository.QuestionManagementServiceRepository;
import com.bst.mms.qms.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;

import static java.util.Map.entry;

@Service
public class QuestionManagementService {

    @Autowired
    QuestionManagementServiceRepository questionManagementServiceRepository;

    public Entry<String,List<Entry<Integer, String>>> saveQuestion(Question question) {
        question.getAnswerOptions().forEach(option -> option.setQuestion(question));
        return convertQuestionToPrimitive(questionManagementServiceRepository.save(question));
    }

    public Map<Integer, Entry<String,List<Entry<Integer, String>>>> findAllQuestionsByTopicId(Integer topicId) {
        return convertQuestionListToPrimitiveList(questionManagementServiceRepository.findAllByTopicId(topicId));
    }

    public Entry<String,List<Entry<Integer, String>>> findQuestionByTopicIdAndQuestionId(Integer topicId, Integer questionId) {
        return convertQuestionToPrimitive(questionManagementServiceRepository.findByTopicIdAndId(topicId, questionId));
    }

    public Map<Integer, Entry<String,List<Entry<Integer, String>>>> findRandomQuestions(Integer topicId, Integer difficulty, Integer count) {
        return convertQuestionListToPrimitiveList(questionManagementServiceRepository.findRandomQuestionsByTopicIdAndDifficultyLevel(
                topicId, difficulty, count));
    }

    private Map<Integer, Entry<String,List<Entry<Integer, String>>>> convertQuestionListToPrimitiveList(
            List<Question> questions){

        HashMap<Integer, Entry<String,List<Entry<Integer, String>>>> questionsAndOptionsMap = new HashMap<>();
        if (questions != null && !questions.isEmpty()){
            questions.forEach(question ->  {
                Entry<String, List<Entry<Integer, String>>> questionAndOptions = convertQuestionToPrimitive(question);
                questionsAndOptionsMap.put(question.getId(), questionAndOptions);
            });
        }
        return questionsAndOptionsMap;
    }

    private Entry<String, List<Entry<Integer, String>>> convertQuestionToPrimitive(Question question) {
        String questionText = "";
        List<Entry<Integer, String>> answersList = new ArrayList<>();
        if (question != null) {
            questionText = question.getQuestionText();
            var answerOptions = question.getAnswerOptions();
            if (answerOptions != null && !answerOptions.isEmpty()) {
                answerOptions.forEach(answer -> answersList.add(Map.entry(answer.getId(), answer.getOption())));
            }
        }
        return Map.entry(questionText, answersList);
    }
}
