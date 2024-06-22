package com.bst.mms.qms.dao;

import com.bst.mms.qms.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionManagementServiceDataAccessObject extends JpaRepository<Question, Integer> {

    List<Question> findAllByTopicId(Integer topicId);

    Question findByTopicIdAndId(Integer topicId, Integer id);
}
