package com.bst.mms.qms.controller;

import com.bst.mms.dto.QuestionDTO;
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
    public ResponseEntity<QuestionDTO> saveQuestion(@RequestBody Question question) {
        ResponseEntity<QuestionDTO> questionDTOResponseEntity;
        QuestionDTO questionDTO = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            questionDTO = questionManagementService.saveQuestion(question);
            httpStatus = HttpStatus.CREATED;
        }
        catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }
        finally {
            questionDTOResponseEntity = new ResponseEntity<>(questionDTO, httpStatus);
        }
        return questionDTOResponseEntity;
    }

    @GetMapping("find/{topicId}/{questionId}")
    public ResponseEntity<QuestionDTO> findQuestionByTopicIdAndQuestionId(@PathVariable Integer topicId,
                                                                          @PathVariable Integer questionId) {

        ResponseEntity<QuestionDTO> questionDTOResponseEntity;
        QuestionDTO questionDTO = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            questionDTO = questionManagementService.findQuestionByTopicIdAndQuestionId(topicId, questionId);
            httpStatus = HttpStatus.OK;
        }
        catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }
        finally {
            questionDTOResponseEntity = new ResponseEntity<>(questionDTO, httpStatus);
        }
        return questionDTOResponseEntity;
    }

    @GetMapping("findAll/{topicId}")
    public ResponseEntity<List<QuestionDTO>> findAllQuestionsByTopicId(@PathVariable Integer topicId) {

        ResponseEntity<List<QuestionDTO>> questionDTOListResponseEntity;
        List<QuestionDTO> questionDTOList = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            questionDTOList = questionManagementService.findAllQuestionsByTopicId(topicId);
        }
        catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }
        finally {
            questionDTOListResponseEntity = new ResponseEntity<>(questionDTOList, httpStatus);
        }
        return questionDTOListResponseEntity;
    }

    @GetMapping("findRandom/{topicId}/{difficulty}/{count}")
    public ResponseEntity<List<QuestionDTO>> findRandomQuestions(@PathVariable Integer topicId,
                                                                 @PathVariable Integer difficulty,
                                                                 @PathVariable Integer count) {

        ResponseEntity<List<QuestionDTO>> questionDTOListResponseEntity;
        List<QuestionDTO> questionDTOList = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            questionDTOList = questionManagementService.findRandomQuestions(topicId, difficulty, count);
            httpStatus = HttpStatus.CREATED;
        }
        catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }
        finally {
            questionDTOListResponseEntity = new ResponseEntity<>(questionDTOList, httpStatus);
        }
        return questionDTOListResponseEntity;
    }

    @GetMapping("findByIds")
    public ResponseEntity<List<QuestionDTO>> findQuestionsByQuestionIdList(@RequestBody List<Integer> questionIds) {

        ResponseEntity<List<QuestionDTO>> questionDTOListResponseEntity;
        List<QuestionDTO> questionDTOList = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            questionDTOList = questionManagementService.findQuestionAndAnswerOptionsDTOListByQuestionIds(questionIds);
            httpStatus = HttpStatus.CREATED;
        }
        catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }
        finally {
            questionDTOListResponseEntity = new ResponseEntity<>(questionDTOList, httpStatus);
        }
        return questionDTOListResponseEntity;
    }
}
