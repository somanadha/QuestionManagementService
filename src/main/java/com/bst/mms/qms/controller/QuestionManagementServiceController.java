package com.bst.mms.qms.controller;

import com.bst.mms.qms.entity.Question;
import com.bst.mms.qms.service.QuestionManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("qms")
@RestController
public class QuestionManagementServiceController {
    @Autowired
    QuestionManagementService questionManagementService;

    @PostMapping("add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        ResponseEntity<Question> questionResponseEntity;
        Question questionCreated = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            questionCreated = questionManagementService.addQuestion(question);
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
    public ResponseEntity<Question> findQuestionByTopicIdAndQuestionId(@PathVariable Integer topicId,
                                                                        @PathVariable Integer questionId) {
        ResponseEntity<Question> questionResponseEntity;
        Question question = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            question = questionManagementService.findQuestionByTopicIdAndQuestionId(topicId, questionId);
        }
        catch (Exception exception) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println("Exception:"+exception.getMessage());
        }
        finally {
            questionResponseEntity = new ResponseEntity<>(question, httpStatus);
        }
        return questionResponseEntity;
    }

    @GetMapping("findAll/{topicId}")
    public ResponseEntity<List<Question>> findAllQuestionsByTopicId(@PathVariable Integer topicId) {

        ResponseEntity<List<Question>> questionListResponseEntity;
        List<Question> questionList = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            questionList = questionManagementService.findAllQuestionsByTopicId(topicId);
            httpStatus = HttpStatus.CREATED;
        }
        catch (Exception exception) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println("Exception:"+exception.getMessage());
        }
        finally {
            questionListResponseEntity = new ResponseEntity<>(questionList, httpStatus);
        }
        return questionListResponseEntity;
    }
}
