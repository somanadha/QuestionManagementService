package com.bst.mms.qms.dao;

import com.bst.mms.qms.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionManagementServiceDataAccessObject extends JpaRepository<Question, Integer> {

    List<Question> findAllByTopicId(Integer topicId);

    Question findByTopicIdAndId(Integer topicId, Integer id);

    @Query(value = "SELECT * FROM question q Where q.topicId=:topicId ORDER BY RANDOM() LIMIT :questionCount",
            nativeQuery = true)
    List<Question> findRandomQuestionsByTopicId(Integer topicId, int questionCount);
}
