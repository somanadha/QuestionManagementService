package com.bst.mms.qms.controller;

import com.bst.mms.qms.entity.Question;
import com.bst.mms.qms.service.QuestionManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;

@RequestMapping("qms")
@RestController
public class QuestionManagementServiceController {
    @Autowired
    QuestionManagementService questionManagementService;

    @PostMapping("add")
    public ResponseEntity<SimpleEntry<String,List<String>>> saveQuestion(@RequestBody Question question) {
        ResponseEntity<SimpleEntry<String,List<String>>> questionResponseEntity;
        SimpleEntry<String,List<String>> questionCreated = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            questionCreated = questionManagementService.saveQuestion(question);
            httpStatus = HttpStatus.CREATED;
        }
        catch (Exception exception) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println("Exception:"+exception.getMessage());
        }
        finally {
            questionResponseEntity = new ResponseEntity<>(questionCreated, httpStatus);
        }
        return questionResponseEntity;
    }

    @GetMapping("find/{topicId}/{questionId}")
    public ResponseEntity<SimpleEntry<String,List<String>>> findQuestionByTopicIdAndQuestionId(
            @PathVariable Integer topicId, @PathVariable Integer questionId) {

        ResponseEntity<SimpleEntry<String,List<String>>> questionResponseEntity;
        SimpleEntry<String,List<String>> questionAndAnswers = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            questionAndAnswers = questionManagementService.findQuestionByTopicIdAndQuestionId(topicId, questionId);
        }
        catch (Exception exception) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println("Exception:"+exception.getMessage());
        }
        finally {
            questionResponseEntity = new ResponseEntity<>(questionAndAnswers, httpStatus);
        }
        return questionResponseEntity;
    }

    @GetMapping("findAll/{topicId}")
    public ResponseEntity<Map<Integer, SimpleEntry<String,List<String>>>> findAllQuestionsByTopicId(
            @PathVariable Integer topicId) {

        ResponseEntity<Map<Integer, SimpleEntry<String,List<String>>>> questionListResponseEntity;
        Map<Integer, SimpleEntry<String,List<String>>> questionsMap = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            questionsMap = questionManagementService.findAllQuestionsByTopicId(topicId);
            httpStatus = HttpStatus.CREATED;
        }
        catch (Exception exception) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println("Exception:"+exception.getMessage());
        }
        finally {
            questionListResponseEntity = new ResponseEntity<>(questionsMap, httpStatus);
        }
        return questionListResponseEntity;
    }

    @GetMapping("findRandom/{topicId}/{difficulty}/{count}")
    public ResponseEntity<Map<Integer, SimpleEntry<String,List<String>>>> findRandomQuestions(
            @PathVariable Integer topicId, @PathVariable Integer difficulty, @PathVariable Integer count) {

        ResponseEntity<Map<Integer, SimpleEntry<String,List<String>>>> questionListResponseEntity;
        Map<Integer, SimpleEntry<String,List<String>>> questionsMap= null;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            questionsMap = questionManagementService.findRandomQuestions(topicId, difficulty, count);
            httpStatus = HttpStatus.CREATED;
        }
        catch (Exception exception) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println("Exception:"+exception.getMessage());
        }
        finally {
            questionListResponseEntity = new ResponseEntity<>(questionsMap, httpStatus);
        }
        return questionListResponseEntity;
    }
}
